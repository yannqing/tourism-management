package com.yannqing.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yannqing.template.domain.entity.Announcement;
import com.yannqing.template.service.AnnouncementService;
import com.yannqing.template.mapper.AnnouncementMapper;
import org.springframework.stereotype.Service;

/**
* @author 67121
* @description 针对表【announcement】的数据库操作Service实现
* @createDate 2025-02-08 17:11:24
*/
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement>
    implements AnnouncementService{

}




