package com.yangg.tourism.domain.vo.tourist;

import com.yangg.tourism.domain.entity.TouristResources;
import com.yangg.tourism.enums.ErrorType;
import com.yangg.tourism.exception.BusinessException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

@EqualsAndHashCode(callSuper = true)
@Data
public class TourismResourcesVo2 extends TouristResources {
    private Integer productTypeId;

    public static TourismResourcesVo2 touristResourcesToVo2(TouristResources touristResources) {
        if (touristResources == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        TourismResourcesVo2 tourismResourcesVo = new TourismResourcesVo2();
        BeanUtils.copyProperties(touristResources, tourismResourcesVo);
        return tourismResourcesVo;
    }
}
