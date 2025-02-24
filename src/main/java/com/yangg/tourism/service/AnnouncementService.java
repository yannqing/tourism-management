package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.domain.dto.announcement.AddAnnouncementDto;
import com.yangg.tourism.domain.dto.announcement.QueryAnnouncementDto;
import com.yangg.tourism.domain.dto.announcement.UpdateAnnouncementDto;
import com.yangg.tourism.domain.entity.Announcement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yangg.tourism.domain.vo.announcement.AnnouncementVo;

/**
* @author 67121
* @description 针对表【announcement】的数据库操作Service
* @createDate 2025-02-08 17:11:24
*/
public interface AnnouncementService extends IService<Announcement> {

    Page<AnnouncementVo> getAllAnnouncements(QueryAnnouncementDto queryAnnouncementDto);

    boolean updateAnnouncement(UpdateAnnouncementDto updateAnnouncementDto);

    boolean addAnnouncement(AddAnnouncementDto addAnnouncementDto);

    boolean deleteAnnouncement(Integer id);

    boolean deleteBatchAnnouncement(Integer... announcementIds);
}
