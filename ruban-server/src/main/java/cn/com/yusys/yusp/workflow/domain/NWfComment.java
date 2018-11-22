package cn.com.yusys.yusp.workflow.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "N_WF_COMMENT")
public class NWfComment implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "COMMENT_ID")
    private String commentId;

    /**
     * 实例id
     */
    @Column(name = "INSTANCE_ID")
    private String instanceId;

    /**
     * 节点id
     */
    @Column(name = "NODE_ID")
    private String nodeId;

    /**
     * 用户id
     */
    @Column(name = "USER_ID")
    private String userId;

    /**
     * 用户结论
     */
    @Column(name = "COMMENT_SIGN")
    private String commentSign;

    /**
     * 评论时间
     */
    @Column(name = "START_TIME")
    private String startTime;

    /**
     * 节点等级
     */
    @Column(name = "NODE_LEVEL")
    private Long nodeLevel;

    /**
     * 用户评论
     */
    @Column(name = "USER_COMMENT")
    private String userComment;

    /**
     * 用户名称
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 节点名称
     */
    @Column(name = "NODE_NAME")
    private String nodeName;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return COMMENT_ID - 主键
     */
    public String getCommentId() {
        return commentId;
    }

    /**
     * 设置主键
     *
     * @param commentId 主键
     */
    public void setCommentId(String commentId) {
        this.commentId = commentId == null ? null : commentId.trim();
    }

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
     * 获取用户结论
     *
     * @return COMMENT_SIGN - 用户结论
     */
    public String getCommentSign() {
        return commentSign;
    }

    /**
     * 设置用户结论
     *
     * @param commentSign 用户结论
     */
    public void setCommentSign(String commentSign) {
        this.commentSign = commentSign == null ? null : commentSign.trim();
    }

    /**
     * 获取评论时间
     *
     * @return START_TIME - 评论时间
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 设置评论时间
     *
     * @param startTime 评论时间
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    /**
     * 获取节点等级
     *
     * @return NODE_LEVEL - 节点等级
     */
    public Long getNodeLevel() {
        return nodeLevel;
    }

    /**
     * 设置节点等级
     *
     * @param nodeLevel 节点等级
     */
    public void setNodeLevel(Long nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    /**
     * 获取用户评论
     *
     * @return USER_COMMENT - 用户评论
     */
    public String getUserComment() {
        return userComment;
    }

    /**
     * 设置用户评论
     *
     * @param userComment 用户评论
     */
    public void setUserComment(String userComment) {
        this.userComment = userComment == null ? null : userComment.trim();
    }

    /**
     * 获取用户名称
     *
     * @return USER_NAME - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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
        NWfComment other = (NWfComment) that;
        return (this.getCommentId() == null ? other.getCommentId() == null : this.getCommentId().equals(other.getCommentId()))
            && (this.getInstanceId() == null ? other.getInstanceId() == null : this.getInstanceId().equals(other.getInstanceId()))
            && (this.getNodeId() == null ? other.getNodeId() == null : this.getNodeId().equals(other.getNodeId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCommentSign() == null ? other.getCommentSign() == null : this.getCommentSign().equals(other.getCommentSign()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getNodeLevel() == null ? other.getNodeLevel() == null : this.getNodeLevel().equals(other.getNodeLevel()))
            && (this.getUserComment() == null ? other.getUserComment() == null : this.getUserComment().equals(other.getUserComment()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getNodeName() == null ? other.getNodeName() == null : this.getNodeName().equals(other.getNodeName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getInstanceId() == null) ? 0 : getInstanceId().hashCode());
        result = prime * result + ((getNodeId() == null) ? 0 : getNodeId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCommentSign() == null) ? 0 : getCommentSign().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getNodeLevel() == null) ? 0 : getNodeLevel().hashCode());
        result = prime * result + ((getUserComment() == null) ? 0 : getUserComment().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getNodeName() == null) ? 0 : getNodeName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commentId=").append(commentId);
        sb.append(", instanceId=").append(instanceId);
        sb.append(", nodeId=").append(nodeId);
        sb.append(", userId=").append(userId);
        sb.append(", commentSign=").append(commentSign);
        sb.append(", startTime=").append(startTime);
        sb.append(", nodeLevel=").append(nodeLevel);
        sb.append(", userComment=").append(userComment);
        sb.append(", userName=").append(userName);
        sb.append(", nodeName=").append(nodeName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}