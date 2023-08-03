package me.wll.assi.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.wll.common.bean.PageInfo;

/**
* 
* @author wll
* @date 2023-07-19
**/
@EqualsAndHashCode(callSuper = true)
@Data
public class BbooksQuery extends PageInfo{
	/** 书籍名称 */
    private String keyword;
    /** 状态 */
    private String state;
}