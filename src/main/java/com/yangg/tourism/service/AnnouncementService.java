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

    /**
     * 查询全部公告内容
     * @param queryAnnouncementDto 查询的请求参数 dto
     * @return 返回查询结果
     */
    Page<AnnouncementVo> getAllAnnouncements(QueryAnnouncementDto queryAnnouncementDto);

    /**
     * 更新公告内容
     * @param updateAnnouncementDto 更新的请求参数 dto
     * @return 返回更新的结果
     */
    boolean updateAnnouncement(UpdateAnnouncementDto updateAnnouncementDto);

    /**
     * 新增公告内容
     * @param addAnnouncementDto 新增的请求参数 dto
     * @return 返回新增的结果
     */
    boolean addAnnouncement(AddAnnouncementDto addAnnouncementDto);

    /**
     * 删除单个公告
     * @param id 要删除的 id
     * @return 返回删除的结果
     */
    boolean deleteAnnouncement(Integer id);

    /**
     * 批量删除公告
     * @param announcementIds 要删除的 id 数组
     * @return 返回删除的结果
     */
    boolean deleteBatchAnnouncement(Integer... announcementIds);
}
