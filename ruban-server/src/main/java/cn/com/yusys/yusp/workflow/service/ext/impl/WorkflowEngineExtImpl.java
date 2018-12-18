package cn.com.yusys.yusp.workflow.service.ext.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.yusys.yusp.workflow.core.Cons;
import cn.com.yusys.yusp.workflow.core.engine.init.EngineCache;
import cn.com.yusys.yusp.workflow.core.engine.node.NodeInfo;
import cn.com.yusys.yusp.workflow.core.engine.node.type.FlowState;
import cn.com.yusys.yusp.workflow.core.engine.node.type.NodeType;
import cn.com.yusys.yusp.workflow.core.engine.node.type.OpType;
import cn.com.yusys.yusp.workflow.core.engine.node.type.SignInType;
import cn.com.yusys.yusp.workflow.core.exception.WorkflowException;
import cn.com.yusys.yusp.workflow.core.util.TimeUtil;
import cn.com.yusys.yusp.workflow.domain.NWfNode;
import cn.com.yusys.yusp.workflow.domain.NWfNodeDone;
import cn.com.yusys.yusp.workflow.domain.NWfProcess;
import cn.com.yusys.yusp.workflow.domain.NWfUserDone;
import cn.com.yusys.yusp.workflow.domain.NWfUserTodo;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.dto.WFReturnDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultMessageDto;
import cn.com.yusys.yusp.workflow.service.NWfNodeDoneService;
import cn.com.yusys.yusp.workflow.service.NWfNodeService;
import cn.com.yusys.yusp.workflow.service.NWfProcessService;
import cn.com.yusys.yusp.workflow.service.NWfUserDoneService;
import cn.com.yusys.yusp.workflow.service.NWfUserTodoService;
import cn.com.yusys.yusp.workflow.service.WorkflowBackUpService;
import cn.com.yusys.yusp.workflow.service.WorkflowCoreService;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowBizInterface;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowEngineExtInterface;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowEngineInterface;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowOrgInterface;
@Service
public class WorkflowEngineExtImpl implements WorkflowEngineExtInterface {
	private static final Log log = LogFactory.getLog(WorkflowEngineExtImpl.class);
	
	ObjectMapper jsonObj = new ObjectMapper();

	@Autowired
	private WorkflowEngineInterface workflowEngineService;
	
	@Autowired
	private WorkflowCoreService workflowCoreService;
	
	@Autowired
	private NWfProcessService processService;
	
	@Autowired
	private NWfUserDoneService userDoneService;
	
	@Autowired
	private NWfUserTodoService userTodoService;
	
	/**
	 * 业务后处理
	 */
	@Autowired
	private WorkflowBizInterface bizService;
	
	/**
	 * 用户信息获取服务
	 */
	@Autowired
	private WorkflowOrgInterface userService;
	
	@Autowired
	private NWfNodeDoneService nodeDoneService;
	
	@Autowired
	private NWfNodeService nodeService;

	@Autowired
	private WorkflowBackUpService workflowBackUpService;
	
