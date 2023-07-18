package me.wll.common.exception;

/**
 * 自定义异常
 */
public class CustomException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;

    public CustomException() {
        super();
    }
    
    public CustomException(String message) {
    	super(message);
    }
	
	public CustomException(Throwable cause) {
		super(cause);
	}
	
	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
