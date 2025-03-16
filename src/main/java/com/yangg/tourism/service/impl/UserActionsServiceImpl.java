package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.entity.UserActions;
import com.yangg.tourism.domain.vo.user.UserActionsVo;
import com.yangg.tourism.mapper.UserActionsMapper;
import com.yangg.tourism.service.UserActionsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author yanqing
* @description 针对表【user_actions(用户行为分析表)】的数据库操作Service实现
* @createDate 2025-03-07 11:19:36
*/
@Service
public class UserActionsServiceImpl extends ServiceImpl<UserActionsMapper, UserActions>
    implements UserActionsService{

    @Override
    public List<UserActionsVo> getUserActions() {
        List<UserActions> userActions = baseMapper.selectList(null);

        return userActions.stream()
                .collect(Collectors.groupingBy(UserActions::getRoute, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    String route = entry.getKey();
                    List<UserActions> actions = entry.getValue();

                    int times = actions.size();
                    long totalStayTime = actions.stream()
                            .mapToLong(action -> {
                                try {
                                    return Long.parseLong(action.getStayTime());
                                } catch (NumberFormatException e) {
                                    return 0;
                                }
                            })
                            .sum();

                    UserActionsVo vo = new UserActionsVo();
                    vo.setRoute(route);
                    vo.setName(actions.get(0).getName()); // 取第一个相同 route 的 name
                    vo.setTimes(times);
                    vo.setStayTime(String.valueOf(totalStayTime));

                    return vo;
                })
                .collect(Collectors.toList());
    }
}




