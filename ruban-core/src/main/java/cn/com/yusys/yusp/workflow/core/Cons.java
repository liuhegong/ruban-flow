package cn.com.yusys.yusp.workflow.core;

public class Cons {
	public final static String ROBOT = "#robot#";
	public final static String SYSTEM = "#system#";
	
	/**
	 * 用户分割符
	 */
	public final static String SPLIT = ";"; 
	
	/**
	 * 流程结束标识，否决专用
	 */
	public final static String END_SIGN = "e"; 
	
	public final static String ERROR_MSG1 = "部分参数为空";
	public final static String ERROR_MSG2 = "流程已经办结";
	public final static String ERROR_MSG3 = "非此流程节点合法用户";
	public final static String ERROR_MSG4 = "找不到后续节点，检查流程图绘制";
	
	public final static String SUCCESS_MSG5 = "正常结束";
	public final static String ERROR_MSG6 = "节点无处理人，无法提交";
	public final static String SUCCESS_MSG7 = "提交完成";
	public final static String ERROR_MSG8 = "更新流程扩展参数失败";

	
	public final static String  DEV = "dev";
	public final static String  ORG = "org";	
	public final static String  PROD = "prod";
	
}
