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

    Page<Slide> getAllSlides(QuerySlideDto querySlideDto);

    boolean updateSlide(UpdateSlideDto updateSlideDto);

    boolean addSlide(AddSlideDto addSlideDto);

    boolean deleteSlide(Integer id);

    boolean deleteBatchSlide(Integer... slideIds);
}
