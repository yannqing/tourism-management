package com.yangg.tourism.domain.dto.slide;

import com.yangg.tourism.domain.entity.Slide;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description: 新增轮播图 dto
 * @author: yannqing
 * @create: 2025-02-08 18:10
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddSlideDto", description = "新增轮播图请求参数")
public class AddSlideDto implements Serializable {

    /**
     * 标题
     */
    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED)
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
    @Schema(description = "图片url", requiredMode = Schema.RequiredMode.REQUIRED)
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

    @Serial
    private static final long serialVersionUID = 1L;

    public static Slide objToSlide(AddSlideDto addSlideDto) {
        if (addSlideDto == null) {
            return null;
        }

        Slide slide = new Slide();
        BeanUtils.copyProperties(addSlideDto, slide);
        return slide;
    }
}
