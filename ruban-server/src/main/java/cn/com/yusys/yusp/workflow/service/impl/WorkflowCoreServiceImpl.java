package cn.com.yusys.yusp.workflow.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.yusys.yusp.workflow.core.Cons;
import cn.com.yusys.yusp.workflow.core.engine.init.EngineCache;
import cn.com.yusys.yusp.workflow.core.exception.WorkflowException;
import cn.com.yusys.yusp.workflow.core.flow.FlowInfo;
import cn.com.yusys.yusp.workflow.core.node.NodeInfo;
import cn.com.yusys.yusp.workflow.core.node.RouteInfo;
import cn.com.yusys.yusp.workflow.core.node.type.FlowState;
import cn.com.yusys.yusp.workflow.core.node.type.NodeState;
import cn.com.yusys.yusp.workflow.core.node.type.NodeType;
import cn.com.yusys.yusp.workflow.core.node.type.OpUsersType;
import cn.com.yusys.yusp.workflow.core.util.TimeUtil;
import cn.com.yusys.yusp.workflow.core.util.UUIDUtil;
import cn.com.yusys.yusp.workflow.domain.NWfComment;
import cn.com.yusys.yusp.workflow.domain.NWfInstance;
import cn.com.yusys.yusp.workflow.domain.NWfNode;
import cn.com.yusys.yusp.workflow.domain.NWfNodeHis;
import cn.com.yusys.yusp.workflow.domain.NWfUserDone;
import cn.com.yusys.yusp.workflow.domain.NWfUserTodo;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.dto.NextNodeInfoDto;
import cn.com.yusys.yusp.workflow.dto.WFCommentDto;
import cn.com.yusys.yusp.workflow.dto.WFStratDto;
import cn.com.yusys.yusp.workflow.dto.WFSubmitDto;
import cn.com.yusys.yusp.workflow.dto.WFUserDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultCommentDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultNodeDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultOpTypeDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultWFMessageDto;
import cn.com.yusys.yusp.workflow.service.NWfCommentService;
import cn.com.yusys.yusp.workflow.service.NWfInstanceService;
import cn.com.yusys.yusp.workflow.service.NWfNodeHisService;
import cn.com.yusys.yusp.workflow.service.NWfNodeService;
import cn.com.yusys.yusp.workflow.service.NWfUserDoneService;
import cn.com.yusys.yusp.workflow.service.NWfUserTodoService;
import cn.com.yusys.yusp.workflow.service.WorkflowBackUpService;
import cn.com.yusys.yusp.workflow.service.WorkflowCoreService;
import cn.com.yusys.yusp.workflow.service.WorkflowCoreServiceInterface;
import cn.com.yusys.yusp.workflow.service.WorkflowMessageInterface;
import cn.com.yusys.yusp.workflow.service.WorkflowNodeInterface;
import cn.com.yusys.yusp.workflow.service.WorkflowRouteInterface;
@Service
public class WorkflowCoreServiceImpl implements WorkflowCoreServiceInterface {

	private static final Log log = LogFactory.getLog(WorkflowCoreServiceImpl.class);
	
	ObjectMapper jsonObj = new ObjectMapper();
	
	@Autowired
	private NWfInstanceService instanceService;
	
	@Autowired
	private NWfNodeService nodeService;
	
	@Autowired
	private NWfNodeHisService nodeHisService;
	
	@Autowired
	private NWfUserTodoService userTodoService;
	
	@Autowired
	private NWfUserDoneService userDoneService;
	
	@Autowired
	private WorkflowCoreService workflowCoreService;
	
	@Autowired
	WorkflowBackUpService workflowBackUpService;
	
	@Autowired
	private NWfCommentService commentService;
	
	/**
	 * 业务后处理
	 */
	@Autowired
	private WorkflowNodeInterface workflowNodeBizService;
	
	/**
	 * 运行路由脚本
	 */
	@Autowired
	private WorkflowRouteInterface WorkflowRouteService;
	
	/**
	 * 消息发送
	 */
	@Autowired
	private WorkflowMessageInterface WorkflowMessageService;
	
