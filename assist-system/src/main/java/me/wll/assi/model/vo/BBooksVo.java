package me.wll.assi.model.vo;

import lombok.Data;
import java.io.Serializable;

/**
* 书籍返回的数据
* @author wll
* @date 2023-07-19
**/
@Data
public class BBooksVo implements Serializable {

    private static final long serialVersionUID = 1L;

	/** 主键ID */
    private String id;

    /** 书籍名称 */
    private String title;
    /** 书籍名称 */
    private String tit;

    /** 顺序号 */
    private Integer sn;

    /** 新增时间 */
    private String newtime;

    /** 作者 */
    private String autor;

    /** 状态 */
    private String state;
}