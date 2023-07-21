package me.wll.assi.model.dto;

import lombok.Data;
import me.wll.common.bean.PageInfo;

/**
* 
* @author wll
* @date 2023-07-19
**/
@Data
public class BBooksQuery extends PageInfo{
	/** 书籍名称 */
    private String keyword;
    /** 状态 */
    private String state;
}