	@Override
	@Transactional
	public ResultInstanceDto start(WFStratDto stratDto) {
		if(log.isDebugEnabled()){
			log.debug("开始流程发起:"+stratDto);
		}
		FlowInfo flowInfo = EngineCache.getInstance().getFlowInfo(stratDto.getFlowId(), stratDto.getSystemId());
		NodeInfo stratNodeInfo = flowInfo.getStartNode();
		List<String> firstNodeIds = stratNodeInfo.getNextNodes();
		String firstNodeId = firstNodeIds.get(0);
		String instanceId = UUIDUtil.getUUID();
		String startTime = TimeUtil.getDateyyyyMMddHHmmss();
		
		NWfInstance record = new NWfInstance();				
		BeanUtils.copyProperties(stratDto,record);
		BeanUtils.copyProperties(flowInfo,record);		
		record.setFlowStarter(stratDto.getUserId());
		record.setFlowAdmin(flowInfo.getAdmin());
		record.setFlowState(FlowState.STRAT);
		record.setInstanceId(instanceId);
		record.setStartTime(startTime);
		if(null!=stratDto.getParam()){		
			try {
				record.setFlowParam(jsonObj.writeValueAsString(stratDto.getParam()));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new WorkflowException("流程公共参数格式化字符串异常:"+stratDto.getParam());
			}
		}
		
		// 插入流程实例信息
		instanceService.insertSelective(record);
		
		NWfNode nodeInfo = new NWfNode();
		NodeInfo firstNode = flowInfo.getNodeInfo(firstNodeId);
		BeanUtils.copyProperties(firstNode,nodeInfo);
		nodeInfo.setInstanceId(instanceId);
		nodeInfo.setNodeState(NodeState.RUN);
		nodeInfo.setOrgId(stratDto.getOrgId());
		nodeInfo.setLastNodeId(stratNodeInfo.getNodeId());
		nodeInfo.setLastNodeName(stratNodeInfo.getNodeName());
		nodeInfo.setStartTime(startTime);
		// 插入节点信息
		nodeService.insertSelective(nodeInfo);
		
		NWfUserTodo userTodo = new NWfUserTodo();
		userTodo.setInstanceId(instanceId);
		userTodo.setNodeId(firstNodeId);
		userTodo.setLastUserId(stratDto.getUserId());
		userTodo.setLastUserName(stratDto.getUserName());
		userTodo.setStartTime(startTime);
		userTodo.setUserId(stratDto.getUserId());
		userTodo.setUserName(stratDto.getUserName());
		// 插入待办用户
		userTodoService.insertSelective(userTodo);		
		ResultInstanceDto instanceInfo = getInstanceInfo(record.getInstanceId(), firstNodeId,null);
		
		// 后业务处理
		afterSubmit(instanceInfo);
		// 发送消息
		sendMessage(instanceInfo);
		
		if(log.isDebugEnabled()){
			log.debug("流程发起成功:"+instanceInfo);
		}
		return instanceInfo;
	}
	
	/**
	 * 后业务处理
	 * @param instanceInfo
	 */
	private void afterSubmit(ResultInstanceDto instanceInfo){
		workflowNodeBizService.afterSubmit(instanceInfo);
	}
	
