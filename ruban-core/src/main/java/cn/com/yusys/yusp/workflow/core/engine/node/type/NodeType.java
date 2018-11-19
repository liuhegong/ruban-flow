package cn.com.yusys.yusp.workflow.core.engine.node.type;

public class NodeType {
	/**
	 * 普通节点
	 */
	public final static String BASE_NODE = "0";
	
	/**
	 * 单选节点
	 */
	public final static String RADIO_NODE = "1";
	
	/**
	 * 多选节点
	 */
	public final static String MULTI_NODE = "2";
	
	
	/**
	 * 条件单选节点
	 */
	public final static String CONDITION_RADIO_NODE = "3";

	
	/**
	 * 条件多选节点
	 */
	public final static String CONDITION_MULTI_NODE = "4";
	
	/**
	 * 自动运行节点
	 */
	public final static String AUTO_NODE = "5";
	
	/**
	 * 汇总节点
	 */
	public final static String TOGETHER_NODE = "C";
	
	/**
	 * 开始节点
	 */
	public final static String STRAT_NODE = "S";

	/**
	 * 结束节点
	 */
	public final static String END_NODE = "E";
	
}
