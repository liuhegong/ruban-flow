package cn.com.yusys.yusp.workflow.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class WFSubmitDto implements Serializable {
	
	private static final long serialVersionUID = -3914960001309796362L;
	
	/**
	 * 提交人机构
	 */
	private String orgId;
	/**
     * 流程公共参数
     */
    private Map<String,Object> param;
    
    /**
     * 节点及处理人信息
     */
    private List<NextNodeInfoDto> nextNodeInfos;

	public List<NextNodeInfoDto> getNextNodeInfos() {
		return nextNodeInfos;
	}

	public void setNextNodeInfos(List<NextNodeInfoDto> nextNodeInfos) {
		this.nextNodeInfos = nextNodeInfos;
	}

	/**
     * 评论信息
     */
    private WFCommentDto comment;
	

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}


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