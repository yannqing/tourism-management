package com.yannqing.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yannqing.template.common.CommonConstant;
import com.yannqing.template.domain.dto.user.AddUserDto;
import com.yannqing.template.domain.dto.user.QueryUserDto;
import com.yannqing.template.domain.dto.user.UpdateMyInfoDto;
import com.yannqing.template.domain.dto.user.UpdateUserDto;
import com.yannqing.template.domain.entity.*;
import com.yannqing.template.domain.vo.user.MySelfInfoVo;
import com.yannqing.template.domain.vo.user.UserVo;
import com.yannqing.template.enums.ErrorType;
import com.yannqing.template.enums.RoleType;
import com.yannqing.template.exception.BusinessException;
import com.yannqing.template.mapper.PermissionsMapper;
import com.yannqing.template.mapper.RoleMapper;
import com.yannqing.template.mapper.RolePermissionsMapper;
import com.yannqing.template.service.RoleUserService;
import com.yannqing.template.service.UserService;
import com.yannqing.template.mapper.UserMapper;
import com.yannqing.template.utils.JwtUtils;
import com.yannqing.template.utils.RedisCache;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
* @author 67121
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-01-10 15:45:04
*/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RoleUserService roleUserService;

    @Resource
    private RedisCache redisCache;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RolePermissionsMapper rolePermissionsMapper;

    @Resource
    private PermissionsMapper permissionsMapper;

    /**
     * 管理员新增用户
     * @param addUserDto
     * @return
     */
    @Override
    public boolean addUser(AddUserDto addUserDto) {
        String username = Optional.ofNullable(addUserDto.getUsername())
                .filter(s -> !s.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));
        String password = Optional.ofNullable(addUserDto.getPassword())
                .filter(s -> !s.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));
        Optional.ofNullable(addUserDto.getNickName())
                .filter(s -> !s.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // username 必须不能重复，且大于 6 个字符，小于等于 15 个字符
        if (username.length() <= 6 || username.length() > 15) {
            throw new BusinessException(ErrorType.USERNAME_LENGTH_ERROR);
        }
        boolean isUsernameExist = this.baseMapper.exists(new QueryWrapper<User>().eq("username", username));
        if (isUsernameExist) {
            throw new BusinessException(ErrorType.USERNAME_ALREADY_EXIST);
        }

        // password 必须大于等于 8 位，小于 15 位
        if (password.length() < 8 || password.length() >= 15) {
            throw new BusinessException(ErrorType.PASSWORD_LENGTH_ERROR);
        }

        User addUser = AddUserDto.dtoToUser(addUserDto);
        addUser.setPassword(passwordEncoder.encode(password));

        // 插入新注册用户
        int result = this.baseMapper.insert(addUser);
        log.info("管理员新增用户{}成功", username);

        // 给用户添加角色
        roleUserService.addRole(username, RoleType.USER);
        log.info("用户{}添加角色{}成功", addUser.getUsername(), RoleType.USER.getRoleCode());
        return result > 0;
    }

    /**
     * 管理员删除单个用户
     * @param id
     * @return
     */
    @Override
    public boolean deleteUserById(Integer id) {
        // 查看用户是否存在
        User deleteUser = verifyUserId(id);
        String username = deleteUser.getUsername();

        // 删除用户
        int result = this.baseMapper.deleteById(id);
        log.info("删除用户{}", username);
        // 删除此用户所有的角色
        roleUserService.remove(new QueryWrapper<RoleUser>().eq("uid", id));
        log.info("删除用户{}的所有角色", username);
        return result > 0;
    }

    /**
     * 批量删除用户
     * @param userIds
     * @return
     */
    @Override
    public int deleteBatchUser(Integer... userIds) {
        // 判空
        if (userIds == null || userIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 判断参数是否有效
        List<Integer> userIdList = Arrays.asList(userIds);
        List<User> users = this.baseMapper.selectBatchIds(userIdList);
        if (users == null || users.size() < userIdList.size()) {
            throw new BusinessException(ErrorType.USER_NOT_EXIST);
        }

        // 删除用户，并记录日志
        int result = this.baseMapper.deleteBatchIds(userIdList);
        logDeletedUsers(users);

        // 删除此用户所有的角色，并记录日志
        roleUserService.remove(new QueryWrapper<RoleUser>().in("uid", userIdList));
        log.info("批量删除用户的角色");

        return result;
    }

    /**
     * 查询单个用户
     * @param id
     * @return
     */
    @Override
    public UserVo getUserById(Integer id) {
        // 有效性检验
        User getUser = verifyUserId(id);

        // 返回封装类 vo
        return UserVo.objToVo(getUser);
    }

    /**
     * 查询所有用户
     * @param queryUserDto
     * @return
     */
    @Override
    public Page<User> getAll(QueryUserDto queryUserDto) {
        // 判空
        Optional.ofNullable(queryUserDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer userId = queryUserDto.getUserId();
        String username = queryUserDto.getUsername();
        String address = queryUserDto.getAddress();
        String phone = queryUserDto.getPhone();
        String email = queryUserDto.getEmail();
        Integer age = queryUserDto.getAge();
        String signature = queryUserDto.getSignature();
        Integer sex = queryUserDto.getSex();
        String nickName = queryUserDto.getNickName();
        String description = queryUserDto.getDescription();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(userId != null, "userId", userId);
        queryWrapper.like(StringUtils.isNotBlank(username), "username", username);
        queryWrapper.like(StringUtils.isNotBlank(description), "description", description);
        queryWrapper.like(StringUtils.isNotBlank(address), "address", address);
        queryWrapper.like(StringUtils.isNotBlank(phone), "phone", phone);
        queryWrapper.like(StringUtils.isNotBlank(email), "email", email);
        queryWrapper.eq(age!= null, "age", age);
        queryWrapper.like(StringUtils.isNotBlank(signature), "signature", signature);
        queryWrapper.eq(sex != null, "sex", sex);
        queryWrapper.like(StringUtils.isNotBlank(nickName), "nickName", nickName);

        log.info("管理员查询所有的用户");
        // TODO 这里查询出来的数据，total 为0
        return this.page(new Page<>(queryUserDto.getCurrent(), queryUserDto.getPageSize()), queryWrapper);
    }

    /**
     * 管理员修改用户信息
     * @param updateUserDto
     * @return
     */
    @Override
    public boolean updateUserByAdmin(UpdateUserDto updateUserDto) {
        // 参数判空
        Optional.ofNullable(updateUserDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 获取参数
        Integer userId = updateUserDto.getUserId();
        List<Integer> roleIds = updateUserDto.getRoleIds();

        // 参数有效性判断
        verifyUserId(userId);

        // TODO 打断点调试，查看内容
        // 更新用户信息
        User updateUser = UpdateUserDto.dtoToUser(updateUserDto);
        boolean updateResult = this.updateById(updateUser);
        log.info("更新用户（id：{}）信息", userId);

        // 修改用户角色
        if (updateUserDto.getRoleIds() != null && !updateUserDto.getRoleIds().isEmpty()) {
            roleUserService.remove(new QueryWrapper<RoleUser>().eq("uid", userId));
            roleIds.forEach(roleId -> {
                roleUserService.addRole(userId, roleId);
            });
            log.info("更新用户（id：{}）的角色信息{}", userId, roleIds.stream().map(String::valueOf));
        }

        return updateResult;
    }

    /**
     * 更新个人信息
     * @param updateMyInfoDto
     * @param request
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public boolean updateMyInfo(UpdateMyInfoDto updateMyInfoDto, HttpServletRequest request) throws JsonProcessingException {
        User loginUser = verifyToken(request);

        // 更新用户信息
        User updateUserInfo = UpdateMyInfoDto.dtoToUser(updateMyInfoDto);
        updateUserInfo.setUserId(loginUser.getUserId());
        boolean updateResult = this.updateById(updateUserInfo);
        log.info("更新个人信息（userId:{}）", loginUser.getUserId());

        return updateResult;
    }

    /**
     * 修改个人密码
     * @param originPassword
     * @param newPassword
     * @param againPassword
     * @param request
     * @return
     */
    @Override
    public boolean updatePassword(String originPassword, String newPassword, String againPassword, HttpServletRequest request) throws JsonProcessingException {
        User loginUser = verifyToken(request);

        // 判空
        Optional.ofNullable(originPassword)
                .filter(op -> !op.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));
        Optional.ofNullable(newPassword)
                .filter(op -> !op.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));
        Optional.ofNullable(againPassword)
                .filter(op -> !op.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性检验
        if (newPassword.length() < 8 || newPassword.length() >= 15) {
            throw new BusinessException(ErrorType.PASSWORD_LENGTH_ERROR);
        }
        if (!newPassword.equals(againPassword)) {
            throw new BusinessException(ErrorType.PASSWORD_NOT_EQUALS);
        }

        // 原始密码验证（先获取到登录用户的全部信息）
        User loginUserAllInfo = this.getById(loginUser.getUserId());
        if (!passwordEncoder.matches(originPassword, loginUserAllInfo.getPassword())) {
            throw new BusinessException(ErrorType.PASSWORD_NOT_MATCH);
        }

        // 修改密码
        boolean updateResult = this.update(
                new UpdateWrapper<User>()
                        .eq("userId", loginUser.getUserId())
                        .set("password", passwordEncoder.encode(newPassword))
        );
        log.info("修改个人密码（username:{}）", loginUser.getUsername());

        // 退出登录
        redisCache.deleteObject("token:"+request.getHeader("token"));
        log.info("用户退出，需重新登录");

        return updateResult;
    }

    /**
     * 管理员重置用户密码
     * @param id
     * @return
     */
    @Override
    public boolean resetUserPassword(Integer id) {
        // 有效性检验
        User resetPasswordUser = verifyUserId(id);

        // 重置密码
        resetPasswordUser.setPassword(passwordEncoder.encode(CommonConstant.RESET_PASSWORD));
        boolean updateResult = this.updateById(resetPasswordUser);

        log.info("管理员重置用户(username: {})密码", resetPasswordUser.getUsername());
        return updateResult;
    }

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     * @return
     */
    @Override
    public boolean addRoleToUser(Integer userId, Integer... roleIds) {
        verifyUserId(userId);
        Optional.ofNullable(roleIds)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // roleIds 有效性判断
        List<Role> addRoles = roleMapper.selectBatchIds(Arrays.asList(roleIds));
        if (addRoles == null || addRoles.isEmpty() || addRoles.size() < roleIds.length) {
            throw new BusinessException(ErrorType.ROLE_NOT_EXIST);
        }

        // 添加角色
        addRoles.forEach(role -> {
            RoleUser addRoleUser = roleUserService.getOne(new QueryWrapper<RoleUser>().eq("uid", userId).eq("rid", role.getId()));
            if (addRoleUser == null) {
                RoleUser roleUser = new RoleUser();
                roleUser.setUid(userId);
                roleUser.setRid(role.getId());
                roleUserService.save(roleUser);
            }
        });
        log.info("给用户{}添加角色{}", userId, addRoles);

        return true;
    }

    /**
     * 查询用户角色
     * @param userId 要查询的用户id
     * @return
     */
    @Override
    public List<Role> getRoleByUser(Integer userId) {
        verifyUserId(userId);

        List<RoleUser> getRoleUserList = roleUserService.list(new QueryWrapper<RoleUser>().eq("uid", userId));

        if (getRoleUserList == null) {
            return null;
        }

        List<Integer> roleList = getRoleUserList.stream().map(RoleUser::getRid).toList();
        List<Role> roles = roleMapper.selectBatchIds(roleList);
        log.info("查询用户（id：{}）的角色（{}）", userId, roles);

        return roles;
    }

    /**
     * 查询用户的权限
     * @param userId
     * @return
     */
    @Override
    public List<Permissions> getPermissionByUser(Integer userId) {
        verifyUserId(userId);

        List<Role> roles = getRoleByUser(userId);
        if (roles == null) {
            return null;
        }

        Set<Permissions> permissions = new HashSet<>();
        roles.forEach(role -> {
            List<RolePermissions> rolePermissions = rolePermissionsMapper.selectList(new QueryWrapper<RolePermissions>().eq("rid", role.getId()));
            List<Integer> permissionIdList = rolePermissions.stream().map(RolePermissions::getPid).toList();
            if (!permissionIdList.isEmpty()) {
                List<Permissions> permissionsList = permissionsMapper.selectBatchIds(permissionIdList);
                permissions.addAll(permissionsList);
            }
        });
        List<Permissions> permissionsList = permissions.stream().toList();

        log.info("查询用户（id：{}）的权限（{}）", userId, permissionsList);
        return permissionsList;
    }

    @Override
    public MySelfInfoVo getMyselfInfo(HttpServletRequest request) throws JsonProcessingException {
        // verifyToken 函数中自带盘空
        User loginUser = verifyToken(request);

        // 转为封装类返回
        MySelfInfoVo mySelfInfoVo = MySelfInfoVo.userToObj(loginUser);

        log.info("获取个人信息成功！");
        return mySelfInfoVo;
    }

    // 辅助方法：批量删除用户，日志记录
    private void logDeletedUsers(List<User> users) {
        String usernames = users.stream()
                .map(User::getUsername)
                .collect(Collectors.joining(", "));
        log.info("批量删除用户: {}", usernames);
    }

    /**
     * 验证用户 id 的有效性
     * @param userId
     * @return
     */
    public User verifyUserId(Integer userId) {
        Optional.ofNullable(userId)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        User verifyUser = this.getById(userId);
        Optional.ofNullable(verifyUser)
                .orElseThrow(() -> new BusinessException(ErrorType.USER_NOT_EXIST));
        return verifyUser;
    }

    /**
     * 验证 token
     * @param request
     * @return
     * @throws JsonProcessingException
     */
    public User verifyToken(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(ErrorType.TOKEN_NOT_EXIST);
        }
        User loginUser = JwtUtils.getUserFromToken(token);
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }
        return loginUser;
    }
}




