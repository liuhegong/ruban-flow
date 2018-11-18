package cn.com.yusys.yusp.workflow.core.exception;

public class WorkflowException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 *错误码
	 */
	private String code;

	/**
	 *提示
	 */
	private String message;

	public WorkflowException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.message = message;
	}
	
	public WorkflowException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}
	
	public WorkflowException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "WorkflowException [code=" + code + ", message=" + message + "]";
	}

}
