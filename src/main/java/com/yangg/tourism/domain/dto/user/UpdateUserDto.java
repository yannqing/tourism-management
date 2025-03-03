package com.yangg.tourism.domain.dto.user;

import com.yangg.tourism.domain.entity.User;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

/**
 * @description: 修改用户信息的 dto（管理员）
 * @author: yannqing
 * @create: 2025-01-13 10:36
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateUserDto", description = "修改用户信息对象")
public class UpdateUserDto {
    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer userId;
    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String username;
    @Schema(description = "资源id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer tourismId;
    @Schema(description = "昵称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String nickName;
    @Schema(description = "地址", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String address;
    @Schema(description = "电话", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String phone;
    @Schema(description = "邮箱", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String email;
    @Schema(description = "头像", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String avatar;
    @Schema(description = "角色id数组", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<Integer> roleIds;
    @Schema(description = "是否可用", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer enabled;

    public static User dtoToUser(UpdateUserDto updateUserDto) {
        Optional.ofNullable(updateUserDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        User updpateUser = new User();
        BeanUtils.copyProperties(updateUserDto, updpateUser);
        return updpateUser;
    }
}
