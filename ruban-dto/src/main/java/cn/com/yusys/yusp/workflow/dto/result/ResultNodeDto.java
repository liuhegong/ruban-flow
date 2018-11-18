package cn.com.yusys.yusp.workflow.dto.result;

import java.io.Serializable;
import java.util.List;

import cn.com.yusys.yusp.workflow.dto.WFUserDto;

public class ResultNodeDto  implements Serializable {
    
    /**
     * 节点id
     */
    private String nodeId;

    /**
     * 节点标识
     */
    private String nodeSign;

    /**
     * 节点名称
     */
    private String nodeName;
    
    /**
	 * 是否委托
	 */
	private String entrust ;
	
	/**
	 * 节点类型
	 */
	private String nodeType;
	
	/**
	 * 节点处理人
	 */
	private List<WFUserDto> users;

    private static final long serialVersionUID = 1L;

    
    /**
     * 获取节点id
     *
     * @return NODE_ID - 节点id
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * 设置节点id
     *
     * @param nodeId 节点id
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }

    /**
     * 获取节点标识
     *
     * @return NODE_SIGN - 节点标识
     */
    public String getNodeSign() {
        return nodeSign;
    }

    /**
     * 设置节点标识
     *
     * @param nodeSign 节点标识
     */
    public void setNodeSign(String nodeSign) {
        this.nodeSign = nodeSign == null ? null : nodeSign.trim();
    }

    /**
     * 获取节点名称
     *
     * @return NODE_NAME - 节点名称
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * 设置节点名称
     *
     * @param nodeName 节点名称
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

	public String getEntrust() {
		return entrust;
	}

	public void setEntrust(String entrust) {
		this.entrust = entrust;
	}

	public List<WFUserDto> getUsers() {
		return users;
	}

	public void setUsers(List<WFUserDto> users) {
		this.users = users;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	@Override
	public String toString() {
		return "ResultNodeDto [nodeId=" + nodeId + ", nodeSign=" + nodeSign + ", nodeName=" + nodeName + ", entrust="
				+ entrust + ", nodeType=" + nodeType + ", users=" + users + "]";
	}

}