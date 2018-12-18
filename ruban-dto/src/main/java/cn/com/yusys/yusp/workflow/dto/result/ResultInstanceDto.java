package cn.com.yusys.yusp.workflow.dto.result;

import java.io.Serializable;
import java.util.Map;
/**
 * 实例信息，不含办里人员
 * @author figue
 *
 */
public class ResultInstanceDto  implements Serializable {
    
	/**
	 * 提交节点id
	 */
	private String from;
	
	/**
	 * 提交到的节点id
	 */
	private String to;
	
	/**
	 * 提交到的节点是否是初始节点
	 */
	private boolean isFirst;
	
	/**
	 * 当前操作类型【提交，打回，退回等】，业务后处理时设置
	 */
	private String currentOpType;
	/**
     * 流程实例id
     */
    private String instanceId;

    /**
     * 流程名称
     */
    private String flowName;

    /**
     * 流程id
     */
    private String flowId;

    /**
     * 流程管理员
     */
    private String flowAdmin;

    /**
     * 流程发起者
     */
    private String flowStarter;
    
    /**
     * 流程发起者
     */
    private String flowStarterName;

    /**
     * 流程发起时间
     */
    private String startTime;

    /**
     * 系统id
     */
    private String systemId;

    /**
     * 发起人机构id
     */
    private String orgId;

    /**
     * 流程状态
     */
    private String flowState;

    /**
     * 业务流水号
     */
    private String bizId;
    
    /**
     * 业务页面
     */
    private String bizPage;

    /**
     * 客户名称
     */
    private String bizUserName;

    /**
     * 客户id
     */
    private String bizUserId;

    /**
     * 流程参数
     */
    private String flowParam;
    
    private String lastNodeId;
    
    private String lastNodeName;
    
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
     * 节点状态
     */
    private String nodeState;
    
    /**
     *操作权限
     */
    private ResultOpTypeDto opType;
    
    /**
     * 评论
     */
    private ResultCommentDto comment;
    
    /**
     * 节点类型
     */
    private String nodeType;
    
    /**
     * 办理类型
     */
    private String handleType;
    
    /**
     * 流程公共参数
     */
    private Map<String,Object> param;

    private static final long serialVersionUID = 1L;

    /**
     * 获取流程实例id
     *
     * @return INSTANCE_ID - 流程实例id
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * 设置流程实例id
     *
     * @param instanceId 流程实例id
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId == null ? null : instanceId.trim();
    }

    /**
     * 获取流程名称
     *
     * @return FLOW_NAME - 流程名称
     */
    public String getFlowName() {
        return flowName;
    }

    /**
     * 设置流程名称
     *
     * @param flowName 流程名称
     */
    public void setFlowName(String flowName) {
        this.flowName = flowName == null ? null : flowName.trim();
    }

    /**
     * 获取流程id
     *
     * @return FLOW_ID - 流程id
     */
    public String getFlowId() {
        return flowId;
    }

    /**
     * 设置流程id
     *
     * @param flowId 流程id
     */
    public void setFlowId(String flowId) {
        this.flowId = flowId == null ? null : flowId.trim();
    }

    /**
     * 获取流程管理员
     *
     * @return FLOW_ADMIN - 流程管理员
     */
    public String getFlowAdmin() {
        return flowAdmin;
    }

    /**
     * 设置流程管理员
     *
     * @param flowAdmin 流程管理员
     */
    public void setFlowAdmin(String flowAdmin) {
        this.flowAdmin = flowAdmin == null ? null : flowAdmin.trim();
    }

    /**
     * 获取流程发起者
     *
     * @return FLOW_STARTER - 流程发起者
     */
    public String getFlowStarter() {
        return flowStarter;
    }

    /**
     * 设置流程发起者
     *
     * @param flowStarter 流程发起者
     */
    public void setFlowStarter(String flowStarter) {
        this.flowStarter = flowStarter == null ? null : flowStarter.trim();
    }

    /**
     * 获取流程发起时间
     *
     * @return START_TIME - 流程发起时间
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 设置流程发起时间
     *
     * @param startTime 流程发起时间
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    /**
     * 获取系统id
     *
     * @return SYSTEM_ID - 系统id
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * 设置系统id
     *
     * @param systemId 系统id
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    /**
     * 获取发起人机构id
     *
     * @return ORG_ID - 发起人机构id
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置发起人机构id
     *
     * @param orgId 发起人机构id
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * 获取流程状态
     *
     * @return FLOW_STATE - 流程状态
     */
    public String getFlowState() {
        return flowState;
    }

