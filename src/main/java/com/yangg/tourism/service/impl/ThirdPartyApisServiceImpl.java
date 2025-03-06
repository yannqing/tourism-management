package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.entity.ThirdPartyApis;
import com.yangg.tourism.service.ThirdPartyApisService;
import com.yangg.tourism.mapper.ThirdPartyApisMapper;
import org.springframework.stereotype.Service;

/**
* @author yanqing
* @description 针对表【third_party_apis(第三方服务api调用统计表)】的数据库操作Service实现
* @createDate 2025-03-06 10:40:21
*/
@Service
public class ThirdPartyApisServiceImpl extends ServiceImpl<ThirdPartyApisMapper, ThirdPartyApis>
    implements ThirdPartyApisService{

}




