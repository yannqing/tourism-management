package com.yangg.tourism.domain.dto.slide;

import com.yangg.tourism.domain.entity.Slide;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @description: 更新轮播图 dto
 * @author: yannqing
 * @create: 2025-02-08 17:44
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateSlideDto", description = "修改轮播图对象")
public class UpdateSlideDto {

    /**
     * 主键id
     */
    @Schema(description = "主键id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 标题
     */
    @Schema(description = "标题", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String title;

    /**
     * 描述
     */
    @Schema(description = "描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    /**
     * 旅游资源id
     */
    @Schema(description = "旅游资源id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer tourismId;

    /**
     * 图片url
     */
    @Schema(description = "图片url", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String image;

    /**
     * 状态
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 类型
     */
    @Schema(description = "类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer type;

    public static Slide objToSlide(UpdateSlideDto updateSlideDto) {
        if (updateSlideDto == null) {
            return null;
        }

        Slide slide = new Slide();
        BeanUtils.copyProperties(updateSlideDto, slide);
        return slide;
    }
}
