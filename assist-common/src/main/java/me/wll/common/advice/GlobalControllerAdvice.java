package me.wll.common.advice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;

import me.wll.common.bean.RequestContextBean;
import me.wll.common.bean.ResultBean;
import me.wll.common.exception.CustomException;
import me.wll.common.utils.JacksonUtil;
/**
 * 统一异常处理
 */
@Order(1)
@RestControllerAdvice
public class GlobalControllerAdvice implements RequestBodyAdvice {
    
	Logger log = LoggerFactory.getLogger(GlobalControllerAdvice.class);
	
	String wiexception_pre = "com.wisoft.hamigua.common.exception.WiException: ";
	
    @Autowired
    private RequestContextBean requestContextBean;
    
    @ExceptionHandler({CustomException.class})
    public ResultBean<?> processCustomException(CustomException e) {
		log.error(e.getMessage(), e);
		e.printStackTrace();
        return new ResultBean<>(ResultBean.UNKNOWN_EXCEPTION, false, e.getMessage());
    }
    
    @ExceptionHandler({ Exception.class })
    public ResultBean<?> processException(WebRequest request, Exception e) {
		log.error(getRequestJson(request), e);
        String message;
        Integer statusCode;
        // 参数缺失
        if (e instanceof MissingServletRequestParameterException
                || e instanceof HttpMessageNotReadableException) {
            //message = "required parameter is not present";
            message = "请求参数缺失：" + e.getMessage();
            statusCode = ResultBean.CHECK_FAIL;
        }
        // 参数验证错误
        else if (e instanceof MethodArgumentNotValidException
                || e instanceof ConstraintViolationException||e instanceof BindException) {
            //message = "method argument not valid";
            message = "请求参数验证错误："+e.getMessage();
            statusCode = ResultBean.CHECK_FAIL;
        } 
        // 自定义错误
        else if (e instanceof CustomException) {
            message = e.getMessage();
            statusCode = ResultBean.CHECK_FAIL;
        } 
        // 系统错误
        else {
            //message = "server error";
            message = "系统错误";
            statusCode = ResultBean.UNKNOWN_EXCEPTION;
        }

        return new ResultBean<>(statusCode, false, message);
    }

    /**
     * 
     * 	获取请求信息JSON
     * 
     * @param request
     * @return
     * @throws JsonProcessingException 
     *
     * @变更记录 2019年10月30日 上午9:43:15 libing@wisoft.com.cn 创建
     *
     */
    private String getRequestJson(WebRequest request) {
    	Map<String, Object> parameterMap = new HashMap<>();
    	parameterMap.putAll(request.getParameterMap());
    	if (requestContextBean.getTypeName()!=null) {
    		parameterMap.put(requestContextBean.getTypeName(), requestContextBean.getRequestBody());
    	}
        
    	Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("parameters", parameterMap);
        requestMap.put("request", request.toString());
        
        return JacksonUtil.mapToJson(requestMap);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        requestContextBean.setTypeName(targetType.getTypeName());
        requestContextBean.setRequestBody(body);
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
            Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBean<?> illegalParamsExceptionHandler(MethodArgumentNotValidException e) {
    	log.error(e.getMessage(), e);
        Integer statusCode = ResultBean.CHECK_FAIL;
        return new ResultBean<>(statusCode, false, validation(e.getBindingResult()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultBean<?> illegalStateException(HttpMessageNotReadableException e) {
    	log.error(e.getMessage(), e);
        Integer statusCode = ResultBean.CHECK_FAIL;
        return new ResultBean<>(statusCode, false, "参数错误");
    }

    @ExceptionHandler(BindException.class)
    public ResultBean<?> illegalStateException(BindException e) {
    	log.error(e.getMessage(), e);
        Integer statusCode = ResultBean.CHECK_FAIL;
        return new ResultBean<>(statusCode, false, validation(e.getBindingResult()));
    }

    private String validation(BindingResult bindingResult) {
        List<ObjectError> errors = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder("");
        if (!CollectionUtils.isEmpty(errors)) {
            for (ObjectError error : errors) {
                FieldError fieldError = (FieldError) error;
                sb.append(fieldError.getField() + fieldError.getDefaultMessage() + ",");
            }
        }
        return sb.length()>0?sb.substring(0,sb.length()-1):sb.toString();
    }
    
}
