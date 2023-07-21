package me.wll.common.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *	返回对象
 */
@JsonInclude(Include.NON_NULL)
@ApiModel("ResultBean 返回对象")
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 200; // 成功

    public static final int CHECK_FAIL = 400; // 参数错误

    public static final int NO_LOGIN = 401; // 未登录

    public static final int NO_PERMISSION = 403; // 禁止

    public static final int UNKNOWN_EXCEPTION = 500;
    
	@ApiModelProperty(value="执行成功标识")
    private Boolean success = true;
	@ApiModelProperty(value="状态码")
    private Integer statusCode = SUCCESS;
	@ApiModelProperty(value="提示信息")
    private String msg = "success";
	@ApiModelProperty(value="返回数据")
    private T data;
    
    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.setData(data);
    }
    
    public ResultBean(String msg, T data) {
    	this.msg = msg;
    	this.setData(data);
    }

    public ResultBean(Integer statusCode, String msg) {
        super();
        this.statusCode = statusCode;
        this.msg = msg;
    }
    
    public ResultBean(Integer statusCode, Boolean success, String msg) {
        super();
        this.statusCode = statusCode;
        this.success = success;
        this.msg = msg;
    }

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
        this.data = data;
	}

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
