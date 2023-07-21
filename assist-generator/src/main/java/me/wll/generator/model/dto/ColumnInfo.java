package me.wll.generator.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ColumnInfo {

    @ApiModelProperty(value = "数据库字段名称")
    private String columnName;

    @ApiModelProperty(value = "数据库字段类型")
    private String columnType;

    @ApiModelProperty(value = "数据库字段键类型")
    private String columnKey;
  
    @ApiModelProperty(value = "数据库字段描述")
    private String remark;
    @ApiModelProperty(value = "数据库字段描述")
    private Integer size=0;

    @ApiModelProperty(value = "是否必填")
    private Boolean notNull;
    
    @ApiModelProperty(value = "主键是否自增")
    private String auto;

    @ApiModelProperty(value = "查询 1:模糊 2：精确")
    private String queryType;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "日期注解")
    private String dateAnnotation;
}
