package com.yangg.tourism.domain.dto.tourist;

import com.yangg.tourism.domain.entity.ResourcesType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;

/**
 * @description: 新增旅游资源类型 dto
 * @author: yannqing
 * @create: 2025-02-08 18:10
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddResourcesTypeDto", description = "新增旅游资源类型请求参数")
public class AddResourcesTypeDto implements Serializable {

    /**
     * 类型名称
     */
    @Schema(description = "类型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    /**
     * 类型描述
     */
    @Schema(description = "类型描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Serial
    private static final long serialVersionUID = 1L;

    public static ResourcesType objToResourcesType(AddResourcesTypeDto addResourcesTypeDto) {
        if (addResourcesTypeDto == null) {
            return null;
        }

        ResourcesType resourcesType = new ResourcesType();
        BeanUtils.copyProperties(addResourcesTypeDto, resourcesType);
        return resourcesType;
    }
}
