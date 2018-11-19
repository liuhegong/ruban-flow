package cn.com.yusys.yusp.workflow.core.engine.node.type;

public class HandleType {
	/**
	 * 多人时竞争办理(默认)
	 */
	public final static String ONE = "0";
	
	/**
	 * 多人时顺序办理
	 */
	public final static String MORE = "1";
	
	/**
	 * 多人时顺序办理可条件结束
	 */
	public final static String MORE_END = "2";
	
	// 0.单人签收办理;1.单人竞争办理;2.多人顺序办理;3.多人并行办理;4.按转移条件;5.多人顺序可结束;6.多人并行可结束
}
