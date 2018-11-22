package cn.com.yusys.yusp.workflow.core.engine.node.type;

public class SignInType {
	
	/**
	 * 不需要签收
	 */
	public final static String NO_SIGN_IN = "0";
	
	/**
	 * 待签收
	 */
	public final static String TODO_SIGN_IN = "1";
	

	/**
	 * 已签收
	 */
	public final static String HAVE_SIGN_IN = "2";
	
	/**
	 * 排他
	 */
	public final static String EX_SIGN_IN = "3";
	
}
