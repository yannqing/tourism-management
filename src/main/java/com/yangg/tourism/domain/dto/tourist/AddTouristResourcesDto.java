package com.yangg.tourism.domain.dto.tourist;

import com.yangg.tourism.domain.entity.TouristResources;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;

/**
 * @description: 新增旅游资源 dto
 * @author: yannqing
 * @create: 2025-02-08 18:10
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddTouristResourcesDto", description = "新增旅游资源请求参数")
public class AddTouristResourcesDto implements Serializable {

    /**
     * 父id
     */
    @Schema(description = "父id（默认0）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer pid;

    /**
     * 负责人 id
     */
    @Schema(description = "负责人 id（新增非商品的资源，必须指定 userId）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer userId;

    /**
     * 类型id
     */
    @Schema(description = "类型id（5是商品，其他为顶级/次顶级资源）", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer typeId;

    /**
     * 商品类型id
     */
    @Schema(description = "商品类型id（如果是商品，则必须指定类型）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer productTypeId;

    /**
     * 订单类型id
     */
    @Schema(description = "订单类型id（如果是商品，则必须指定类型）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer CostTypeId;

    /**
     * 资源名称
     */
    @Schema(description = "资源名称", requiredMode = Schema.RequiredMode.REQUIRED)
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
     * 营业开启时间
     */
    @Schema(description = "营业时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalTime openingTime;

    /**
     * 营业时间
     */
    @Schema(description = "营业关闭时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalTime closingTime;

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

    @Serial
    private static final long serialVersionUID = 1L;

    public static TouristResources objToTouristResources(AddTouristResourcesDto addTouristResourcesDto) {
        if (addTouristResourcesDto == null) {
            return null;
        }

        TouristResources touristResources = new TouristResources();
        BeanUtils.copyProperties(addTouristResourcesDto, touristResources);
        return touristResources;
    }
}
