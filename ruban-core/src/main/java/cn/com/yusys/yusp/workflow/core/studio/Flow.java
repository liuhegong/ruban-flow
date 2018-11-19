package cn.com.yusys.yusp.workflow.core.studio;

import java.io.Serializable;
import java.util.List;

import com.figue.channel.transform.annotation.parse.BeanClass;
import com.figue.channel.transform.annotation.parse.xml.XmlPath;
@XmlPath("mxGraphModel/root")
public class Flow implements Serializable{

	private static final long serialVersionUID = -7748714322856168218L;
	
	private String flowId;
	
	private String flowName;
	
	private String systemId;
	
	private String orgId;
	
	private String admin;

	public String getFlowId() {
		return flowId;
	}
	
	@BeanClass(Node.class)
	private List<Node> nodes;
	
	@BeanClass(Route.class)
	private List<Route> routes;
	

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

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}	
}
