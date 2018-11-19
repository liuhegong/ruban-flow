package cn.com.yusys.yusp.workflow.core.engine;

import java.io.Serializable;
import java.util.List;

import cn.com.yusys.yusp.workflow.core.engine.flow.FlowInfo;

/**
 * 引擎下有多个flow
 * @author figue
 *
 */
public class EngineInfo implements Serializable{

	private static final long serialVersionUID = -669720134858662501L;	
	List<FlowInfo> flows;
	public List<FlowInfo> getFlows() {
		return flows;
	}
	public void setFlows(List<FlowInfo> flows) {
		this.flows = flows;
	}	
}
