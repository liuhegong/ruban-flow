package cn.com.yusys.yusp.workflow.service;

import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;

/**
 * 路由运行脚本
 * @author figue
 *
 */
public interface WorkflowRouteInterface {
	/**
	 * 返回true，此节点返回
	 * @param instanceInfo
	 * @return
	 */
	boolean run(ResultInstanceDto instanceInfo,String nextNodeId);
}
