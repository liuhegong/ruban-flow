package cn.com.yusys.yusp.workflow.service.ext;

import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;

/**
 * 路由运行脚本,决定路由指定的节点是否出现
 * @author figue
 *
 */
public interface WorkflowRouteInterface {
	/**
	 * 返回true，此节点返回
	 * @param instanceInfo
	 * @return
	 */
	boolean run(ResultInstanceDto instanceInfo,String nextNodeId,String isContinueBeanId);
}
