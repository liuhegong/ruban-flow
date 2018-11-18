package cn.com.yusys.yusp.workflow.core.node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.figue.channel.transform.annotation.parse.BeanClass;
import com.figue.channel.transform.annotation.parse.xml.XmlPath;
/**
 * 节点基本信息，节点下可以有路由
 * @author figue
 *
 */
@XmlPath("node")
public class NodeInfo implements Serializable{
	
	private static final long serialVersionUID = -6366130858323357070L;
	@XmlPath(valueAttr="@id")
	private String nodeId;
	
	@XmlPath(valueAttr="@name")
	private String nodeName;
	
	/**
	 * 节点类型
	 */
	@XmlPath(valueAttr="@type")
	private String nodeType;
	
	/**
	 * 节点标识
	 */
	@XmlPath(value="property[@name='nodesign']",valueAttr="@value")
	private String nodeSign;

	
	/**
	 * 办理类型
	 */
	@XmlPath(value="property[@name='nodetransacttype']",valueAttr="@value")
	private String handleType;
	
	/**
	 * 节点类型为【多人顺序办理可条件结束】时才生效
	 */
	private String isEndBeanId;

	/**
	 *  运行到此节点业务后处理
	 */
	@XmlPath(value="property[@name='noderunscript']",valueAttr="@value")
	private String bizBeanId;
	
	/**
	 * 节点处理人
	 */
	@XmlPath(value="property[@name='nodeuserslist']",valueAttr="@value")
	private String nodeUser;
	
	/**
	 * 办理人员如何指定
	 */
	@XmlPath(value="property[@name='nodeusersassign']",valueAttr="@value")
	private String opUsersType;
	
	/**
	 * 人员如何计算
	 */
	@XmlPath(value="property[@name='nodeuserscompute']",valueAttr="@value")
	private String computeType;
	
	/**
	 * 重办人员选择
	 */
	@XmlPath(value="property[@name='repeatersign']",valueAttr="@value")
	private String reDoUserSelect;
	
	/**
	 * 路由信息
	 */
	@BeanClass(RouteInfo.class)
	private List<RouteInfo> routeInfos;
	
	/**
	 * 拿回
	 */
	@XmlPath(value="property[@name='noderecallset']",valueAttr="@value")
	private String tackBack;
	/**
	 * 打回
	 */
	@XmlPath(value="property[@name='nodecallbackset']",valueAttr="@value")
	private String callBack ;
	/**
	 * 退回
	 */
	@XmlPath(value="property[@name='nodereturnbackset']",valueAttr="@value")
	private String returnBack ;
	
	
	/**
	 * 催办
	 */
	@XmlPath(value="property[@name='nodeurgeset']",valueAttr="@value")
	private String urged ;
	/**
	 * 转办
	 */
	@XmlPath(value="property[@name='nodechangeset']",valueAttr="@value")
	private String change ;
	/**
	 * 协办
	 */
	@XmlPath(value="property[@name='nodeassist']",valueAttr="@value")
	private String assist ;
	/**
	 * 否决
	 */
	@XmlPath(value="property[@name='$ext_canexe20']",valueAttr="@value")
	private String refuse ;
	/**
	 * 跳转
	 */
	@XmlPath(value="property[@name='nodejumpset']",valueAttr="@value")
	private String jump  ;
	
	/**
	 * 委托
	 */
	//@XmlPath(value="property[@name='entrust']",valueAttr="@value")
	private String entrust ;
	
	/**
	 * 委托人员
	 */
	//@XmlPath(value="property[@name='entrustusers']",valueAttr="@value")
	private String entrustUsers;
	
	/**
	 * 抄送
	 */
	@XmlPath(value="property[@name='nodeannounceset']",valueAttr="@value")
	private String copy ;
	
	/**
	 * 抄送人员，允许【抄送】时才生效
	 */
	@XmlPath(value="property[@name='nodeannounceuser']",valueAttr="@value")
	private String copyUsers;
	
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public String getIsEndBeanId() {
		return isEndBeanId;
	}
	public void setIsEndBeanId(String isEndBeanId) {
		this.isEndBeanId = isEndBeanId;
	}
	public String getBizBeanId() {
		return bizBeanId;
	}
	public void setBizBeanId(String bizBeanId) {
		this.bizBeanId = bizBeanId;
	}
	
	public List<RouteInfo> getRouteInfos() {
		return routeInfos;
	}
	public void setRouteInfos(List<RouteInfo> routeInfos) {
		this.routeInfos = routeInfos;
	}
	public String getHandleType() {
		return handleType;
	}
	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}
	public String getNodeSign() {
		return nodeSign;
	}
	public void setNodeSign(String nodeSign) {
		this.nodeSign = nodeSign;
	}
	
	public String getNodeUser() {
		return nodeUser;
	}
	public void setNodeUser(String nodeUser) {
		this.nodeUser = nodeUser;
	}
	public String getOpUsersType() {
		return opUsersType;
	}
	public void setOpUsersType(String opUsersType) {
		this.opUsersType = opUsersType;
	}
	public String getComputeType() {
		return computeType;
	}
	public void setComputeType(String computeType) {
		this.computeType = computeType;
	}
	public String getReDoUserSelect() {
		return reDoUserSelect;
	}
	public void setReDoUserSelect(String reDoUserSelect) {
		this.reDoUserSelect = reDoUserSelect;
	}
	public String getCopyUsers() {
		return copyUsers;
	}
	public void setCopyUsers(String copyUsers) {
		this.copyUsers = copyUsers;
	}
	
	
	
	public String getTackBack() {
		return tackBack;
	}
	public void setTackBack(String tackBack) {
		this.tackBack = tackBack;
	}
	public String getCallBack() {
		return callBack;
	}
	public void setCallBack(String callBack) {
		this.callBack = callBack;
	}
	public String getReturnBack() {
		return returnBack;
	}
	public void setReturnBack(String returnBack) {
		this.returnBack = returnBack;
	}
	
	public String getUrged() {
		return urged;
	}
	public void setUrged(String urged) {
		this.urged = urged;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getAssist() {
		return assist;
	}
	public void setAssist(String assist) {
		this.assist = assist;
	}
	public String getRefuse() {
		return refuse;
	}
	public void setRefuse(String refuse) {
		this.refuse = refuse;
	}
	public String getJump() {
		return jump;
	}
	public void setJump(String jump) {
		this.jump = jump;
	}
	public String getEntrust() {
		return entrust;
	}
	public void setEntrust(String entrust) {
		this.entrust = entrust;
	}
	public String getCopy() {
		return copy;
	}
	public void setCopy(String copy) {
		this.copy = copy;
	}
	
	public String getEntrustUsers() {
		return entrustUsers;
	}
	public void setEntrustUsers(String entrustUsers) {
		this.entrustUsers = entrustUsers;
	}
	public List<String> getNextNodes(){
		List<String> nodeIds = new ArrayList<String>();
		if(null!=routeInfos){
			for(RouteInfo route:routeInfos){
				nodeIds.add(route.getNextNodeId());
			}
		}
		return nodeIds;
	}
}
