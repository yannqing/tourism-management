package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.entity.UserTourist;
import com.yangg.tourism.service.UserTouristService;
import com.yangg.tourism.mapper.UserTouristMapper;
import org.springframework.stereotype.Service;

/**
* @author yanqing
* @description 针对表【user_tourist(商户与旅游资源关系表)】的数据库操作Service实现
* @createDate 2025-02-27 14:52:43
*/
@Service
public class UserTouristServiceImpl extends ServiceImpl<UserTouristMapper, UserTourist>
    implements UserTouristService{

}




