package cn.com.yusys.yusp.workflow.dto;

import java.util.List;

public class NextNodeInfoDto {
	String nextNodeId;
	List<String> nextNodeUserIds;
	public String getNextNodeId() {
		return nextNodeId;
	}
	public void setNextNodeId(String nextNodeId) {
		this.nextNodeId = nextNodeId;
	}
	public List<String> getNextNodeUserIds() {
		return nextNodeUserIds;
	}
	public void setNextNodeUserIds(List<String> nextNodeUserIds) {
		this.nextNodeUserIds = nextNodeUserIds;
	}
}
