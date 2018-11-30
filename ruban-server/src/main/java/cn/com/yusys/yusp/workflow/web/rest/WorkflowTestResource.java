package cn.com.yusys.yusp.workflow.web.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.workflow.core.exception.WorkflowException;
import cn.com.yusys.yusp.workflow.dto.NextNodeInfoDto;
import cn.com.yusys.yusp.workflow.dto.WFCommentDto;
import cn.com.yusys.yusp.workflow.dto.WFStratDto;
import cn.com.yusys.yusp.workflow.dto.WFSubmitDto;
import cn.com.yusys.yusp.workflow.dto.WFUserDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultNodeDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultMessageDto;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowEngineInterface;
import cn.com.yusys.yusp.workflow.web.dto.ResultDto;
import cn.com.yusys.yusp.workflow.web.fillter.session.CurrentUser;

@RestController
public class WorkflowTestResource {
	@Autowired
	private WorkflowEngineInterface WorkflowCoreServicee;
	
	@GetMapping("/")
	public ResultDto index(){
		ResultDto resultDto = new ResultDto();
		resultDto.setData(1);
		return resultDto;
	}	
	
	private List<String> nodeIdsTTT = new ArrayList();	
	private Map<String,List<String>> userIdsTTT = new HashMap<>();
	private String instanceIdTTT = null;
	
	@GetMapping("/1")
	public ResultDto WorkflowCoreServicee() throws WorkflowException{
		WFStratDto stratDto = new WFStratDto();
		stratDto.setBizType("bizType");
		stratDto.setBizId("bizId");
		stratDto.setBizUserId("客户id");
		stratDto.setBizUserName("客户名称");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(System.currentTimeMillis()+"x", System.currentTimeMillis()+"cc");
		stratDto.setParam(param );
		
		stratDto.setFlowId("1");
		stratDto.setOrgId(CurrentUser.info.get().getOrgId());
		stratDto.setSystemId(CurrentUser.info.get().getSystemId());
		stratDto.setUserId(CurrentUser.info.get().getUserId());
		stratDto.setUserName(CurrentUser.info.get().getUserName());
		ResultInstanceDto data = WorkflowCoreServicee.start(stratDto);
		ResultDto resultDto = new ResultDto();
		resultDto.setData(data);
		
		nodeIdsTTT.clear();
		userIdsTTT.clear();
		nodeIdsTTT.add(data.getNodeId());		
		userIdsTTT.put(data.getNodeId(),Arrays.asList(data.getFlowStarter()));
		
		instanceIdTTT = data.getInstanceId();
		return resultDto;
	}
	
	@GetMapping("/1-1")
	public ResultDto submit() throws WorkflowException{	
		ResultDto resultDto = new ResultDto();
		List<ResultMessageDto> data1 = new ArrayList();
		for(String nodeId:nodeIdsTTT){
			List<ResultNodeDto> data = WorkflowCoreServicee.getNextNodeInfos(instanceIdTTT,nodeId);
			for(ResultNodeDto node:data){
				WFSubmitDto sb = new WFSubmitDto();
				sb.setOrgId(CurrentUser.info.get().getOrgId());
				WFCommentDto comment = new WFCommentDto();
				comment.setCommentSign("1");
				comment.setInstanceId(instanceIdTTT);
				comment.setNodeId(nodeId);
				comment.setUserComment("userComment");
				comment.setUserId(CurrentUser.info.get().getUserId());
				sb.setComment(comment);
				List<NextNodeInfoDto> nextNodeInfos = new ArrayList<NextNodeInfoDto>();
				NextNodeInfoDto nextNodeInfoDto = new NextNodeInfoDto();
				
				nextNodeInfoDto.setNextNodeId(node.getNodeId());
				
				List<String> userIds = new ArrayList();
				for(WFUserDto sto:node.getUsers()){
					userIds.add(sto.getUserId());
				}
				nextNodeInfoDto.setNextNodeUserIds(userIds);
				nextNodeInfos.add(nextNodeInfoDto);
				sb.setNextNodeInfos(nextNodeInfos);
				
				Map<String, Object> param = new HashMap();
				param.put(System.currentTimeMillis()+"X", System.currentTimeMillis());
				sb.setParam(param );
				
				data1.addAll(WorkflowCoreServicee.submit(sb));
				
				nodeIdsTTT.clear();
				userIdsTTT.clear();
				nodeIdsTTT.add(node.getNodeId());
				userIdsTTT.put(node.getNodeId(),userIds);
			}
		}	
		resultDto.setData(data1);
		return resultDto;
	}
	
