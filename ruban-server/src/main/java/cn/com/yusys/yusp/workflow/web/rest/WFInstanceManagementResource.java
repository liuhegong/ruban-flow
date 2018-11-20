package cn.com.yusys.yusp.workflow.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.workflow.core.engine.init.EngineCache;
import cn.com.yusys.yusp.workflow.domain.NWfInstance;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.repository.mapper.EchainBenchMapper;
import cn.com.yusys.yusp.workflow.service.NWfInstanceService;
import cn.com.yusys.yusp.workflow.web.dto.ResultDto;
import cn.com.yusys.yusp.workflow.web.fillter.session.CurrentUser;
/**
 * 流程实例管理
 * @author figue
 *
 */
@RestController
@RequestMapping("/api/instance")
public class WFInstanceManagementResource {
	@Autowired
	private NWfInstanceService instanceService;
	
	/**
	 * 【流程发起者】 发起的在途流程
	 * @return
	 */
	@GetMapping("/doing")
	protected ResultDto<NWfInstance> doing(QueryModel model) {
		model.getCondition().put("flowStarter", CurrentUser.info.get().getUserId());
		List<NWfInstance> data = instanceService.selectByModel(model);
		return new ResultDto(data);
	}
	
	/**
	 * 【流程发起者】 发起的已经结束的流程
	 * @return
	 */
	@GetMapping("/end")
	protected Object end() {
		return EngineCache.getNodeInfoCache();
	}
	@Autowired
	EchainBenchMapper echainBenchMapper;
	
	@GetMapping("/test/{nodeId}")
	protected Object nodeInfo(@PathVariable String nodeId) {
		List<String> data = echainBenchMapper.selectInstanceId(nodeId);
		for(String instanceId:data){
			echainBenchMapper.deleteWorkflowInfo(instanceId);
		}
		return "success";
	}
}