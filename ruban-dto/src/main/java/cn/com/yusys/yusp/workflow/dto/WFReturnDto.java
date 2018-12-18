package cn.com.yusys.yusp.workflow.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class WFReturnDto implements Serializable {
	private static final long serialVersionUID = 5557659769270202052L;
	
	/**
     * 评论信息
     */
	@NotNull
    private WFCommentDto comment;
    
    /**
     * 退回人机构id
     */
    @NotNull
    private String orgId;

	public WFCommentDto getComment() {
		return comment;
	}

	public void setComment(WFCommentDto comment) {
		this.comment = comment;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}