package com.yannqing.template.aop;

import com.yannqing.template.annotation.AuthCheck;
import com.yannqing.template.domain.entity.Permissions;
import com.yannqing.template.domain.entity.User;
import com.yannqing.template.enums.PermissionType;
import com.yannqing.template.enums.ErrorType;
import com.yannqing.template.exception.BusinessException;
import com.yannqing.template.service.UserService;
import com.yannqing.template.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 * 权限校验 AOP
 *
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 执行拦截
     *
     * @param joinPoint
     * @param authCheck
     * @return
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String code = authCheck.code();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 查看访问的接口需要的权限
        PermissionType mustRoleEnum = PermissionType.getEnumByValue(code);
        // 1. 不需要权限，放行
        if (mustRoleEnum == null) {
            return joinPoint.proceed();
        }
        // 2. 获取用户的权限
        // 当前登录用户
        String token = request.getHeader("token");
        User loginUser = JwtUtils.getUserFromToken(token);
        List<Permissions> permissionByLoginUser = userService.getPermissionByUser(loginUser.getUserId());

        // 3. 判断用户是否拥有访问的权限
        if (permissionByLoginUser == null) {
            throw new BusinessException(ErrorType.NO_AUTH_ERROR);
        }
        List<Permissions> newPermission = permissionByLoginUser.stream().filter(permission -> permission.getCode().equals(mustRoleEnum.getCode())).toList();
        if (newPermission.isEmpty()) {
            throw new BusinessException(ErrorType.NO_AUTH_ERROR);
        }
        // 通过权限校验，放行
        return joinPoint.proceed();
    }
}