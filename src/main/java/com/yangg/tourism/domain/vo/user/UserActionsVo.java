package com.yangg.tourism.domain.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActionsVo {
    private String name;
    private String route;
    private String stayTime;
    private Integer times;
}
