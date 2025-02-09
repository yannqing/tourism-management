package com.yannqing.template.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yannqing.template.common.Code;
import com.yannqing.template.domain.dto.announcement.AddAnnouncementDto;
import com.yannqing.template.domain.dto.announcement.QueryAnnouncementDto;
import com.yannqing.template.domain.dto.announcement.UpdateAnnouncementDto;
import com.yannqing.template.domain.model.BaseResponse;
import com.yannqing.template.domain.vo.announcement.AnnouncementVo;
import com.yannqing.template.service.AnnouncementService;
import com.yannqing.template.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 公告管理
 * @author: yannqing
 * @create: 2025-02-09 11:13
 * @from: <更多资料：yannqing.com>
 **/
@RestController
@RequestMapping("/announcement")
@Tag(name = "公告管理")
public class AnnouncementController {

    @Resource
    private AnnouncementService announcementService;

    @Operation(summary = "查询所有公告")
    @GetMapping
    public BaseResponse<?> getAllAnnouncements(QueryAnnouncementDto queryAnnouncementDto) {
        Page<AnnouncementVo> announcementList = announcementService.getAllAnnouncements(queryAnnouncementDto);
        return ResultUtils.success(Code.SUCCESS, announcementList, "查询全部公告成功！");
    }

    @PutMapping
    @Operation(summary = "更新公告内容")
    public BaseResponse<?> updateAnnouncement(UpdateAnnouncementDto updateAnnouncementDto) {
        boolean result = announcementService.updateAnnouncement(updateAnnouncementDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改公告信息成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改公告信息失败！");
        }
    }

    @Operation(summary = "添加新公告")
    @PostMapping
    public BaseResponse<?> addAnnouncement(AddAnnouncementDto addAnnouncementDto) {
        boolean result = announcementService.addAnnouncement(addAnnouncementDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增公告成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增公告失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除单个公告")
    public BaseResponse<?> deleteAnnouncement(@PathVariable Integer id) {
        boolean result = announcementService.deleteAnnouncement(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除公告成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除公告失败！");
        }
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除公告信息")
    public BaseResponse<?> deleteBatchAnnouncement(Integer... announcementIds) {
        boolean result = announcementService.deleteBatchAnnouncement(announcementIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除公告成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除公告失败！");
        }
    }
}
