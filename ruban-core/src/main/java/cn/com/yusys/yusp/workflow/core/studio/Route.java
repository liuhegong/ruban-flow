package cn.com.yusys.yusp.workflow.core.studio;

import java.io.Serializable;

import com.figue.channel.transform.annotation.parse.xml.XmlPath;

@XmlPath("route")
public class Route implements Serializable {

	private static final long serialVersionUID = 2324865031160582358L;

	@XmlPath(valueAttr="@id")
	private String routeId;
	
	@XmlPath(valueAttr="@label")
	private String routeName;
	
	/**
	 * 当前节点
	 */
	@XmlPath(value="mxCell",valueAttr="@source")
	private String nodeId;
	
	/**
	 * 下一节点
	 */
	@XmlPath(value="mxCell",valueAttr="@target")
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
	
}
