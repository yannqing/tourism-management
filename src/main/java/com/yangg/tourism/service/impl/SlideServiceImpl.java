package com.yangg.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangg.tourism.domain.dto.slide.AddSlideDto;
import com.yangg.tourism.domain.dto.slide.QuerySlideDto;
import com.yangg.tourism.domain.dto.slide.UpdateSlideDto;
import com.yangg.tourism.domain.entity.Slide;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import com.yangg.tourism.mapper.SlideMapper;
import com.yangg.tourism.service.SlideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
* @author yanqing
* @description 针对表【slide(轮播图)】的数据库操作Service实现
* @createDate 2025-03-01 16:48:23
*/
@Slf4j
@Service
public class SlideServiceImpl extends ServiceImpl<SlideMapper, Slide>
    implements SlideService{

    @Override
    public Page<Slide> getAllSlides(QuerySlideDto querySlideDto) {
        // 判空
        if (querySlideDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        Integer id = querySlideDto.getId();
        String title = querySlideDto.getTitle();
        String description = querySlideDto.getDescription();
        String image = querySlideDto.getImage();
        Integer tourismId = querySlideDto.getTourismId();
        Integer status = querySlideDto.getStatus();
        Integer type = querySlideDto.getType();

        QueryWrapper<Slide> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.eq(tourismId!= null, "tourismId", tourismId);
        queryWrapper.like(title!= null, "title", title);
        queryWrapper.like(image!= null, "image", image);
        queryWrapper.eq(status!= null, "status", status);
        queryWrapper.eq(type!= null, "type", type);
        queryWrapper.like(description!= null, "description", description);
        log.info("查询所有轮播图");

        return this.page(new Page<>(querySlideDto.getCurrent(), querySlideDto.getPageSize()), queryWrapper);
    }

    @Override
    public boolean updateSlide(UpdateSlideDto updateSlideDto) {
        // 判空
        Optional.ofNullable(updateSlideDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateSlideId = updateSlideDto.getId();
        Optional.ofNullable(this.getById(updateSlideId))
                .orElseThrow(() -> new BusinessException(ErrorType.SLIDE_NOT_EXIST));

        Slide updateSlide = UpdateSlideDto.objToSlide(updateSlideDto);

        boolean updateResult = this.updateById(updateSlide);
        log.info("更新轮播图");

        return updateResult;
    }

    @Override
    public boolean addSlide(AddSlideDto addSlideDto) {
        // 判空
        if (addSlideDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        Optional.ofNullable(addSlideDto.getTitle())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));
        Optional.ofNullable(addSlideDto.getImage())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 添加费用类型
        Slide addSlide = AddSlideDto.objToSlide(addSlideDto);
        boolean saveResult = this.save(addSlide);
        log.info("新增轮播图");

        return saveResult;
    }

    @Override
    public boolean deleteSlide(Integer id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BusinessException(ErrorType.SLIDE_NOT_EXIST));

        boolean deleteResult = this.removeById(id);
        log.info("删除轮播图 id：{}", id);

        return deleteResult;
    }

    @Override
    public boolean deleteBatchSlide(Integer... slideIds) {
        // 判空
        if (slideIds == null || slideIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        List<Slide> slideList = this.listByIds(Arrays.asList(slideIds));
        if (slideList.size()!= slideIds.length) {
            throw new BusinessException(ErrorType.SLIDE_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(slideIds));
        log.info("批量删除轮播图");

        return deleteResult > 0;
    }
}




