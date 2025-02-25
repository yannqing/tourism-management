package com.yangg.tourism.domain.dto.tourist;

import com.yangg.tourism.domain.entity.TouristResources;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 更新旅游资源 dto
 * @author: yannqing
 * @create: 2025-02-08 17:44
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateTouristResourcesDto", description = "修改旅游资源对象")
public class UpdateTouristResourcesDto {
    /**
     * 资源id
     */
    @Schema(description = "资源id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 类型id
     */
    @Schema(description = "类型id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer typeId;

    /**
     * 资源名称
     */
    @Schema(description = "资源名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    /**
     * 描述
     */
    @Schema(description = "描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    /**
     * 地理位置
     */
    @Schema(description = "地理位置", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String location;

    /**
     * 评分
     */
    @Schema(description = "评分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal rating;

    /**
     * 价格
     */
    @Schema(description = "价格", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal price;

    /**
     * 状态
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String phone;

    /**
     * 宣传图片
     */
    @Schema(description = "宣传图片", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String images;

    /**
     * 开放时间
     */
    @Schema(description = "开放时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date beginTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date endTime;

    public static TouristResources objToTouristResources(UpdateTouristResourcesDto updateTouristResourcesDto) {
        if (updateTouristResourcesDto == null) {
            return null;
        }

        TouristResources touristResources = new TouristResources();
        BeanUtils.copyProperties(updateTouristResourcesDto, touristResources);
        return touristResources;
    }
}
