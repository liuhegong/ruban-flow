package cn.com.yusys.yusp.workflow.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "N_WF_USER_HIS")
public class NWfUserHis  implements Serializable {
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
     * 用户id
     */
    @Id
    @Column(name = "USER_ID")
    private String userId;

    /**
     * 开始时间
     */
    @Column(name = "START_TIME")
    private String startTime;

    @Column(name = "END_TIME")
    private String endTime;

    /**
     * 用户姓名
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 上一节点处理人
     */
    @Column(name = "LAST_USER_ID")
    private String lastUserId;

    /**
     * 上一节点处理人姓名
     */
    @Column(name = "LAST_USER_NAME")
    private String lastUserName;

    /**
     * 1-已签收，0-未签收
     */
    @Column(name = "SIGN_IN")
    private String signIn;

    /**
     * 用户顺序，越小越靠前
     */
    @Column(name = "USER_LEVEL")
    private Integer userLevel;

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
     * 获取用户id
     *
     * @return USER_ID - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取开始时间
     *
     * @return START_TIME - 开始时间
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    /**
     * @return END_TIME
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    /**
     * 获取用户姓名
     *
     * @return USER_NAME - 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     *
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取上一节点处理人
     *
     * @return LAST_USER_ID - 上一节点处理人
     */
    public String getLastUserId() {
        return lastUserId;
    }

    /**
     * 设置上一节点处理人
     *
     * @param lastUserId 上一节点处理人
     */
    public void setLastUserId(String lastUserId) {
        this.lastUserId = lastUserId == null ? null : lastUserId.trim();
    }

    /**
     * 获取上一节点处理人姓名
     *
     * @return LAST_USER_NAME - 上一节点处理人姓名
     */
    public String getLastUserName() {
        return lastUserName;
    }

    /**
     * 设置上一节点处理人姓名
     *
     * @param lastUserName 上一节点处理人姓名
     */
    public void setLastUserName(String lastUserName) {
        this.lastUserName = lastUserName == null ? null : lastUserName.trim();
    }

    /**
     * 获取1-已签收，0-未签收
     *
     * @return SIGN_IN - 1-已签收，0-未签收
     */
    public String getSignIn() {
        return signIn;
    }

    /**
     * 设置1-已签收，0-未签收
     *
     * @param signIn 1-已签收，0-未签收
     */
    public void setSignIn(String signIn) {
        this.signIn = signIn == null ? null : signIn.trim();
    }

    /**
     * 获取用户顺序，越小越靠前
     *
     * @return USER_LEVEL - 用户顺序，越小越靠前
     */
    public Integer getUserLevel() {
        return userLevel;
    }

    /**
     * 设置用户顺序，越小越靠前
     *
     * @param userLevel 用户顺序，越小越靠前
     */
    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
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
        NWfUserHis other = (NWfUserHis) that;
        return (this.getInstanceId() == null ? other.getInstanceId() == null : this.getInstanceId().equals(other.getInstanceId()))
            && (this.getNodeId() == null ? other.getNodeId() == null : this.getNodeId().equals(other.getNodeId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getLastUserId() == null ? other.getLastUserId() == null : this.getLastUserId().equals(other.getLastUserId()))
            && (this.getLastUserName() == null ? other.getLastUserName() == null : this.getLastUserName().equals(other.getLastUserName()))
            && (this.getSignIn() == null ? other.getSignIn() == null : this.getSignIn().equals(other.getSignIn()))
            && (this.getUserLevel() == null ? other.getUserLevel() == null : this.getUserLevel().equals(other.getUserLevel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInstanceId() == null) ? 0 : getInstanceId().hashCode());
        result = prime * result + ((getNodeId() == null) ? 0 : getNodeId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getLastUserId() == null) ? 0 : getLastUserId().hashCode());
        result = prime * result + ((getLastUserName() == null) ? 0 : getLastUserName().hashCode());
        result = prime * result + ((getSignIn() == null) ? 0 : getSignIn().hashCode());
        result = prime * result + ((getUserLevel() == null) ? 0 : getUserLevel().hashCode());
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
        sb.append(", userId=").append(userId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", userName=").append(userName);
        sb.append(", lastUserId=").append(lastUserId);
        sb.append(", lastUserName=").append(lastUserName);
        sb.append(", signIn=").append(signIn);
        sb.append(", userLevel=").append(userLevel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}