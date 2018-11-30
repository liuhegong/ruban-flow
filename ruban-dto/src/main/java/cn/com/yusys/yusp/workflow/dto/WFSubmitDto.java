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
	 * 节点处理人有多个时，是否强行向下一节点提交，不需要再等待其他人员办理完成；默认为false;
	 * 一般【办理类型】是[多人顺序可结束]或[多人并行可结束]时生效，可以选择true和false;
	 */
	private boolean lastOne = false;
	
	/**
     * 流程公共参数
     */
    private Map<String,Object> param;
    
    /**
     * 节点及处理人信息
     */
    private List<NextNodeInfoDto> nextNodeInfos;

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

	public List<NextNodeInfoDto> getNextNodeInfos() {
		return nextNodeInfos;
	}

	public void setNextNodeInfos(List<NextNodeInfoDto> nextNodeInfos) {
		this.nextNodeInfos = nextNodeInfos;
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

	public boolean isLastOne() {
		return lastOne;
	}

	public void setLastOne(boolean lastOne) {
		this.lastOne = lastOne;
	}

}