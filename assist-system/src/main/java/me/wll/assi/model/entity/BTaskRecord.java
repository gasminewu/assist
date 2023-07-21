package me.wll.assi.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.wll.common.model.entity.IdEntity;

/**
* 
* @description 书籍
* @author wll
* @since 2023-07-19
**/
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="b_task_record")
public class BTaskRecord extends IdEntity {
    
    private static final long serialVersionUID = -7353513661319929347L;

	@Size(max = 100)
    @ApiModelProperty(value = "关联主键")
    private String relationid;
	
	@Size(max = 100)
	@ApiModelProperty(value = "关联主键，0 book，1 数学知识点")
	private String relationtype;
	
	@ApiModelProperty(value = "任务简述")
	private String tasktitle;
	@ApiModelProperty(value = "任务内容，描述")
	private String taskcontent;
	
	@ApiModelProperty(value = "任务时间（计划时间）")
	private String tasktime;
	
	@ApiModelProperty(value = "任务状态（0未开始，1已完成")
	private String state;
	
	@ApiModelProperty(value = "任务进度（百分比）")
	private String process;
	
	@ApiModelProperty(value = "标签")
	private String tag;
	
	@ApiModelProperty(value = "优先级（普通，较高，最高）")
	private String priority ;
	
	@ApiModelProperty(value = "完成时间")
	private String responsetime;
	
	@ApiModelProperty(value = "任务完成情况，完成描述")
	private String taskresponse;
}