    /**
     * 设置流程状态
     *
     * @param flowState 流程状态
     */
    public void setFlowState(String flowState) {
        this.flowState = flowState == null ? null : flowState.trim();
    }

    /**
     * 获取业务流水号
     *
     * @return BIZ_ID - 业务流水号
     */
    public String getBizId() {
        return bizId;
    }

    /**
     * 设置业务流水号
     *
     * @param bizId 业务流水号
     */
    public void setBizId(String bizId) {
        this.bizId = bizId == null ? null : bizId.trim();
    }

    /**
     * 获取客户名称
     *
     * @return BIZ_USER_NAME - 客户名称
     */
    public String getBizUserName() {
        return bizUserName;
    }

    /**
     * 设置客户名称
     *
     * @param bizUserName 客户名称
     */
    public void setBizUserName(String bizUserName) {
        this.bizUserName = bizUserName == null ? null : bizUserName.trim();
    }

    /**
     * 获取客户id
     *
     * @return BIZ_USER_ID - 客户id
     */
    public String getBizUserId() {
        return bizUserId;
    }

    /**
     * 设置客户id
     *
     * @param bizUserId 客户id
     */
    public void setBizUserId(String bizUserId) {
        this.bizUserId = bizUserId == null ? null : bizUserId.trim();
    }

    /**
     * 获取流程参数
     *
     * @return FLOW_PARAM - 流程参数
     */
    public String getFlowParam() {
        return flowParam;
    }

    /**
     * 设置流程参数
     *
     * @param flowParam 流程参数
     */
    public void setFlowParam(String flowParam) {
        this.flowParam = flowParam == null ? null : flowParam.trim();
    }


	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeSign() {
		return nodeSign;
	}

	public void setNodeSign(String nodeSign) {
		this.nodeSign = nodeSign;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeState() {
		return nodeState;
	}

	public void setNodeState(String nodeState) {
		this.nodeState = nodeState;
	}

	public ResultOpTypeDto getOpType() {
		return opType;
	}

	public void setOpType(ResultOpTypeDto opType) {
		this.opType = opType;
	}

	public ResultCommentDto getComment() {
		return comment;
	}

	public void setComment(ResultCommentDto comment) {
		this.comment = comment;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public String getLastNodeId() {
		return lastNodeId;
	}

	public void setLastNodeId(String lastNodeId) {
		this.lastNodeId = lastNodeId;
	}

	public String getLastNodeName() {
		return lastNodeName;
	}

	public void setLastNodeName(String lastNodeName) {
		this.lastNodeName = lastNodeName;
	}

	public String getFlowStarterName() {
		return flowStarterName;
	}

	public void setFlowStarterName(String flowStarterName) {
		this.flowStarterName = flowStarterName;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public String getBizPage() {
		return bizPage;
	}

	public void setBizPage(String bizPage) {
		this.bizPage = bizPage;
	}

	public String getCurrentOpType() {
		return currentOpType;
	}

	public void setCurrentOpType(String currentOpType) {
		this.currentOpType = currentOpType;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	@Override
	public String toString() {
		return "ResultInstanceDto [from=" + from + ", to=" + to + ", isFirst=" + isFirst + ", currentOpType="
				+ currentOpType + ", instanceId=" + instanceId + ", flowName=" + flowName + ", flowId=" + flowId
				+ ", flowAdmin=" + flowAdmin + ", flowStarter=" + flowStarter + ", flowStarterName=" + flowStarterName
				+ ", startTime=" + startTime + ", systemId=" + systemId + ", orgId=" + orgId + ", flowState="
				+ flowState + ", bizId=" + bizId + ", bizPage=" + bizPage + ", bizUserName=" + bizUserName
				+ ", bizUserId=" + bizUserId + ", flowParam=" + flowParam + ", lastNodeId=" + lastNodeId
				+ ", lastNodeName=" + lastNodeName + ", nodeId=" + nodeId + ", nodeSign=" + nodeSign + ", nodeName="
				+ nodeName + ", nodeState=" + nodeState + ", opType=" + opType + ", comment=" + comment + ", nodeType="
				+ nodeType + ", handleType=" + handleType + ", param=" + param + "]";
	}


}