	@Override
	@Transactional
	public ResultMessageDto returnBack(WFReturnDto returnDto) throws WorkflowException {
		ResultMessageDto re = new ResultMessageDto();

		if(log.isDebugEnabled()){
			log.debug("退回入参:"+returnDto);
		}
		
		String instanceId = returnDto.getComment().getInstanceId();
		String nodeId = returnDto.getComment().getNodeId();
		String orgId = returnDto.getOrgId();
		String userId = returnDto.getComment().getUserId();	
		String completeTime = TimeUtil.getDateyyyyMMddHHmmss();				

		// 检验参数
		if(isNullOrEmpty(orgId)||isNullOrEmpty(instanceId)||isNullOrEmpty(nodeId)||isNullOrEmpty(userId)){
			re.setCode("1");
			re.setTip(Cons.ERROR_MSG1);
			return re;
		}
		
		// 获取流程实例信息
		ResultInstanceDto instanceInfo = workflowEngineService.getInstanceInfo(instanceId, nodeId,null);
		if(null==instanceInfo){// 流程已经办结
			re.setCode("1");
			re.setTip(Cons.ERROR_MSG2);
			return re;
		}
		
		//判断提交者是否是当前流程提交人
		List<String> users = getCurrentNodeUsers(instanceId, nodeId);
		if(!isPartOf( users,userId)){
			re.setCode("1");
			re.setTip(Cons.ERROR_MSG3);
			return re;
		}
		
		// 保存评论
		workflowEngineService.saveComment(returnDto.getComment());
		
		//获取最早提交给此节点的节点
		QueryModel model = new QueryModel();
		model.getCondition().put("instanceId", instanceId);
		model.getCondition().put("nodeId", nodeId);
		model.setSort("start_time asc");
		List<NWfProcess> process = processService.selectByModel(model);
		if(process.isEmpty()){
			re.setCode("1");
			re.setTip(Cons.ERROR_MSG13);
			return re;
		}
		String nodeName = process.get(0).getNodeName();
		String lastNodeId = process.get(0).getLastNodeId();
		String lastNodeName = process.get(0).getLastNodeName();
		if(isNullOrEmpty(lastNodeId)){
			re.setCode("1");
			re.setTip(Cons.ERROR_MSG13);
			return re;
		}
		// 获取上一节点的最后一个处理人
		QueryModel model2 = new QueryModel();
		model2.getCondition().put("instanceId", instanceId);
		model2.getCondition().put("nodeId", lastNodeId);
		model2.setSort("end_time desc");
		List<NWfUserDone> usersT = userDoneService.selectByModel(model2);
		if(usersT.isEmpty()){
			re.setCode("1");
			re.setTip(Cons.ERROR_MSG14);
			return re;
		}
		
		String lastUserId = usersT.get(0).getUserId();
		String lastUserName = usersT.get(0).getLastUserName();
		if(isNullOrEmpty(lastUserId)){
			re.setCode("1");
			re.setTip(Cons.ERROR_MSG14);
			return re;
		}
		
		// 删除当前节点待办
		workflowBackUpService.deleteUserTodo(instanceId, nodeId);
		
		// 待办用户新增
		NWfUserTodo userTodo = new NWfUserTodo();
		userTodo.setInstanceId(instanceId);
		userTodo.setLastUserId(userId);
		userTodo.setLastUserName(userService.getUserInfo(instanceInfo.getSystemId(),userId).getUserName());
		userTodo.setNodeId(lastNodeId);// 下一节点设置为当前节点
		userTodo.setStartTime(completeTime);
		userTodo.setUserId(lastUserId);
		userTodo.setUserName(lastUserName);
		userTodo.setSignIn(SignInType.NO_SIGN_IN);// 置签收状态为不需要签收
		userTodo.setUserLevel(0);
		workflowBackUpService.deleteUserTodo(instanceId,lastNodeId);// 删除下一节点存在的待办【如打回后重新回到的未处理的节点】
		userTodoService.insertSelective(userTodo); // 插入待办人员
		
		// 节点实例信息新增
		NWfNodeDone currentNodeInfo = nodeDoneService.selectByPrimaryKey(instanceId, lastNodeId);
		NWfNode nodeInfo = new NWfNode();
		BeanUtils.copyProperties(currentNodeInfo, nodeInfo);
		nodeInfo.setInstanceId(instanceId);
		nodeInfo.setNodeState(OpType.RETURN_BACK);
		nodeInfo.setOrgId(orgId);
		nodeInfo.setLastNodeId(nodeId);
		nodeInfo.setLastNodeName(nodeName);
		nodeInfo.setStartTime(completeTime);
		nodeInfo.setNodeId(lastNodeId);
		nodeInfo.setNodeName(lastNodeName);
		NWfNode nextNodeInfoT = nodeService.selectByPrimaryKey(instanceId, lastNodeId);
		if (null != nextNodeInfoT) {// 节点还有未处理的实例时，先删除
			nodeService.deleteByPrimaryKey(instanceId, lastNodeId);
		}
		nodeService.insertSelective(nodeInfo);		
		
		NodeInfo currentNodeInfoT = EngineCache.getNodeInfo(nodeId);
		// 获取节点的上一节点，判断是否是汇总节点，如果是汇总节点需要将汇总节点的已办删除，防止流程再次走汇总节点时，不再往下流转
		NodeInfo lastNodeInfo = EngineCache.getNodeInfo(currentNodeInfoT.getLastNodeId());
		if(NodeType.TOGETHER_NODE.equals(lastNodeInfo.getNodeType())){
			nodeDoneService.deleteByPrimaryKey(instanceId, lastNodeInfo.getNodeId());
		}
		
		
		instanceInfo.setCurrentOpType(OpType.RETURN_BACK);
		instanceInfo.setFrom(nodeId);
		instanceInfo.setTo(lastNodeId);
		afterSubmit(currentNodeInfoT,instanceInfo);
		
		re.getUserNames().add(lastUserName);
		re.setNodeName(lastNodeName);// 设置返回节点名称
		re.setNodeId(lastNodeId);// 设置返回节点id
		re.setTip(Cons.SUCCESS_MSG15);
		re.setCode(FlowState.RUN);
		return re;
	}
	
	private void afterSubmit(NodeInfo nodeInfo,ResultInstanceDto instanceInfo) throws WorkflowException{
		String paramStr = instanceInfo.getFlowParam();
		if(!isNullOrEmpty(paramStr)){
			Map<String,Object> param = strToMap(paramStr);
			instanceInfo.setParam(param);
		}
		if(null!=nodeInfo&&!isNullOrEmpty(nodeInfo.getBizBeanId())){// 配置了后业务处理才进行业务处理
			log.debug("引擎将进行流程提交后业务处理：["+nodeInfo+"]");
			bizService.afterSubmit(nodeInfo.getBizBeanId(),instanceInfo);
		}else{
			log.debug("节点未配置后业务处理：["+nodeInfo.getNodeId()+"]");
		}
	}
	
	private Map<String,Object> strToMap(String content) throws WorkflowException{
		try {
			if(!isNullOrEmpty(content)){
				Map<String,Object> param = jsonObj.readValue(content, HashMap.class);
				return param;
			}
		} catch (Exception e) {
			throw new WorkflowException("流程公共参数格式化成map异常:"+content);
		} 
		return null;
	}
	
	
	/**
	 * 获取当前节点下所有办理人
	 * @param instanceId
	 * @param nodeId
	 * @return
	 */
	private List<String> getCurrentNodeUsers(String instanceId,String nodeId){
		return workflowCoreService.getNodeUsers(instanceId, nodeId);
	}
	
	/**
	 * 是否是当前节点办理人之一
	 * @param users
	 * @param currentUserId
	 * @return
	 */
	private boolean isPartOf(List<String> users,String currentUserId){
		return users.contains(currentUserId);
	}
	
	private boolean isNullOrEmpty(String param){
		if(null==param||"".equals(param)){
			return true;
		}
		return false;
	}

}
