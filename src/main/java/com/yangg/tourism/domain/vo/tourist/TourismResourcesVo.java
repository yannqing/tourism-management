package com.yangg.tourism.domain.vo.tourist;

import com.yangg.tourism.domain.entity.ResourcesType;
import com.yangg.tourism.domain.entity.TouristResources;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;

/**
 * @description: 商户查询旅游资源返回封装类
 * @author: yannqing
 * @create: 2025-02-09 13:59
 * @from: <更多资料：yannqing.com>
 **/
@Data
public class TourismResourcesVo {

    /**
     * 资源id
     */
    private Integer id;

    /**
     * 父 id
     */
    private Integer pid;

    /**
     * 类型id
     */
    private ResourcesType type;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 地理位置
     */
    private String location;

    /**
     * 评分
     */
    private BigDecimal rating;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 状态（0草稿，1已发布/可用，2异常/维护中）
     */
    private Integer status;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 宣传图片
     */
    private String images;

    /**
     * 营业开启时间
     */
    private LocalTime openingTime;

    /**
     * 营业关闭时间
     */
    private LocalTime closingTime;

    /**
     * 开放时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    public static TourismResourcesVo touristResourcesToVo(TouristResources touristResources) {
        if (touristResources == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        TourismResourcesVo tourismResourcesVo = new TourismResourcesVo();
        BeanUtils.copyProperties(touristResources, tourismResourcesVo);
        return tourismResourcesVo;
    }
}
