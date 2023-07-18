package me.wll.generator.model.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenConfig {

	@ApiModelProperty(value = "数据库表的名称")
    private String tableName;
	
	@ApiModelProperty(value = "接口名称")
	private String apiAlias;

    @ApiModelProperty(value = "包路径")
    private String pack;

    @ApiModelProperty(value = "模块名")
    private String moduleName;


    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "表前缀")
    private String prefix;

    @ApiModelProperty(value = "是否覆盖")
    private Boolean cover = true;
    
    private List<ColumnInfo> columns;
}
