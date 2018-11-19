package cn.com.yusys.yusp.workflow.core.engine.flow;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.figue.channel.transform.annotation.parse.BeanClass;
import com.figue.channel.transform.annotation.parse.xml.XmlPath;

import cn.com.yusys.yusp.workflow.core.engine.node.NodeInfo;
import cn.com.yusys.yusp.workflow.core.engine.node.type.NodeType;

/**
 * 流程基本信息，流程由多个节点组成
 * @author figue
 *
 */
@XmlPath("wf")
public class FlowInfo implements Serializable{
	
	private static final long serialVersionUID = -6070493386935907542L;
	
	private static final Log log = LogFactory.getLog(FlowInfo.class);
	
	@XmlPath(valueAttr="@id")
	private String flowId;
	
	@XmlPath(valueAttr="@name")
	private String flowName;
	
	/**
	 * 所属系统id
	 */
	@XmlPath(valueAttr="@systemId")
	private String systemId;
	
	/**
	 * 所属机构id
	 */
	@XmlPath(valueAttr="@orgId")
	private String orgId;
	
	/**
	 * 管理员
	 */
	@XmlPath(valueAttr="@admin")
	private String admin;
	
	
	@BeanClass(NodeInfo.class)
	private List<NodeInfo> nodes;

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public List<NodeInfo> getNodes() {
		return nodes;
	}

	public void setNodes(List<NodeInfo> nodes) {
		this.nodes = nodes;
	}
	
	public NodeInfo getStartNode(){
		if(null!=nodes&&!nodes.isEmpty()){
			for(NodeInfo node:nodes){
				if(node.getNodeType().equals(NodeType.STRAT_NODE)){
					return node;
				}
			}
		}
		log.error(flowName+"未找到开始节点");
		return null;
	}
	
	public NodeInfo getNodeInfo(String nodeId){
		if(null!=nodes&&!nodes.isEmpty()){
			for(NodeInfo node:nodes){
				if(node.getNodeId().equals(nodeId)){
					return node;
				}
			}
		}
		log.error(flowName+"未找到节点["+nodeId+"]");
		return null;
	}
}