	/**
	 * 发送消息
	 * @param instanceInfo
	 */
	private void sendMessage(ResultInstanceDto instanceInfo){
		try {
			WorkflowMessageService.sendMessage(instanceInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn("消息发送失败"+instanceInfo);
		}
	}

	@Override
	public ResultInstanceDto getInstanceInfo(String instanceId,String nodeId,String currentUserId) {
		ResultInstanceDto instanceInfo = workflowCoreService.getInstanceInfo(instanceId, nodeId);
		if(null!=instanceInfo.getFlowParam()
				&&!"".equals(instanceInfo.getFlowParam())){
			Map param = jsonObj.convertValue(instanceInfo.getFlowParam(), Map.class);
			instanceInfo.setParam(param);
		}		
		
		FlowInfo flowInfo = EngineCache.getInstance().getFlowInfo(instanceInfo.getFlowId(), instanceInfo.getSystemId());
		NodeInfo nodeInfo = EngineCache.getInstance().getNodeInfo(nodeId);
		
		ResultOpTypeDto opTypeDto = new ResultOpTypeDto();
		BeanUtils.copyProperties(flowInfo,opTypeDto);
		BeanUtils.copyProperties(nodeInfo,opTypeDto);
		//设置操作权限
		instanceInfo.setOpType(opTypeDto);
		
		// 查询用户保存对的评论
		if(null!=currentUserId&&!"".equals(currentUserId)){
			QueryModel model = new QueryModel();
			model.getCondition().put("instanceId", instanceId);
			model.getCondition().put("nodeId", nodeId);
			model.getCondition().put("userId", currentUserId);
			model.setSort("start_time desc");
			List<NWfComment> comments = commentService.selectByModel(model);
			if(!comments.isEmpty()){
				ResultCommentDto comment = new ResultCommentDto();
				BeanUtils.copyProperties(comments.get(0),comment);
				instanceInfo.setComment(comment);
			}
		}
		return instanceInfo;
	}

	@Override
	public int saveComment(WFCommentDto comment) {
		NWfComment record = new NWfComment();
		BeanUtils.copyProperties(comment,record);
		if(null!=comment.getCommentId()&&!"".equals(comment.getCommentId())){
			commentService.updateByPrimaryKeySelective(record);
		}else{
			record.setCommentId(UUIDUtil.getUUID());
			record.setStartTime(TimeUtil.getDateyyyyMMddHHmmss());
			commentService.insertSelective(record);
		}
		return 1;
	}

	@Override
	public ResultCommentDto getComment(String instanceId, String nodeId, String currentUserId) {
		if(log.isDebugEnabled()){
			log.debug("流程提交:[instanceId="+instanceId+";nodeId="+nodeId+";currentUserId="+currentUserId+"]");
		}
		if (null != instanceId && !"".equals(instanceId) 				
				&& null != nodeId && !"".equals(nodeId)
				&& null != currentUserId && !"".equals(currentUserId)) {
			QueryModel model = new QueryModel();
			model.getCondition().put("instanceId", instanceId);
			model.getCondition().put("nodeId", nodeId);
			model.getCondition().put("userId", currentUserId);
			model.setSort("start_time desc");

			List<NWfComment> comments = commentService.selectByModel(model);
			if (!comments.isEmpty()) {
				ResultCommentDto comment = new ResultCommentDto();
				BeanUtils.copyProperties(comments.get(0), comment);
				return comment;
			}
			return null;
		} else {
			throw new WorkflowException(Cons.ERROR_MSG1);
		}
	}

	@Override
	public List<ResultNodeDto> getNextNodeInfos(String instanceId,String nodeId) {		
		ResultInstanceDto instanceInfo = getInstanceInfo(instanceId, nodeId,null);
		List<NextNodeInfoDto> nextNodes = getWFNextNodeInfos(instanceInfo,nodeId);
		// 数据转换
		List<ResultNodeDto> data = new ArrayList<ResultNodeDto>();
		for(NextNodeInfoDto nextNode:nextNodes){
			NodeInfo nodeInfo = EngineCache.getInstance().getNodeInfo(nextNode.getNextNodeId());
			ResultNodeDto resultNodeDto = new ResultNodeDto();
			BeanUtils.copyProperties(nodeInfo, resultNodeDto);
			// 开始人员计算
			resultNodeDto.setUsers(calculateUser(splitNodeUser(nodeInfo.getNodeUser()),nodeInfo.getNodeId(),instanceInfo.getOrgId()));
			data.add(resultNodeDto);
		}
		return data;
	}
	
	/**
	 * 运行节点路由
	 * @param instanceInfo
	 * @param nextNodeId
	 * @return
	 */
	private boolean runRoute(ResultInstanceDto instanceInfo,String nextNodeId){
		return WorkflowRouteService.run(instanceInfo,nextNodeId);
	}
	
	/**
	 * 获取后续节点原始信息
	 * @param instanceInfo
	 * @param nodeId
	 * @return
	 */
	private List<NextNodeInfoDto> getWFNextNodeInfos(ResultInstanceDto instanceInfo,String nodeId){
		String orgId = instanceInfo.getOrgId();// 人员计算是上一办理人指定时，根据机构相对关系获取节点处理人员
		
		NodeInfo nodeInfo = EngineCache.getInstance().getNodeInfo(nodeId);
		
		// 路由到的节点放入返回的list中
		List<NodeInfo> nodeInfos = new ArrayList<NodeInfo>();
		for(String nodeIdT:nodeInfo.getNextNodes()){
			NodeInfo nodeInfoT = EngineCache.getInstance().getNodeInfo(nodeIdT);											
			nodeInfos.add(nodeInfoT);
		}
		if(log.isDebugEnabled()){
			log.debug("获取节点条数:"+nodeInfos.size());
		}
		// 条件单选或者条件多选，需要运行脚本决定是否返回
		if(NodeType.CONDITION_MULTI_NODE.equals(nodeInfo.getNodeType())
				||NodeType.CONDITION_RADIO_NODE.equals(nodeInfo.getNodeType())){
			List<RouteInfo> routes = nodeInfo.getRouteInfos();
			for(RouteInfo route:routes){
				if(null!=route.getIsContinueBeanId()&&!"".equals(route.getIsContinueBeanId())){
					String nextNodeId = route.getNextNodeId();
					if(!runRoute(instanceInfo,nextNodeId)){
						NodeInfo nodeInfoT = EngineCache.getInstance().getNodeInfo(nextNodeId);						
						nodeInfos.remove(nodeInfoT);
						if(log.isDebugEnabled()){
							log.debug("节点路由返回false,去除此节点:"+nextNodeId);
						}
					}
				}
				
			}
		}		
		if(log.isDebugEnabled()){
			log.debug("过滤剩余条数:"+nodeInfos.size());
		}
		// 数据转换
		List<NextNodeInfoDto> data = new ArrayList<NextNodeInfoDto>();
		for(NodeInfo nextNodeInfo:nodeInfos){
			NextNodeInfoDto nextNodeInfoDto = new NextNodeInfoDto();
			nextNodeInfoDto.setNextNodeId(nextNodeInfo.getNodeId());
			nextNodeInfoDto.setNextNodeUserIds(splitNodeUser(nextNodeInfo.getNodeUser()));
			data.add(nextNodeInfoDto);
		}
		return data;
	}
	
	/**
	 * 人员计算
	 * @param nodeInfo
	 * @param orgId 人员计算是上一办理人指定时，根据机构相对关系获取节点处理人员
	 * @return
	 */
	private List<WFUserDto> calculateUser(List<String> user,String nodeId,String orgId){
		List<WFUserDto> users = new ArrayList<WFUserDto>();

		NodeInfo nodeInfo = EngineCache.getInstance().getNodeInfo(nodeId);
		String nodeType = nodeInfo.getNodeType();		
		for(String userId : user){// R。 U.等
			if(NodeType.AUTO_NODE.equals(nodeType)// 自动节点，结束节点，汇总节点用户写死
					||NodeType.END_NODE.equals(nodeType)
					||NodeType.TOGETHER_NODE.equals(nodeType)){
				
				return users;
			}else{// 其他节点，根据节点计算
				String opUsersType = nodeInfo.getOpUsersType();
				if(OpUsersType.USEROP.equals(opUsersType)){// 上一办里人指定
					// 待完成
				}else if(OpUsersType.AUTO.equals(opUsersType)){// 系统指定
					
				}else{// 人员列表选择
					// 代完成
					
				}
			}	
		}
		
		// 黑名单TODO
		WFUserDto userDto = new WFUserDto();
		userDto.setUserId(Cons.SYSTEM);
		userDto.setUserName("系统指定");
		users.add(userDto);
		return users;
	}

	@Override
	@Transactional
	public List<ResultWFMessageDto> submit(WFSubmitDto submitDto) {
		List<ResultWFMessageDto> re = new ArrayList<ResultWFMessageDto>();
		// 保存评论
		saveComment(submitDto.getComment());
		
		String instanceId = submitDto.getComment().getInstanceId();
		String nodeId = submitDto.getComment().getNodeId();
		String orgId = submitDto.getComment().getOrgId();
		String userId = submitDto.getComment().getUserId();
				
		if(log.isDebugEnabled()){
			log.debug("流程提交:[instanceId="+instanceId+";nodeId="+nodeId+";userId="+userId+"] to "+submitDto.getNextNodeInfos());
		}
		if(isNullOrEmpty(instanceId)||isNullOrEmpty(nodeId)||isNullOrEmpty(userId)||isNullOrEmpty(orgId)){
			//throw new WorkflowException(Cons.ERROR_MSG1);
			ResultWFMessageDto m1 = new ResultWFMessageDto();
			m1.setCode("1");
			m1.setTip(Cons.ERROR_MSG1);
			re.add(m1);
			return re;
		}
		
		if (!checkNode(nodeId)){//校验节点是否有后续节点
			ResultWFMessageDto m1 = new ResultWFMessageDto();
			m1.setCode("1");
			m1.setTip(Cons.ERROR_MSG4);
			re.add(m1);
			return re;
		}
		
		ResultInstanceDto instanceInfo = getInstanceInfo(instanceId, nodeId,null);
		if(null==instanceInfo){// 流程已经办结
			ResultWFMessageDto m1 = new ResultWFMessageDto();
			m1.setCode("1");
			m1.setTip(Cons.ERROR_MSG2);
			re.add(m1);
			return re;
		}
		
		//判断提交者是否是当前流程提交人
		List<String> users = getCurrentNodeUsers(instanceId, nodeId);
		if(!isPartOf( users,userId)){
			ResultWFMessageDto m1 = new ResultWFMessageDto();
			m1.setCode("1");
			m1.setTip(Cons.ERROR_MSG3);
			re.add(m1);
			return re;
		}
		
		//是否是最后一个处理人
		boolean isLast = (users.size()==1);
		
		// TODO 按条件转移操作操作
		
		if(isLast){// 是最后办理人,直接提交流程
			 submitNextMultiNode(instanceInfo, submitDto.getNextNodeInfos(),re,userId,orgId);
		}else{// 不是最后办理人，节点内流转
			 //flow( instanceId, nodeId, userId, orgId, submitDto.getNextNodeInfos(),re);			
		}
		return re;
		
	}
	
	/**
	 * 
	 * @param instanceInfo
	 * @param nodeInfoDtos
	 * @param msg
	 */
	private void submitNextMultiNode(ResultInstanceDto instanceInfo, List<NextNodeInfoDto> nodeInfoDtos, List<ResultWFMessageDto> msg,String currentUserId,String orgId) {
		if (null == nodeInfoDtos || nodeInfoDtos.isEmpty()) {// 未指定节点从内存获取
			if (nodeInfoDtos == null) {
				nodeInfoDtos = new ArrayList<NextNodeInfoDto>();
			}
			List<NextNodeInfoDto> nextNodes = getWFNextNodeInfos(instanceInfo,instanceInfo.getNodeId());
			for (NextNodeInfoDto nextNode : nextNodes) {			
				submitNextOneNode(instanceInfo, nextNode, msg,currentUserId,orgId);
			}
		} else {// 指定了节点则直接提交
			for(NextNodeInfoDto nextNodeDto:nodeInfoDtos){
				submitNextOneNode(instanceInfo, nextNodeDto, msg,currentUserId,orgId);
			}
		}
	}
	
	private void submitNextOneNode(ResultInstanceDto instanceInfo,NextNodeInfoDto nodeInfoDtos,List<ResultWFMessageDto> msg,String currentUserId,String orgId){
		String nextNodeId = nodeInfoDtos.getNextNodeId();
		if(nextNodeId.contains(Cons.END_SIGN)){// 流程结束标识
			msg.add(end(instanceInfo));
		}
		
		// 判断节点类型
		NodeInfo nodeInfoT = EngineCache.getInstance().getNodeInfo(nextNodeId);
		String nodeType = nodeInfoT.getNodeType();
		if(NodeType.END_NODE.equals(nodeType)){// 结束节点
			msg.add(end(instanceInfo));
		}else{		
			// 节点处理人
			List<String> users = nodeInfoDtos.getNextNodeUserIds();
			if(null==users||users.isEmpty()){// 未指定办理人，从流程图中获取
						users = splitNodeUser(nodeInfoT.getNodeUser());
			}
			List<WFUserDto> nextNodeUsers = calculateUser(users,nextNodeId,instanceInfo.getOrgId());
			if(NodeType.AUTO_NODE.equals(nodeType)){// 自动节点
				
			}else if(NodeType.TOGETHER_NODE.equals(nodeType)){// 汇总节点
				
			}else {// 非以上节点
				complete(instanceInfo, nextNodeId,nextNodeUsers,currentUserId,orgId);
			}
		}
	}
	
	/**
	 * 流程提交
	 * @param instanceId
	 * @param nextNodeId
	 * @param nextNodeUsers
	 * @return
	 */
	public ResultWFMessageDto complete(ResultInstanceDto instanceInfo,String nextNodeId,List<WFUserDto> nextNodeUsers,String currentUserId,String orgId){
		ResultWFMessageDto re = new ResultWFMessageDto();
		
		String instanceId = instanceInfo.getInstanceId();
		String nodeId = instanceInfo.getNodeId();
		if(log.isDebugEnabled()){
			log.debug("流程最终提交:[instanceId="+instanceId+";nodeId="+nodeId+";userId="+currentUserId+"] to "+nextNodeUsers);
		}
		if(isNullOrEmpty(instanceId)||isNullOrEmpty(nodeId)||isNullOrEmpty(currentUserId)||isNullOrEmpty(orgId)){
			ResultWFMessageDto m1 = new ResultWFMessageDto();
			m1.setCode("1");
			m1.setTip(Cons.ERROR_MSG1);
			return re;
		}
		if(nextNodeUsers.isEmpty()){
			re.setTip(Cons.ERROR_MSG6);
			return re;
		}
		
		String completeTime = TimeUtil.getDateyyyyMMddHHmmss();		
		// 节点历史信息迁移
		NWfNode currentNodeInfo = nodeService.selectByPrimaryKey(instanceId,nodeId);		
		NWfNodeHis nodeInfoHis = new NWfNodeHis();
		BeanUtils.copyProperties(currentNodeInfo,nodeInfoHis);
		nodeInfoHis.setEndTime(completeTime);
		nodeHisService.insertSelective(nodeInfoHis);
		nodeService.deleteByPrimaryKey(instanceId, nodeId);
		
		// 新增节点信息
		NWfNode nodeInfo = new NWfNode();
		NodeInfo nextNodeInfo =  EngineCache.getInstance().getNodeInfo(nextNodeId);
		BeanUtils.copyProperties(nextNodeInfo,nodeInfo);
		nodeInfo.setInstanceId(instanceId);
		nodeInfo.setNodeState(NodeState.RUN);
		nodeInfo.setOrgId(orgId);
		nodeInfo.setLastNodeId(currentNodeInfo.getNodeId());
		nodeInfo.setLastNodeName(currentNodeInfo.getNodeName());
		nodeInfo.setStartTime(completeTime);
		nodeService.insertSelective(nodeInfo);
		
		// 用户待办信息迁移为已办
		QueryModel model = new QueryModel();
		model.getCondition().put("instanceId", instanceId);
		model.getCondition().put("nodeId", nodeId);
		List<NWfUserTodo> userTodes = userTodoService.selectByModel(model);
		userTodes.stream().forEach(NWfUserTodo->{NWfUserTodo.setEndTime(completeTime);});		
		workflowBackUpService.backUpUserTodo(userTodes);
		workflowBackUpService.deleteUserTodo(instanceId,nodeId);
		
		List<NWfUserTodo> userTodeNew = new ArrayList<NWfUserTodo>();
		for(WFUserDto wFUserDto:nextNodeUsers){
			NWfUserTodo userTodo = new NWfUserTodo();
			userTodo.setInstanceId(instanceId);
			userTodo.setLastUserId(userTodes.get(0).getUserId());
			userTodo.setLastUserName(userTodes.get(0).getUserName());
			userTodo.setNodeId(nodeId);
			userTodo.setStartTime(completeTime);
			userTodo.setUserId(wFUserDto.getUserId());
			userTodo.setUserName(wFUserDto.getUserName());
			userTodeNew.add(userTodo);
		}
		// 新增用户待办信息
		workflowBackUpService.insertUserTodoBatch(userTodeNew);
		
		re.setTip(Cons.SUCCESS_MSG5);
		// 后业务处理
		afterSubmit(instanceInfo);
		// 发送消息
		sendMessage(instanceInfo);
		return re;
	}
	
	/**
	 * 流程结束
	 * @param instanceInfo
	 * @return
	 */
	private ResultWFMessageDto end(ResultInstanceDto instanceInfo){
		
		
		
		// 后业务处理
		afterSubmit(instanceInfo);
		// 发送消息
		sendMessage(instanceInfo);
		ResultWFMessageDto re = new ResultWFMessageDto();
		re.setTip(Cons.SUCCESS_MSG5);
		return re;
	}
	
	private boolean isNullOrEmpty(String param){
		if(null==param||"".equals(param)){
			return true;
		}
		return false;
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
	
	/**
	 * 校验节点是否有后续节点
	 * @param nodeId
	 * @return 0-有，1-无
	 */
	private boolean checkNode(String nodeId){
		ResultWFMessageDto re = new ResultWFMessageDto();
		NodeInfo nodeInfo = EngineCache.getInstance().getNodeInfo(nodeId);
		if(nodeInfo.getNextNodes().isEmpty()){
			return false;
		}
		return true;
	}
	
	/**
	 * 节点配置的用户格式化
	 * @param nodeUser
	 * @return
	 */
	private List<String> splitNodeUser(String nodeUser){
		List<String> users = new ArrayList<String>();
		//if(isNullOrEmpty(nodeUser)){
			String[] usersT = nodeUser.split(Cons.SPLIT);
			users = Arrays.asList(usersT);
		//}
		return users;
	}

}
