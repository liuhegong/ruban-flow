package cn.com.yusys.yusp.workflow.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class WFCommentDto implements Serializable {
    
	/**
     * 主键
     */
    private String commentId;

    /**
     * 实例id
     */
    @NotNull
    private String instanceId;

    /**
     * 节点id
     */
    @NotNull
    private String nodeId;

    /**
     * 用户id
     */
    @NotNull
    private String userId;
    
    /**
     * 用户结论
     */
    @NotNull
    private String commentSign;


    /**
     * 用户评价
     */
    @NotNull
    private String userComment;

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
     * 获取用户评价
     *
     * @return USER_COMMENT - 用户评价
     */
    public String getUserComment() {
        return userComment;
    }

    /**
     * 设置用户评价
     *
     * @param userComment 用户评价
     */
    public void setUserComment(String userComment) {
        this.userComment = userComment == null ? null : userComment.trim();
    }

}