	@GetMapping("/2")
	public ResultDto WorkflowCoreService2() throws WorkflowException{
		WFStratDto stratDto = new WFStratDto();
		stratDto.setOrgId(CurrentUser.info.get().getOrgId());
		stratDto.setBizType("bizType");
		stratDto.setBizId("bizId");
		stratDto.setBizUserId("客户id");
		stratDto.setBizUserName("客户名称");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(System.currentTimeMillis()+"x", System.currentTimeMillis()+"cc");
		stratDto.setParam(param );
		
		stratDto.setFlowId("2");
		stratDto.setOrgId(CurrentUser.info.get().getOrgId());
		stratDto.setSystemId(CurrentUser.info.get().getSystemId());
		stratDto.setUserId(CurrentUser.info.get().getUserId());
		stratDto.setUserName(CurrentUser.info.get().getUserName());
		ResultInstanceDto data = WorkflowCoreServicee.start(stratDto);
		ResultDto resultDto = new ResultDto();
		resultDto.setData(data);
		
		nodeIdsTTT.clear();
		userIdsTTT.clear();
		nodeIdsTTT.add(data.getNodeId());		
		userIdsTTT.put(data.getNodeId(),Arrays.asList(data.getFlowStarter()));
		
		instanceIdTTT = data.getInstanceId();
		return resultDto;
	}
	
	@GetMapping("/2-1")
	public ResultDto submit2() throws WorkflowException{	
		ResultDto resultDto = new ResultDto();
		List<ResultMessageDto> data1 = new ArrayList();
		for(String nodeId:nodeIdsTTT){
			List<ResultNodeDto> data = WorkflowCoreServicee.getNextNodeInfos(instanceIdTTT,nodeId);
			for(ResultNodeDto node:data){
				WFSubmitDto sb = new WFSubmitDto();
				sb.setOrgId(CurrentUser.info.get().getUserId());
				WFCommentDto comment = new WFCommentDto();
				comment.setCommentSign("1");
				comment.setInstanceId(instanceIdTTT);
				comment.setNodeId(nodeId);
				comment.setUserComment("userComment");
				comment.setUserId(CurrentUser.info.get().getUserId());
				sb.setComment(comment);
				List<NextNodeInfoDto> nextNodeInfos = new ArrayList<NextNodeInfoDto>();
				NextNodeInfoDto nextNodeInfoDto = new NextNodeInfoDto();
				
				nextNodeInfoDto.setNextNodeId(node.getNodeId());
				
				List<String> userIds = new ArrayList();
				for(WFUserDto sto:node.getUsers()){
					userIds.add(sto.getUserId());
				}
				nextNodeInfoDto.setNextNodeUserIds(userIds);
				nextNodeInfos.add(nextNodeInfoDto);
				sb.setNextNodeInfos(nextNodeInfos);
				
				Map<String, Object> param = new HashMap();
				param.put(System.currentTimeMillis()+"X", System.currentTimeMillis());
				sb.setParam(param );
				
				data1.addAll(WorkflowCoreServicee.submit(sb));
				
				nodeIdsTTT.clear();
				userIdsTTT.clear();
				nodeIdsTTT.add(node.getNodeId());
				userIdsTTT.put(node.getNodeId(),userIds);
			}
		}	
		resultDto.setData(data1);
		return resultDto;
	}
	
}