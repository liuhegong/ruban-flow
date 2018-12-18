package cn.com.yusys.yusp.workflow.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "N_WF_PROCESS")
public class NWfProcess implements Serializable {
    /**
     * ����
     */
    @Id
    @Column(name = "PK_ID")
    private String pkId;

    @Column(name = "INSTANCE_ID")
    private String instanceId;

    @Column(name = "NODE_ID")
    private String nodeId;

    @Column(name = "NODE_NAME")
    private String nodeName;

    @Column(name = "START_TIME")
    private String startTime;

    @Column(name = "LAST_NODE_ID")
    private String lastNodeId;

    @Column(name = "LAST_NODE_NAME")
    private String lastNodeName;

    /**
     * �ڵ�ȼ�
     */
    @Column(name = "NODE_LEVEL")
    private Long nodeLevel;

    /**
     * �ڵ�����
     */
    @Column(name = "NODE_TYPE")
    private String nodeType;

    private static final long serialVersionUID = 1L;

    /**
     * ��ȡ����
     *
     * @return PK_ID - ����
     */
    public String getPkId() {
        return pkId;
    }

    /**
     * ��������
     *
     * @param pkId ����
     */
    public void setPkId(String pkId) {
        this.pkId = pkId == null ? null : pkId.trim();
    }

    /**
     * @return INSTANCE_ID
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * @param instanceId
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId == null ? null : instanceId.trim();
    }

    /**
     * @return NODE_ID
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }

    /**
     * @return NODE_NAME
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * @param nodeName
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    /**
     * @return START_TIME
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    /**
     * @return LAST_NODE_ID
     */
    public String getLastNodeId() {
        return lastNodeId;
    }

    /**
     * @param lastNodeId
     */
    public void setLastNodeId(String lastNodeId) {
        this.lastNodeId = lastNodeId == null ? null : lastNodeId.trim();
    }

    /**
     * @return LAST_NODE_NAME
     */
    public String getLastNodeName() {
        return lastNodeName;
    }

    /**
     * @param lastNodeName
     */
    public void setLastNodeName(String lastNodeName) {
        this.lastNodeName = lastNodeName == null ? null : lastNodeName.trim();
    }

    /**
     * ��ȡ�ڵ�ȼ�
     *
     * @return NODE_LEVEL - �ڵ�ȼ�
     */
    public Long getNodeLevel() {
        return nodeLevel;
    }

    /**
     * ���ýڵ�ȼ�
     *
     * @param nodeLevel �ڵ�ȼ�
     */
    public void setNodeLevel(Long nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    /**
     * ��ȡ�ڵ�����
     *
     * @return NODE_TYPE - �ڵ�����
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * ���ýڵ�����
     *
     * @param nodeType �ڵ�����
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType == null ? null : nodeType.trim();
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
        NWfProcess other = (NWfProcess) that;
        return (this.getPkId() == null ? other.getPkId() == null : this.getPkId().equals(other.getPkId()))
            && (this.getInstanceId() == null ? other.getInstanceId() == null : this.getInstanceId().equals(other.getInstanceId()))
            && (this.getNodeId() == null ? other.getNodeId() == null : this.getNodeId().equals(other.getNodeId()))
            && (this.getNodeName() == null ? other.getNodeName() == null : this.getNodeName().equals(other.getNodeName()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getLastNodeId() == null ? other.getLastNodeId() == null : this.getLastNodeId().equals(other.getLastNodeId()))
            && (this.getLastNodeName() == null ? other.getLastNodeName() == null : this.getLastNodeName().equals(other.getLastNodeName()))
            && (this.getNodeLevel() == null ? other.getNodeLevel() == null : this.getNodeLevel().equals(other.getNodeLevel()))
            && (this.getNodeType() == null ? other.getNodeType() == null : this.getNodeType().equals(other.getNodeType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPkId() == null) ? 0 : getPkId().hashCode());
        result = prime * result + ((getInstanceId() == null) ? 0 : getInstanceId().hashCode());
        result = prime * result + ((getNodeId() == null) ? 0 : getNodeId().hashCode());
        result = prime * result + ((getNodeName() == null) ? 0 : getNodeName().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getLastNodeId() == null) ? 0 : getLastNodeId().hashCode());
        result = prime * result + ((getLastNodeName() == null) ? 0 : getLastNodeName().hashCode());
        result = prime * result + ((getNodeLevel() == null) ? 0 : getNodeLevel().hashCode());
        result = prime * result + ((getNodeType() == null) ? 0 : getNodeType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pkId=").append(pkId);
        sb.append(", instanceId=").append(instanceId);
        sb.append(", nodeId=").append(nodeId);
        sb.append(", nodeName=").append(nodeName);
        sb.append(", startTime=").append(startTime);
        sb.append(", lastNodeId=").append(lastNodeId);
        sb.append(", lastNodeName=").append(lastNodeName);
        sb.append(", nodeLevel=").append(nodeLevel);
        sb.append(", nodeType=").append(nodeType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}