package cn.com.yusys.yusp.workflow.biz;

import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;

public interface FeginRouteInterface {
	/**
	 * fegin方式运行路由脚本
	 * @param instanceInfo
	 * @param nextNodeId
	 * @return
	 */
	boolean runScript(ResultInstanceDto instanceInfo,String nextNodeId);
	
}
