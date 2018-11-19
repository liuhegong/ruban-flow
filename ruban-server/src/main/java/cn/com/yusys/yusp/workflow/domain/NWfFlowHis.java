package cn.com.yusys.yusp.workflow.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "N_WF_FLOW_HIS")
public class NWfFlowHis implements Serializable {
    /**
     * 流程图id
     */
    @Id
    @Column(name = "FLOW_ID")
    private Long flowId;

    /**
     * 系统id
     */
    @Column(name = "SYSTEM_ID")
    private String systemId;

    /**
     * 流程图状态
     */
    @Column(name = "FLOW_STATE")
    private String flowState;

    /**
     * 创建时间
     */
    @Column(name = "START_TIME")
    private String startTime;

    /**
     * 版本号
     */
    @Column(name = "FLOW_VERSION")
    private Long flowVersion;

    /**
     * 流程名称
     */
    @Column(name = "FLOW_NAME")
    private String flowName;

    /**
     * 流程图管理员
     */
    @Column(name = "FLOW_ADMIN")
    private String flowAdmin;

    /**
     * 管理员机构id
     */
    @Column(name = "ORG_ID")
    private String orgId;

    /**
     * 流程图内容
     */
    @Column(name = "FLOW_CONTENT")
    private String flowContent;

    private static final long serialVersionUID = 1L;

    /**
     * 获取流程图id
     *
     * @return FLOW_ID - 流程图id
     */
    public Long getFlowId() {
        return flowId;
    }

    /**
     * 设置流程图id
     *
     * @param flowId 流程图id
     */
    public void setFlowId(Long flowId) {
        this.flowId = flowId;
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
     * 获取流程图状态
     *
     * @return FLOW_STATE - 流程图状态
     */
    public String getFlowState() {
        return flowState;
    }

    /**
     * 设置流程图状态
     *
     * @param flowState 流程图状态
     */
    public void setFlowState(String flowState) {
        this.flowState = flowState == null ? null : flowState.trim();
    }

    /**
     * 获取创建时间
     *
     * @return START_TIME - 创建时间
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 设置创建时间
     *
     * @param startTime 创建时间
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    /**
     * 获取版本号
     *
     * @return FLOW_VERSION - 版本号
     */
    public Long getFlowVersion() {
        return flowVersion;
    }

    /**
     * 设置版本号
     *
     * @param flowVersion 版本号
     */
    public void setFlowVersion(Long flowVersion) {
        this.flowVersion = flowVersion;
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
     * 获取流程图管理员
     *
     * @return FLOW_ADMIN - 流程图管理员
     */
    public String getFlowAdmin() {
        return flowAdmin;
    }

    /**
     * 设置流程图管理员
     *
     * @param flowAdmin 流程图管理员
     */
    public void setFlowAdmin(String flowAdmin) {
        this.flowAdmin = flowAdmin == null ? null : flowAdmin.trim();
    }

    /**
     * 获取管理员机构id
     *
     * @return ORG_ID - 管理员机构id
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置管理员机构id
     *
     * @param orgId 管理员机构id
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * 获取流程图内容
     *
     * @return FLOW_CONTENT - 流程图内容
     */
    public String getFlowContent() {
        return flowContent;
    }

    /**
     * 设置流程图内容
     *
     * @param flowContent 流程图内容
     */
    public void setFlowContent(String flowContent) {
        this.flowContent = flowContent == null ? null : flowContent.trim();
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
        NWfFlowHis other = (NWfFlowHis) that;
        return (this.getFlowId() == null ? other.getFlowId() == null : this.getFlowId().equals(other.getFlowId()))
            && (this.getSystemId() == null ? other.getSystemId() == null : this.getSystemId().equals(other.getSystemId()))
            && (this.getFlowState() == null ? other.getFlowState() == null : this.getFlowState().equals(other.getFlowState()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getFlowVersion() == null ? other.getFlowVersion() == null : this.getFlowVersion().equals(other.getFlowVersion()))
            && (this.getFlowName() == null ? other.getFlowName() == null : this.getFlowName().equals(other.getFlowName()))
            && (this.getFlowAdmin() == null ? other.getFlowAdmin() == null : this.getFlowAdmin().equals(other.getFlowAdmin()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getFlowContent() == null ? other.getFlowContent() == null : this.getFlowContent().equals(other.getFlowContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFlowId() == null) ? 0 : getFlowId().hashCode());
        result = prime * result + ((getSystemId() == null) ? 0 : getSystemId().hashCode());
        result = prime * result + ((getFlowState() == null) ? 0 : getFlowState().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getFlowVersion() == null) ? 0 : getFlowVersion().hashCode());
        result = prime * result + ((getFlowName() == null) ? 0 : getFlowName().hashCode());
        result = prime * result + ((getFlowAdmin() == null) ? 0 : getFlowAdmin().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getFlowContent() == null) ? 0 : getFlowContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", flowId=").append(flowId);
        sb.append(", systemId=").append(systemId);
        sb.append(", flowState=").append(flowState);
        sb.append(", startTime=").append(startTime);
        sb.append(", flowVersion=").append(flowVersion);
        sb.append(", flowName=").append(flowName);
        sb.append(", flowAdmin=").append(flowAdmin);
        sb.append(", orgId=").append(orgId);
        sb.append(", flowContent=").append(flowContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}