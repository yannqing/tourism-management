package com.yangg.tourism.domain.dto.tourist;

import com.yangg.tourism.domain.entity.ResourcesType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @description: 更新旅游资源 dto
 * @author: yannqing
 * @create: 2025-02-08 17:44
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateResourcesTypeDto", description = "修改旅游资源类型对象")
public class UpdateResourcesTypeDto {

    /**
     * 资源类型id
     */
    @Schema(description = "资源类型id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 类型名称
     */
    @Schema(description = "类型名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    /**
     * 类型描述
     */
    @Schema(description = "类型描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    public static ResourcesType objToResourcesType(UpdateResourcesTypeDto updateResourcesTypeDto) {
        if (updateResourcesTypeDto == null) {
            return null;
        }

        ResourcesType resourcesType = new ResourcesType();
        BeanUtils.copyProperties(updateResourcesTypeDto, resourcesType);
        return resourcesType;
    }
}
