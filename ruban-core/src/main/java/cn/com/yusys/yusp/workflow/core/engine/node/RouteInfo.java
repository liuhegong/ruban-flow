package cn.com.yusys.yusp.workflow.core.engine.node;

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
	
	@XmlPath(valueAttr="@routeId")
	private String routeId;
	
	@XmlPath(valueAttr="@routeName")
	private String routeName;
	
	/**
	 * 当前节点
	 */
	@XmlPath(valueAttr="@nodeId")
	private String nodeId;
	
	/**
	 * 下一节点
	 */
	@XmlPath(valueAttr="@nextNodeId")
	private String nextNodeId;
	/**
	 * 是否往下获取节点
	 */
	@XmlPath(valueAttr="@isContinueBeanId")
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
	public String getIsContinueBeanId() {
		return isContinueBeanId;
	}
	public void setIsContinueBeanId(String isContinueBeanId) {
		this.isContinueBeanId = isContinueBeanId;
	}
	@Override
	public String toString() {
		return "RouteInfo [routeId=" + routeId + ", routeName=" + routeName + ", nodeId=" + nodeId + ", nextNodeId="
				+ nextNodeId + ", isContinueBeanId=" + isContinueBeanId + "]";
	}	
}
