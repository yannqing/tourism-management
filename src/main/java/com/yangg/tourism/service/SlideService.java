package com.yangg.tourism.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangg.tourism.domain.dto.slide.AddSlideDto;
import com.yangg.tourism.domain.dto.slide.QuerySlideDto;
import com.yangg.tourism.domain.dto.slide.UpdateSlideDto;
import com.yangg.tourism.domain.entity.Slide;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yanqing
* @description 针对表【slide(轮播图)】的数据库操作Service
* @createDate 2025-03-01 16:48:23
*/
public interface SlideService extends IService<Slide> {

    /**
     * 分页查询所有的轮播图
     * @param querySlideDto 查询 dto
     * @return 返回查询结果
     */
    Page<Slide> getAllSlides(QuerySlideDto querySlideDto);

    /**
     * 更新轮播图
     * @param updateSlideDto  更新dto
     * @return 返回更新结果
     */
    boolean updateSlide(UpdateSlideDto updateSlideDto);

    /**
     * 新增轮播图
     * @param addSlideDto 新增 dto
     * @return 返回新增结果
     */
    boolean addSlide(AddSlideDto addSlideDto);

    /**
     * 删除轮播图
     * @param id 要删除的id
     * @return 返回删除结果
     */
    boolean deleteSlide(Integer id);

    /**
     * 批量删除轮播图
     * @param slideIds 要删除的 id 数组
     * @return 返回删除结果
     */
    boolean deleteBatchSlide(Integer... slideIds);
}
