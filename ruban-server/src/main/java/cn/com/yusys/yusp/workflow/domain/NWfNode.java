package cn.com.yusys.yusp.workflow.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "N_WF_NODE")
public class NWfNode implements Serializable {
    /**
     * 实例id
     */
    @Id
    @Column(name = "INSTANCE_ID")
    private String instanceId;

    /**
     * 节点id
     */
    @Id
    @Column(name = "NODE_ID")
    private String nodeId;

    /**
     * 节点标识
     */
    @Column(name = "NODE_SIGN")
    private String nodeSign;

    /**
     * 节点名称
     */
    @Column(name = "NODE_NAME")
    private String nodeName;

    /**
     * 节点状态
     */
    @Column(name = "NODE_STATE")
    private String nodeState;

    /**
     * 节点开始时间
     */
    @Column(name = "START_TIME")
    private String startTime;

    /**
     * 提交人机构id
     */
    @Column(name = "ORG_ID")
    private String orgId;

    /**
     * 上一节点id
     */
    @Column(name = "LAST_NODE_ID")
    private String lastNodeId;

    /**
     * 上一节点名称
     */
    @Column(name = "LAST_NODE_NAME")
    private String lastNodeName;

    /**
     * 流经的节点等级之和
     */
    @Column(name = "NODE_LEVEL_TOTAL")
    private Long nodeLevelTotal;

    private static final long serialVersionUID = 1L;

    /**
     * 获取实例id
     *
     * @return INSTANCE_ID - 实例id
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * 设置实例id
     *
     * @param instanceId 实例id
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId == null ? null : instanceId.trim();
    }

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

    /**
     * 获取节点状态
     *
     * @return NODE_STATE - 节点状态
     */
    public String getNodeState() {
        return nodeState;
    }

    /**
     * 设置节点状态
     *
     * @param nodeState 节点状态
     */
    public void setNodeState(String nodeState) {
        this.nodeState = nodeState == null ? null : nodeState.trim();
    }

    /**
     * 获取节点开始时间
     *
     * @return START_TIME - 节点开始时间
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 设置节点开始时间
     *
     * @param startTime 节点开始时间
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    /**
     * 获取提交人机构id
     *
     * @return ORG_ID - 提交人机构id
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置提交人机构id
     *
     * @param orgId 提交人机构id
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * 获取上一节点id
     *
     * @return LAST_NODE_ID - 上一节点id
     */
    public String getLastNodeId() {
        return lastNodeId;
    }

    /**
     * 设置上一节点id
     *
     * @param lastNodeId 上一节点id
     */
    public void setLastNodeId(String lastNodeId) {
        this.lastNodeId = lastNodeId == null ? null : lastNodeId.trim();
    }

    /**
     * 获取上一节点名称
     *
     * @return LAST_NODE_NAME - 上一节点名称
     */
    public String getLastNodeName() {
        return lastNodeName;
    }

    /**
     * 设置上一节点名称
     *
     * @param lastNodeName 上一节点名称
     */
    public void setLastNodeName(String lastNodeName) {
        this.lastNodeName = lastNodeName == null ? null : lastNodeName.trim();
    }

    /**
     * 获取流经的节点等级之和
     *
     * @return NODE_LEVEL_TOTAL - 流经的节点等级之和
     */
    public Long getNodeLevelTotal() {
        return nodeLevelTotal;
    }

    /**
     * 设置流经的节点等级之和
     *
     * @param nodeLevelTotal 流经的节点等级之和
     */
    public void setNodeLevelTotal(Long nodeLevelTotal) {
        this.nodeLevelTotal = nodeLevelTotal;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        NWfNode other = (NWfNode) that;
        return (this.getInstanceId() == null ? other.getInstanceId() == null : this.getInstanceId().equals(other.getInstanceId()))
            && (this.getNodeId() == null ? other.getNodeId() == null : this.getNodeId().equals(other.getNodeId()))
            && (this.getNodeSign() == null ? other.getNodeSign() == null : this.getNodeSign().equals(other.getNodeSign()))
            && (this.getNodeName() == null ? other.getNodeName() == null : this.getNodeName().equals(other.getNodeName()))
            && (this.getNodeState() == null ? other.getNodeState() == null : this.getNodeState().equals(other.getNodeState()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getLastNodeId() == null ? other.getLastNodeId() == null : this.getLastNodeId().equals(other.getLastNodeId()))
            && (this.getLastNodeName() == null ? other.getLastNodeName() == null : this.getLastNodeName().equals(other.getLastNodeName()))
            && (this.getNodeLevelTotal() == null ? other.getNodeLevelTotal() == null : this.getNodeLevelTotal().equals(other.getNodeLevelTotal()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInstanceId() == null) ? 0 : getInstanceId().hashCode());
        result = prime * result + ((getNodeId() == null) ? 0 : getNodeId().hashCode());
        result = prime * result + ((getNodeSign() == null) ? 0 : getNodeSign().hashCode());
        result = prime * result + ((getNodeName() == null) ? 0 : getNodeName().hashCode());
        result = prime * result + ((getNodeState() == null) ? 0 : getNodeState().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getLastNodeId() == null) ? 0 : getLastNodeId().hashCode());
        result = prime * result + ((getLastNodeName() == null) ? 0 : getLastNodeName().hashCode());
        result = prime * result + ((getNodeLevelTotal() == null) ? 0 : getNodeLevelTotal().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", instanceId=").append(instanceId);
        sb.append(", nodeId=").append(nodeId);
        sb.append(", nodeSign=").append(nodeSign);
        sb.append(", nodeName=").append(nodeName);
        sb.append(", nodeState=").append(nodeState);
        sb.append(", startTime=").append(startTime);
        sb.append(", orgId=").append(orgId);
        sb.append(", lastNodeId=").append(lastNodeId);
        sb.append(", lastNodeName=").append(lastNodeName);
        sb.append(", nodeLevelTotal=").append(nodeLevelTotal);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}