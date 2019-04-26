package com.deepexi.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@ToString(exclude = {"COM_ID"})
public class Components implements Serializable {

    private Integer COM_ID;          // 主键ID
    private String COM_NAME;         // 组件名称
    private String COM_TYPE;         // 分类
    private String COM_VERSION;      // 版本
    private String COM_STATUS;       // 状态

}
