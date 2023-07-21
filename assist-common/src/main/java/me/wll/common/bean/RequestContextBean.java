package me.wll.common.bean;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * 请求上下文
 */
@Component
@RequestScope
public class RequestContextBean {
    
    /**
     * 类名
     */
    private String typeName;
    
    /**
     * 内容
     */
    private Object requestBody;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Object getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}
    
}
