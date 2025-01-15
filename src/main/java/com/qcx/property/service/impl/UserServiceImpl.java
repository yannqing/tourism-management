package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.domain.dto.user.AddUserDto;
import com.qcx.property.domain.dto.user.QueryUserRequest;
import com.qcx.property.domain.dto.user.UpdateMyInfoDto;
import com.qcx.property.domain.dto.user.UpdateUserDto;
import com.qcx.property.domain.entity.RoleUser;
import com.qcx.property.domain.entity.User;
import com.qcx.property.domain.vo.user.UserVo;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.enums.RoleType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.service.RoleUserService;
import com.qcx.property.service.UserService;
import com.qcx.property.mapper.UserMapper;
import com.qcx.property.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
        String nickName = Optional.ofNullable(addUserDto.getNickName())
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
        // 判空处理
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

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
        logDeletedRoles(users);

        return result;
    }

    /**
     * 查询单个用户
     * @param id
     * @return
     */
    @Override
    public UserVo getUserById(Integer id) {
        // 判空
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性检验
        User getUser = verifyUserId(id);

        // 返回封装类 vo
        return UserVo.objToVo(getUser);
    }

    /**
     * 查询所有用户
     * @param queryUserRequest
     * @return
     */
    @Override
    public Page<User> getAll(QueryUserRequest queryUserRequest) {
        // 判空
        Optional.ofNullable(queryUserRequest)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer userId = queryUserRequest.getUserId();
        String username = queryUserRequest.getUsername();
        String address = queryUserRequest.getAddress();
        String phone = queryUserRequest.getPhone();
        String email = queryUserRequest.getEmail();
        Integer age = queryUserRequest.getAge();
        String signature = queryUserRequest.getSignature();
        Integer sex = queryUserRequest.getSex();
        String nickName = queryUserRequest.getNickName();
        String description = queryUserRequest.getDescription();

        // userId 有效性判断
        verifyUserId(userId);

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
        return this.page(new Page<>(queryUserRequest.getCurrent(), queryUserRequest.getPageSize()), queryWrapper);
    }

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
        roleUserService.remove(new QueryWrapper<RoleUser>().eq("uid", userId));
        roleIds.forEach(roleId -> {
            roleUserService.addRole(userId, roleId);
        });
        log.info("更新用户（id：{}）的角色信息{}", userId, roleIds.stream().map(String::valueOf));

        return updateResult;
    }

    @Override
    public boolean updateMyInfo(UpdateMyInfoDto updateMyInfoDto, HttpServletRequest request) throws JsonProcessingException {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(ErrorType.TOKEN_NOT_EXIST);
        }
        User loginUser = JwtUtils.getUserFromToken(token);
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }

        // 更新用户信息
        User updateUserInfo = UpdateMyInfoDto.dtoToUser(updateMyInfoDto);
        boolean updateResult = this.updateById(updateUserInfo);
        log.info("更新个人信息（userId:{}）", loginUser.getUserId());

        return updateResult;
    }

    // 辅助方法：批量删除用户，日志记录
    private void logDeletedUsers(List<User> users) {
        String usernames = users.stream()
                .map(User::getUsername)
                .collect(Collectors.joining(", "));
        log.info("批量删除用户: {}", usernames);
    }

    // 辅助方法：记录已删除用户的角色
    private void logDeletedRoles(List<User> users) {
        String usernames = users.stream()
                .map(User::getUsername)
                .collect(Collectors.joining(", "));
        log.info("批量删除用户的角色: {}", usernames);
    }

    /**
     * 验证用户 id 的有效性
     * @param userId
     * @return
     */
    public User verifyUserId(Integer userId) {
        User verifyUser = this.getById(userId);
        Optional.ofNullable(verifyUser)
                .orElseThrow(() -> new BusinessException(ErrorType.USER_NOT_EXIST));
        return verifyUser;
    }
}




