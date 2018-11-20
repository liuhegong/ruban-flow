package cn.com.yusys.yusp.workflow.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "N_WF_INSTANCE_HIS")
public class NWfInstanceHis implements Serializable {
    /**
     * 流程实例id
     */
    @Id
    @Column(name = "INSTANCE_ID")
    private String instanceId;

    /**
     * 流程名称
     */
    @Column(name = "FLOW_NAME")
    private String flowName;

    /**
     * 流程id
     */
    @Column(name = "FLOW_ID")
    private String flowId;

    /**
     * 流程管理员
     */
    @Column(name = "FLOW_ADMIN")
    private String flowAdmin;

    /**
     * 流程发起者
     */
    @Column(name = "FLOW_STARTER")
    private String flowStarter;

    /**
     * 流程发起时间
     */
    @Column(name = "START_TIME")
    private String startTime;

    /**
     * 系统id
     */
    @Column(name = "SYSTEM_ID")
    private String systemId;

    /**
     * 发起人机构id
     */
    @Column(name = "ORG_ID")
    private String orgId;

    /**
     * 流程状态
     */
    @Column(name = "FLOW_STATE")
    private String flowState;

    /**
     * 业务流水号
     */
    @Column(name = "BIZ_ID")
    private String bizId;

    /**
     * 客户名称
     */
    @Column(name = "BIZ_USER_NAME")
    private String bizUserName;

    /**
     * 客户id
     */
    @Column(name = "BIZ_USER_ID")
    private String bizUserId;

    /**
     * 流程参数
     */
    @Column(name = "FLOW_PARAM")
    private String flowParam;

    /**
     * 业务类型
     */
    @Column(name = "BIZ_TYPE")
    private String bizType;

    /**
     * 流程结束时间
     */
    @Column(name = "END_TIME")
    private String endTime;

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

    /**
     * 获取业务类型
     *
     * @return BIZ_TYPE - 业务类型
     */
    public String getBizType() {
        return bizType;
    }

    /**
     * 设置业务类型
     *
     * @param bizType 业务类型
     */
    public void setBizType(String bizType) {
        this.bizType = bizType == null ? null : bizType.trim();
    }

    /**
     * 获取流程结束时间
     *
     * @return END_TIME - 流程结束时间
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 设置流程结束时间
     *
     * @param endTime 流程结束时间
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
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
        NWfInstanceHis other = (NWfInstanceHis) that;
        return (this.getInstanceId() == null ? other.getInstanceId() == null : this.getInstanceId().equals(other.getInstanceId()))
            && (this.getFlowName() == null ? other.getFlowName() == null : this.getFlowName().equals(other.getFlowName()))
            && (this.getFlowId() == null ? other.getFlowId() == null : this.getFlowId().equals(other.getFlowId()))
            && (this.getFlowAdmin() == null ? other.getFlowAdmin() == null : this.getFlowAdmin().equals(other.getFlowAdmin()))
            && (this.getFlowStarter() == null ? other.getFlowStarter() == null : this.getFlowStarter().equals(other.getFlowStarter()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getSystemId() == null ? other.getSystemId() == null : this.getSystemId().equals(other.getSystemId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getFlowState() == null ? other.getFlowState() == null : this.getFlowState().equals(other.getFlowState()))
            && (this.getBizId() == null ? other.getBizId() == null : this.getBizId().equals(other.getBizId()))
            && (this.getBizUserName() == null ? other.getBizUserName() == null : this.getBizUserName().equals(other.getBizUserName()))
            && (this.getBizUserId() == null ? other.getBizUserId() == null : this.getBizUserId().equals(other.getBizUserId()))
            && (this.getFlowParam() == null ? other.getFlowParam() == null : this.getFlowParam().equals(other.getFlowParam()))
            && (this.getBizType() == null ? other.getBizType() == null : this.getBizType().equals(other.getBizType()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInstanceId() == null) ? 0 : getInstanceId().hashCode());
        result = prime * result + ((getFlowName() == null) ? 0 : getFlowName().hashCode());
        result = prime * result + ((getFlowId() == null) ? 0 : getFlowId().hashCode());
        result = prime * result + ((getFlowAdmin() == null) ? 0 : getFlowAdmin().hashCode());
        result = prime * result + ((getFlowStarter() == null) ? 0 : getFlowStarter().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getSystemId() == null) ? 0 : getSystemId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getFlowState() == null) ? 0 : getFlowState().hashCode());
        result = prime * result + ((getBizId() == null) ? 0 : getBizId().hashCode());
        result = prime * result + ((getBizUserName() == null) ? 0 : getBizUserName().hashCode());
        result = prime * result + ((getBizUserId() == null) ? 0 : getBizUserId().hashCode());
        result = prime * result + ((getFlowParam() == null) ? 0 : getFlowParam().hashCode());
        result = prime * result + ((getBizType() == null) ? 0 : getBizType().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", instanceId=").append(instanceId);
        sb.append(", flowName=").append(flowName);
        sb.append(", flowId=").append(flowId);
        sb.append(", flowAdmin=").append(flowAdmin);
        sb.append(", flowStarter=").append(flowStarter);
        sb.append(", startTime=").append(startTime);
        sb.append(", systemId=").append(systemId);
        sb.append(", orgId=").append(orgId);
        sb.append(", flowState=").append(flowState);
        sb.append(", bizId=").append(bizId);
        sb.append(", bizUserName=").append(bizUserName);
        sb.append(", bizUserId=").append(bizUserId);
        sb.append(", flowParam=").append(flowParam);
        sb.append(", bizType=").append(bizType);
        sb.append(", endTime=").append(endTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}