package cn.com.yusys.yusp.workflow.dto;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotNull;

public class WFStratDto implements Serializable {
	
	private static final long serialVersionUID = -3914960001309796362L;
	
	/**
     * 流程id
     */
	@NotNull
    private String flowId;  

    /**
     * 系统id
     */
	@NotNull
    private String systemId;

    /**
     * 机构id
     */
	@NotNull
    private String orgId;

    /**
     * 业务流水号
     */
	@NotNull
    private String bizId;
    
    /**
     * 业务类型
     */
	@NotNull
    private String bizType;

    /**
     * 客户名称
     */
	@NotNull
    private String bizUserName;

    /**
     * 客户id
     */
	@NotNull
    private String bizUserId;
    
    /**
     * 发起用户id
     */
	@NotNull
    private String userId;
    
    /**
     * 发起用户名称
     */
    private String userName;
    
    /**
     * 流程公共参数
     */
    private Map<String,Object> param;

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
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

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getBizUserName() {
		return bizUserName;
	}

	public void setBizUserName(String bizUserName) {
		this.bizUserName = bizUserName;
	}

	public String getBizUserId() {
		return bizUserId;
	}

	public void setBizUserId(String bizUserId) {
		this.bizUserId = bizUserId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	@Override
	public String toString() {
		return "WFStratDto [flowId=" + flowId + ", systemId=" + systemId + ", orgId=" + orgId + ", bizId=" + bizId
				+ ", bizType=" + bizType + ", bizUserName=" + bizUserName + ", bizUserId=" + bizUserId + ", userId="
				+ userId + ", userName=" + userName + ", param=" + param + "]";
	}
	
}