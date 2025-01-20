package com.yannqing.template.domain.dto.user;

import com.yannqing.template.domain.entity.User;
import com.yannqing.template.enums.ErrorType;
import com.yannqing.template.exception.BusinessException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

/**
 * @description: 修改个人信息的 dto
 * @author: yannqing
 * @create: 2025-01-13 10:36
 * @from: <更多资料：yannqing.com>
 **/
@Schema(name = "UpdateMyInfoDto", description = "更新个人信息请求参数")
@Data
public class UpdateMyInfoDto {
    private String username;
    private String nickName;
    private String address;
    private String phone;
    private String email;
    private int age;
    private int sex;
    private String avatar;
    private String signature;
    private String description;

    public static User dtoToUser(UpdateMyInfoDto updateMyInfoDto) {
        Optional.ofNullable(updateMyInfoDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        User updpateUser = new User();
        BeanUtils.copyProperties(updateMyInfoDto, updpateUser);
        return updpateUser;
    }

}
