package cn.com.yusys.yusp.workflow.core.node;

import java.io.Serializable;

import com.figue.channel.transform.annotation.parse.xml.XmlPath;
/**
 * 路由基本信息
 * @author figue
 *
 */
@XmlPath("route")
public class RouteInfo implements Serializable{
	
	private static final long serialVersionUID = 100138765172147978L;
	private String routeId;
	private String routeName;
	
	/**
	 * 当前节点
	 */
	@XmlPath(value="property[@name='nodeid']",valueAttr="@value")
	private String nodeId;
	
	/**
	 * 下一节点
	 */
	@XmlPath(value="property[@name='noderouternodeid']",valueAttr="@value")
	private String nextNodeId;
	/**
	 * 是否往下获取节点
	 */
	private String isContinueBeanId;
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getIsContinueBeanId() {
		return isContinueBeanId;
	}
	public void setIsContinueBeanId(String isContinueBeanId) {
		this.isContinueBeanId = isContinueBeanId;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNextNodeId() {
		return nextNodeId;
	}
	public void setNextNodeId(String nextNodeId) {
		this.nextNodeId = nextNodeId;
	}
}
