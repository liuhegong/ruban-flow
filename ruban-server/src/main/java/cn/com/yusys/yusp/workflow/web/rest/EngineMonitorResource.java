package cn.com.yusys.yusp.workflow.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.workflow.core.engine.init.EngineCache;
import cn.com.yusys.yusp.workflow.web.dto.ResultDto;
/**
 * 流程缓存监控
 * @author figue
 *
 */
@RestController
@RequestMapping("/ruban")
public class EngineMonitorResource {
	/**
	 * 获取所有流程信息
	 * @return
	 */
	@GetMapping("/flow")
	protected Object flowsInfo() {
		return EngineCache.getEngineInfo();
	}
	/**
	 * 获取所有节点信息
	 * @return
	 */
	@GetMapping("/node")
	protected Object nodesInfo() {
		return EngineCache.getNodeInfoCache();
	}
	
	/**
	 * 获取某个节点信息
	 * @param nodeId
	 * @return
	 */
	@GetMapping("/node/{nodeId}")
	protected Object nodeInfo(@PathVariable String nodeId) {
		return EngineCache.getNodeInfoCache().get(nodeId);
	}
	
	/**
	 * 刷新流程缓存
	 * @return
	 */
	@GetMapping("/init")
	protected ResultDto<String> init() {
		EngineCache.getInstance().init();
		return new ResultDto<String>("success");
	}
	
}