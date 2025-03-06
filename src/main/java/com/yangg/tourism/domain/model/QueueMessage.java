package com.yangg.tourism.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueueMessage <T> {
    private T message;

    // 类型：1 insert, 2 update, 3 delete
    private Integer type;
}
