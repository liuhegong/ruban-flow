package cn.com.yusys.yusp.workflow.core.engine.node.type;

public class HandleType {
	
	// 0.单人签收办理;1.单人竞争办理;2.多人顺序办理;3.多人并行办理;4.按转移条件;5.多人顺序可结束;6.多人并行可结束
	/**
	 * 单人签收办理
	 */
	public final static String ONE_SIGN = "0";
	
	/**
	 * 单人竞争办理
	 */
	public final static String ONE = "1";
	
	/**
	 * 多人顺序办理
	 */
	public final static String MORE_SX = "2";
	
	/**
	 * 多人并行办理
	 */
	public final static String MORE_BX = "3";
	
	/**
	 * 按转移条件
	 */
	public final static String CONDITION = "4";
	
	/**
	 * 多人顺序可结束
	 */
	public final static String MORE_SX_END = "5";
	
	/**
	 * 多人并行可结束
	 */
	public final static String MORE_BX_END = "6";
	
}
