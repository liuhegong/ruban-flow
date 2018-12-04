package cn.com.yusys.yusp.workflow.service.ext.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.yusys.yusp.workflow.core.Cons;
import cn.com.yusys.yusp.workflow.core.engine.flow.FlowInfo;
import cn.com.yusys.yusp.workflow.core.engine.init.EngineCache;
import cn.com.yusys.yusp.workflow.core.engine.node.NodeInfo;
import cn.com.yusys.yusp.workflow.core.engine.node.RouteInfo;
import cn.com.yusys.yusp.workflow.core.engine.node.type.ComputeType;
import cn.com.yusys.yusp.workflow.core.engine.node.type.FlowState;
import cn.com.yusys.yusp.workflow.core.engine.node.type.HandleType;
import cn.com.yusys.yusp.workflow.core.engine.node.type.NodeType;
import cn.com.yusys.yusp.workflow.core.engine.node.type.OpType;
import cn.com.yusys.yusp.workflow.core.engine.node.type.OpUsersType;
import cn.com.yusys.yusp.workflow.core.engine.node.type.ReDoUserSelect;
import cn.com.yusys.yusp.workflow.core.engine.node.type.SignInType;
import cn.com.yusys.yusp.workflow.core.engine.node.type.UserType;
import cn.com.yusys.yusp.workflow.core.exception.WorkflowException;
import cn.com.yusys.yusp.workflow.core.org.OrgCache;
import cn.com.yusys.yusp.workflow.core.org.WFUser;
import cn.com.yusys.yusp.workflow.core.util.TimeUtil;
import cn.com.yusys.yusp.workflow.core.util.UUIDUtil;
import cn.com.yusys.yusp.workflow.domain.NWfComment;
import cn.com.yusys.yusp.workflow.domain.NWfInstance;
import cn.com.yusys.yusp.workflow.domain.NWfInstanceHis;
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
import cn.com.yusys.yusp.workflow.dto.result.ResultMessageDto;
import cn.com.yusys.yusp.workflow.service.NWfCommentService;
import cn.com.yusys.yusp.workflow.service.NWfInstanceHisService;
import cn.com.yusys.yusp.workflow.service.NWfInstanceService;
import cn.com.yusys.yusp.workflow.service.NWfNodeHisService;
import cn.com.yusys.yusp.workflow.service.NWfNodeService;
import cn.com.yusys.yusp.workflow.service.NWfUserDoneService;
import cn.com.yusys.yusp.workflow.service.NWfUserTodoService;
import cn.com.yusys.yusp.workflow.service.WorkflowBackUpService;
import cn.com.yusys.yusp.workflow.service.WorkflowCoreService;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowBizInterface;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowEngineInterface;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowMessageInterface;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowRouteInterface;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowUserFilterInterface;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowUserInterface;
/**
 * 流转核心实现
 * @author figue
 *
 */
@Service
public class WorkflowEngineImpl implements WorkflowEngineInterface {

	private static final Log log = LogFactory.getLog(WorkflowEngineImpl.class);
	
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
	private NWfInstanceHisService instanceHisService;
	
	@Autowired
	private WorkflowCoreService workflowCoreService;
	
	@Autowired
	WorkflowBackUpService workflowBackUpService;
	
	@Autowired
	private NWfCommentService commentService;
	
	/**
	 * [分配策略]
	 */
	@Autowired
	private WorkflowUserFilterInterface userFilterInterface;
	
	/**
	 * 业务后处理
	 */
	@Autowired
	private WorkflowBizInterface bizService;
	
	/**
	 * 运行路由脚本
	 */
	@Autowired
	private WorkflowRouteInterface routeService;
	
	/**
	 * 消息发送服务
	 */
	@Autowired
	private WorkflowMessageInterface messageService;
	
	/**
	 * 用户信息获取服务
	 */
	@Autowired
	private WorkflowUserInterface userService;
	
	@Override
	@Transactional
	public ResultInstanceDto start(WFStratDto stratDto) throws WorkflowException {
		if(log.isDebugEnabled()){
			log.debug("开始流程发起:"+stratDto);
		}
		String systemId = stratDto.getSystemId();
		String userId = stratDto.getUserId();
		String orgId = stratDto.getOrgId();
		String flowId = stratDto.getFlowId(); 
		if (isNullOrEmpty(systemId) || isNullOrEmpty(userId) || isNullOrEmpty(flowId)|| isNullOrEmpty(orgId)) {
			throw new WorkflowException(Cons.ERROR_MSG1);
		}
		
		EngineCache.getInstance();
		FlowInfo flowInfo = EngineCache.getFlowInfo(flowId, systemId);
		NodeInfo stratNodeInfo = flowInfo.getStartNode();
		List<String> firstNodeIds = stratNodeInfo.getNextNodes();
		String firstNodeId = firstNodeIds.get(0);
		String instanceId = UUIDUtil.getUUID();
		String startTime = TimeUtil.getDateyyyyMMddHHmmss();
		
		NWfInstance record = new NWfInstance();				
		BeanUtils.copyProperties(stratDto,record);
		BeanUtils.copyProperties(flowInfo,record);		
		record.setFlowStarter(userId);		
		String userName = OrgCache.getUserInfo(systemId, userId).getUserName();
		record.setFlowStarterName(userName);
		record.setFlowAdmin(flowInfo.getAdmin());
		record.setFlowState(FlowState.STRAT);
		record.setInstanceId(instanceId);
		record.setStartTime(startTime);
		if(null!=stratDto.getParam()){		
			try {
				record.setFlowParam(mapToStr(stratDto.getParam()));
			} catch (Exception e) {
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
		nodeInfo.setNodeState(OpType.RUN);
		nodeInfo.setOrgId(orgId);
		nodeInfo.setLastNodeId(stratNodeInfo.getNodeId());
		nodeInfo.setLastNodeName(stratNodeInfo.getNodeName());
		nodeInfo.setStartTime(startTime);
		// 插入节点信息
		nodeService.insertSelective(nodeInfo);
		
		NWfUserTodo userTodo = new NWfUserTodo();
		userTodo.setInstanceId(instanceId);
		userTodo.setNodeId(firstNodeId);
		userTodo.setLastUserId(userId);
		userTodo.setLastUserName(userName);
		userTodo.setStartTime(startTime);
		userTodo.setUserId(userId);
		userTodo.setUserName(userName);
		// 插入待办用户
		userTodoService.insertSelective(userTodo);		
		ResultInstanceDto instanceInfo = getInstanceInfo(record.getInstanceId(), firstNodeId,null);
		
		//流程初始化
		afterInit(stratNodeInfo,instanceInfo);
		
		// 发送消息
		List<NWfUserTodo> users = new ArrayList<NWfUserTodo>();
		users.add(userTodo);
		sendMessage(users,firstNode,instanceInfo);
		
		if(log.isDebugEnabled()){
			log.debug("流程发起成功:"+instanceInfo);
		}
		return instanceInfo;
	}
	
	/**
	 * 提交业务处理
	 * @param instanceInfo
	 * @throws WorkflowException
	 */
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
	/**
	 * 流程初始化时回调
	 * @param instanceInfo
	 * @throws WorkflowException
	 */
	private void afterInit(NodeInfo nodeInfo,ResultInstanceDto instanceInfo) throws WorkflowException{
		String paramStr = instanceInfo.getFlowParam();
		if(!isNullOrEmpty(paramStr)){
			Map<String,Object> param = strToMap(paramStr);
			instanceInfo.setParam(param);
		}	
		if(null!=nodeInfo&&!isNullOrEmpty(nodeInfo.getBizBeanId())){// 配置了后业务处理才进行业务处理
			log.debug("引擎将进行流程发起后业务处理：["+nodeInfo+"]");
			bizService.afterInit(nodeInfo.getBizBeanId(),instanceInfo);
		}else{
			log.debug("节点未配置后业务处理：["+nodeInfo.getNodeId()+"]");
		}
	}
	
	private void afterEnd(NodeInfo nodeInfo,ResultInstanceDto instanceInfo) throws WorkflowException{
		String paramStr = instanceInfo.getFlowParam();
		if(!isNullOrEmpty(paramStr)){
			Map<String,Object> param = strToMap(paramStr);
			instanceInfo.setParam(param);
		}
		if(null!=nodeInfo&&!isNullOrEmpty(nodeInfo.getBizBeanId())){// 配置了后业务处理才进行业务处理
			log.debug("引擎将进行流程结束后业务处理：["+nodeInfo+"]");
			bizService.afterEnd(nodeInfo.getBizBeanId(),instanceInfo);
		}else{
			log.debug("节点未配置后业务处理：["+nodeInfo.getNodeId()+"]");
		}
	}
	
	/**
	 * 发送消息
	 * @param instanceInfo
	 */
	private void sendMessage(List<NWfUserTodo> users,NodeInfo nodeInfo,ResultInstanceDto instanceInfo){
		try {
			String noticeType = nodeInfo.getNoticeType();
			for(NWfUserTodo user:users){
				if(!isNullOrEmpty(noticeType)){
					WFUser  userT = new WFUser();
					BeanUtils.copyProperties(user, userT);
					messageService.sendMessage(noticeType, userT, instanceInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("消息发送失败"+instanceInfo);
		}
	}

	@Override
	public ResultInstanceDto getInstanceInfo(String instanceId,String nodeId,String currentUserId) throws WorkflowException {
		if(log.isDebugEnabled()){
			log.debug("获取流程实例信息:[instanceId="+instanceId+";nodeId="+nodeId+";currentUserId="+currentUserId+"]");
		}
		
		if (isNullOrEmpty(instanceId) || isNullOrEmpty(nodeId)) {
			throw new WorkflowException(Cons.ERROR_MSG1);
		}
		ResultInstanceDto instanceInfo = workflowCoreService.getInstanceInfo(instanceId, nodeId);
		
		Map<String, Object> param = strToMap(instanceInfo.getFlowParam());
		instanceInfo.setParam(param);
		
		
		EngineCache.getInstance();
		FlowInfo flowInfo = EngineCache.getFlowInfo(instanceInfo.getFlowId(), instanceInfo.getSystemId());
		NodeInfo nodeInfo = EngineCache.getNodeInfo(nodeId);
		// 获取节点配置的业务页面，如果没有则使用，开始节点配置的页面
		String bizPage = nodeInfo.getBizPage();
		if(isNullOrEmpty(bizPage)){// 设置节点业务页面，如果没有则使用开始节点配置的页面
			bizPage = EngineCache.getFlowInfo(instanceInfo.getFlowId(), instanceInfo.getSystemId()).getStartNode().getBizPage();
		}
		instanceInfo.setBizPage(bizPage);
		instanceInfo.setNodeType(nodeInfo.getNodeType());// 设置节点类型
		instanceInfo.setHandleType(nodeInfo.getHandleType());// 设置办理类型
		ResultOpTypeDto opTypeDto = new ResultOpTypeDto();
		BeanUtils.copyProperties(flowInfo,opTypeDto);
		BeanUtils.copyProperties(nodeInfo,opTypeDto);
		opTypeDto.setSignIn("0");// 签收和撤销签收按钮默认不显示
		opTypeDto.setUnSignIn("0");
		// 设置操作权限
		instanceInfo.setOpType(opTypeDto);
		
		// 查询用户保存对的评论
		if(!isNullOrEmpty(currentUserId)){
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
			
			// 添加用户签收、撤销签收按钮权限
			if(HandleType.ONE_SIGN.equals(nodeInfo.getHandleType())){//节点【办理类型】是【单人签收办里】时，获取当前用户是否已经签收
				NWfUserTodo userTodoInfo = userTodoService.selectByPrimaryKey(instanceId, nodeId, currentUserId);
				if(SignInType.HAVE_SIGN_IN.equals(userTodoInfo.getSignIn())){
					opTypeDto.setSignIn("0");// 撤销签收按钮显示
					opTypeDto.setUnSignIn("1");
				}else if(SignInType.TODO_SIGN_IN.equals(userTodoInfo.getSignIn())){
					opTypeDto.setSignIn("1");// 签收按钮显示
					opTypeDto.setUnSignIn("0");
				}
			}
			
		}
		if(log.isDebugEnabled()){
			log.debug("获取流程实例信息:[instanceId="+instanceId+";nodeId="+nodeId+";currentUserId="+currentUserId+"]"+instanceInfo);
		}
		return instanceInfo;
	}

	@Override
	public ResultCommentDto saveComment(WFCommentDto comment) {
		if(log.isDebugEnabled()){
			log.debug("保存评论:[comment="+comment+"]");
		}
		ResultInstanceDto instanceInfo = getInstanceInfo(comment.getInstanceId(), comment.getNodeId(),null);
		if(null==instanceInfo){// 流程已经办结
			throw new WorkflowException(Cons.ERROR_MSG1); 
		}
		
		NWfComment record = new NWfComment();
		BeanUtils.copyProperties(comment,record);
		
		record.setUserName(OrgCache.getUserInfo(instanceInfo.getSystemId(), comment.getUserId()).getUserName());
		EngineCache.getInstance();
		NodeInfo nodeInfo = EngineCache.getNodeInfo(comment.getNodeId());
		String nodeLevel = nodeInfo.getNodeLevel();
		record.setNodeName(nodeInfo.getNodeName());
		if(null!=nodeLevel){// 设置节点等级，根据节点等级排序可以控制打回等选择范围
			record.setNodeLevel(Long.parseLong(nodeLevel));
		}
		record.setStartTime(TimeUtil.getDateyyyyMMddHHmmss());
		if(null!=comment.getCommentId()&&!"".equals(comment.getCommentId())){
			commentService.updateByPrimaryKeySelective(record);
		}else{
			record.setCommentId(UUIDUtil.getUUID());
			commentService.insertSelective(record);
		}
		if(log.isDebugEnabled()){
			log.debug("保存评论完成:[comment="+comment+"]");
		}
		return getNodeUserComment(comment.getInstanceId(), comment.getNodeId(), comment.getUserId());
	}

	@Override
	public List<ResultCommentDto> getComments(String instanceId)  throws WorkflowException{
		if(log.isDebugEnabled()){
			log.debug("获取流程所有评论:[instanceId="+instanceId+"]");
		}
		if(isNullOrEmpty(instanceId)){
			throw new WorkflowException(Cons.ERROR_MSG1);
		}
		QueryModel model = new QueryModel();
		model.getCondition().put("instanceId", instanceId);
		model.setSort("start_time desc");
		List<ResultCommentDto> comments = new ArrayList<ResultCommentDto>();
		List<NWfComment> commentsT = commentService.selectByModel(model);
		for(NWfComment commentT:commentsT){
			ResultCommentDto comment = new ResultCommentDto();
			BeanUtils.copyProperties(commentT, comment);
			comments.add(comment);
		}
		return comments;
	}

	@Override
	public ResultCommentDto getNodeUserComment(String instanceId, String nodeId, String currentUserId)  throws WorkflowException{
		if(log.isDebugEnabled()){
			log.debug("获取流程评论:[instanceId="+instanceId+";nodeId="+nodeId+";currentUserId="+currentUserId+"]");
		}
		
		if (isNullOrEmpty(instanceId) || isNullOrEmpty(nodeId) || isNullOrEmpty(currentUserId)) {
			throw new WorkflowException(Cons.ERROR_MSG1);
		}

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
	}
	
	@Override
	public List<ResultNodeDto> getNextNodeInfos(String instanceId,String nodeId) throws WorkflowException {		
		if(log.isDebugEnabled()){
			log.debug("获取下一节点信息:[instanceId="+instanceId+";nodeId="+nodeId+"]");
		}
		if (isNullOrEmpty(instanceId) || isNullOrEmpty(nodeId)){
			throw new WorkflowException(Cons.ERROR_MSG1);
		}
		ResultInstanceDto instanceInfo = getInstanceInfo(instanceId, nodeId,null);
		List<NextNodeInfoDto> nextNodes = getWFNextNodeInfos(instanceInfo,nodeId);
		// 数据转换
		List<ResultNodeDto> data = new ArrayList<ResultNodeDto>();
		for(NextNodeInfoDto nextNode:nextNodes){
			NodeInfo nodeInfo = EngineCache.getNodeInfo(nextNode.getNextNodeId());
			ResultNodeDto resultNodeDto = new ResultNodeDto();
			BeanUtils.copyProperties(nodeInfo, resultNodeDto);
			// 开始人员计算
			resultNodeDto.setUsers(getNodeUsers(instanceId,nodeInfo.getNodeId(),instanceInfo.getOrgId(),instanceInfo.getSystemId()));
			data.add(resultNodeDto);
		}
		if(log.isDebugEnabled()){
			log.debug("获取下一节点信息:[instanceId="+instanceId+";nodeId="+nodeId+"]"+data);
		}
		return data;
	}
	
	@Override
	public List<WFUserDto> getNodeUsers(String instanceId,String nodeId,String orgId,String systemId){
		
		NodeInfo nodeInfo = EngineCache.getNodeInfo(nodeId);
		List<WFUserDto> users = new ArrayList<WFUserDto>();
		String nodeType = nodeInfo.getNodeType();
		if (NodeType.AUTO_NODE.equals(nodeType)// 自动节点，结束节点，汇总节点用户写死
				|| NodeType.END_NODE.equals(nodeType) || NodeType.TOGETHER_NODE.equals(nodeType)) {
			
			WFUserDto userDto = new WFUserDto();
			userDto.setUserId(Cons.SYSTEM_USER_ID);
			userDto.setUserName(Cons.SYSTEM_USER_NAME);
			users.add(userDto);
			return users;
		}else{// 其他种类节点，如果【人员选择类型】是系统指定等
			String opUsersType = nodeInfo.getOpUsersType();
			if (OpUsersType.USEROP.equals(opUsersType)) {// 上一办里人指定
				// 待完成
			} else if (OpUsersType.AUTO.equals(opUsersType)) {// 系统指定
				WFUserDto userDto = new WFUserDto();
				userDto.setUserId(Cons.SYSTEM_USER_ID);
				userDto.setUserName(Cons.SYSTEM_USER_NAME);
				users.add(userDto);
				return users;
			} else{// 人员列表选择	
				String reDoUserSelect = nodeInfo.getReDoUserSelect();		
				if(ReDoUserSelect.HIS_USER.equals(reDoUserSelect)){// 重办人员选择配置了【上次办理人】，需要先获取节点已办历史，直接将节点办理人员设置为已办人员
					QueryModel model = new QueryModel();
					model.getCondition().put("instanceId", instanceId);
					model.getCondition().put("nodeId", nodeId);
					List<NWfUserDone> usersT = userDoneService.selectByModel(model);
					for(NWfUserDone userDone:usersT){
						WFUserDto userDto = userService.getUserInfo(systemId, userDone.getUserId());// 人员基本信息获取
						users.add(userDto);
					}
				}
				
				if(users.isEmpty()){ // 其他节点未办理过，依然走人员计算
					users = calculateUser(splitNodeUser(nodeInfo.getNodeUser()),nodeId,orgId,systemId);
				}
			}
		}
		return users;
	}
	
	@Override
	public List<WFUserDto> getNodeRealUsers(String instanceId, String nodeId, String orgId, String systemId) {
		List<WFUserDto> users = new ArrayList<WFUserDto>();
		
		NodeInfo nodeInfo = EngineCache.getNodeInfo(nodeId);
		String reDoUserSelect = nodeInfo.getReDoUserSelect();		
		if(ReDoUserSelect.HIS_USER.equals(reDoUserSelect)){// 重办人员选择配置了【上次办理人】，需要先获取节点已办历史，直接将节点办理人员设置为已办人员
			QueryModel model = new QueryModel();
			model.getCondition().put("instanceId", instanceId);
			model.getCondition().put("nodeId", nodeId);
			List<NWfUserDone> usersT = userDoneService.selectByModel(model);
			for(NWfUserDone userDone:usersT){
				WFUserDto userDto = userService.getUserInfo(systemId, userDone.getUserId());// 人员基本信息获取
				users.add(userDto);
			}
		}
		
		if(users.isEmpty()){ // 其他节点未办理过，依然走人员计算
			users = calculateUser(splitNodeUser(nodeInfo.getNodeUser()),nodeId,orgId,systemId);
		}
		
		// 判断节点是否配置了人员分配策略
		String strategyBeanName = nodeInfo.getStrategyBeanId();
		if (!isNullOrEmpty(strategyBeanName)) {
			// 执行人员[分配策略]
			return userFilterInterface.selectUser(strategyBeanName, users, instanceId, nodeId, orgId, systemId);
		}
		
		return users;
	}
	
	/**
	 * 运行节点路由
	 * @param instanceInfo
	 * @param nextNodeId
	 * @return
	 */
	private boolean runRoute(ResultInstanceDto instanceInfo,String nextNodeId,String isContinueBeanId){
		return routeService.run(instanceInfo,nextNodeId,isContinueBeanId);
	}
	
	/**
	 * 获取后续节点原始信息
	 * @param instanceInfo
	 * @param nodeId
	 * @return
	 */
	private List<NextNodeInfoDto> getWFNextNodeInfos(ResultInstanceDto instanceInfo,String nodeId){		
		NodeInfo nodeInfo = EngineCache.getNodeInfo(nodeId);
		
		// 路由到的节点放入返回的list中
		List<NodeInfo> nodeInfos = new ArrayList<NodeInfo>();
		for(String nodeIdT:nodeInfo.getNextNodes()){
			NodeInfo nodeInfoT = EngineCache.getNodeInfo(nodeIdT);											
			nodeInfos.add(nodeInfoT);
		}
		if(log.isDebugEnabled()){
			log.debug("获取节点条数:"+nodeInfos.size());
		}
		// 【条件单选】或者【条件多选】，需要运行脚本决定是否返回
		if(NodeType.CONDITION_MULTI_NODE.equals(nodeInfo.getNodeType())
				||NodeType.CONDITION_RADIO_NODE.equals(nodeInfo.getNodeType())){
			List<RouteInfo> routes = nodeInfo.getRouteInfos();
			for(RouteInfo route:routes){
				if(!isNullOrEmpty(route.getIsContinueBeanId())){
					String nextNodeId = route.getNextNodeId();
					if(!runRoute(instanceInfo,nextNodeId,route.getIsContinueBeanId())){
						NodeInfo nodeInfoT = EngineCache.getNodeInfo(nextNodeId);						
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
	 * 人员列表选择时的人员计算
	 * @param nodeInfo
	 * @param orgId 人员计算是上一办理人指定时，根据机构相对关系获取节点处理人员
	 * @return
	 */
	private List<WFUserDto> calculateUser(List<String> user, String nodeId, String orgId, String systemId) {
		List<WFUserDto> users = new ArrayList<WFUserDto>();

		NodeInfo nodeInfo = EngineCache.getNodeInfo(nodeId);
		String userComputeType = nodeInfo.getComputeType();// 获取节点配置的人员计算模式
		if(ComputeType.NOTALL.equals(userComputeType)){// 交集
			List<WFUserDto> usersT = new ArrayList<WFUserDto>();
			for (String key : user) {
				Collection<WFUserDto> usersTT = calculateUser(key,orgId,systemId);
				if(usersT.isEmpty()){
					usersT.addAll(usersTT);
				}else{
					usersT.retainAll(usersTT);
				}
			}
			users.addAll(usersT);
		}else{// 并集
			List<WFUserDto> usersT = new ArrayList<WFUserDto>();
			for (String key : user) {
				Collection<WFUserDto> usersTT = calculateUser(key,orgId,systemId);
				usersT.addAll(usersTT);
			}
			users.addAll(usersT);
		}
		log.debug("人员计算汇总结果:"+users);
		return users;
	}

	private Collection<WFUserDto> calculateUser(String key,String orgId,String systemId){
		Map<String, WFUserDto> usersT = new HashMap<String, WFUserDto>();
		if (key.startsWith(UserType.DEPT)) {// 部门
			String deptId = key.substring(2);
			List<WFUserDto> datas = userService.getUsersByDeptId(systemId, deptId);
			if (null != datas) {
				for (WFUserDto userT : datas) {
					usersT.put(userT.getUserId(), userT);
				}
			}
		} else if (key.startsWith(UserType.ORG)) {// 机构
			String orgIdT = key.substring(2);
			List<WFUserDto> datas = userService.getUsersByOrgId(systemId, orgIdT);
			if (null != datas) {
				for (WFUserDto userT : datas) {
					usersT.put(userT.getUserId(), userT);
				}
			}
		} else if (key.startsWith(UserType.GW)) {// 岗位
			String dutyId = key.substring(2);
			List<WFUserDto> datas = userService.getUsersByDutyId(systemId, dutyId);
			if (null != datas) {
				for (WFUserDto userT : datas) {
					usersT.put(userT.getUserId(), userT);
				}
			}
		} else if (key.startsWith(UserType.ROLE)) {// 角色
			String roleId = key.substring(2);
			List<WFUserDto> datas = userService.getUsersByRoleId(systemId, roleId);
			if (null != datas) {
				for (WFUserDto userT : datas) {
					usersT.put(userT.getUserId(), userT);
				}
			}
		} else if (key.startsWith(UserType.USER)) {// 用户
			String userId = key.substring(2);
			usersT.put(userId, userService.getUserInfo(systemId, userId));
		} else {
			log.warn("用户来源未知" + key);
		}
		log.debug("人员计算[" + key+"]:"+usersT.values());
		return usersT.values();
	}
	
	@Override
	@Transactional
	public List<ResultMessageDto> submit(WFSubmitDto submitDto) {
		List<ResultMessageDto> re = new ArrayList<ResultMessageDto>();
		// 保存评论
		saveComment(submitDto.getComment());
		String instanceId = submitDto.getComment().getInstanceId();
		String nodeId = submitDto.getComment().getNodeId();
		String orgId = submitDto.getOrgId();
		String userId = submitDto.getComment().getUserId();			
		if(log.isDebugEnabled()){
			log.debug("流程提交:[instanceId="+instanceId+";nodeId="+nodeId+";userId="+userId+"] to "+submitDto.getNextNodeInfos());
		}
		if(isNullOrEmpty(instanceId)||isNullOrEmpty(nodeId)||isNullOrEmpty(userId)||isNullOrEmpty(orgId)){
			ResultMessageDto m1 = new ResultMessageDto();
			m1.setCode("1");
			m1.setTip(Cons.ERROR_MSG1);
			re.add(m1);
			return re;
		}
		
		if (!checkNode(nodeId)){//校验节点是否有后续节点
			ResultMessageDto m1 = new ResultMessageDto();
			m1.setCode("1");
			m1.setTip(Cons.ERROR_MSG4);
			re.add(m1);
			return re;
		}
		
		ResultInstanceDto instanceInfo = getInstanceInfo(instanceId, nodeId,null);
		if(null==instanceInfo){// 流程已经办结
			ResultMessageDto m1 = new ResultMessageDto();
			m1.setCode("1");
			m1.setTip(Cons.ERROR_MSG2);
			re.add(m1);
			return re;
		}
		String systemId = instanceInfo.getSystemId();
		//判断提交者是否是当前流程提交人
		List<String> users = getCurrentNodeUsers(instanceId, nodeId);
		if(!isPartOf( users,userId)){
			ResultMessageDto m1 = new ResultMessageDto();
			m1.setCode("1");
			m1.setTip(Cons.ERROR_MSG3);
			re.add(m1);
			return re;
		}
		
		// 更新流程实例的参数
		try {
			updateInstanceParam(instanceId, submitDto.getParam());
		} catch (Exception e) {
			e.printStackTrace();
			ResultMessageDto m1 = new ResultMessageDto();
			m1.setCode("1");
			m1.setTip(Cons.ERROR_MSG12);
			re.add(m1);
			throw new WorkflowException(Cons.ERROR_MSG12);
			//return re;
		}
		
		//是否是最后一个处理人
		boolean isLast = (users.size()==1);	
		NodeInfo nodeInfo = EngineCache.getNodeInfo(nodeId);
		if(HandleType.ONE_SIGN.equals(nodeInfo.getHandleType())||HandleType.ONE.equals(nodeInfo.getHandleType())){// 【办理类型】是【单人签收办里】或【单人竞争】时,无论待办有多少人，强行提交
			isLast = true;
		}
		
		if(submitDto.isLastOne()){// 前端指定强行提交，一般【办理类型】是[多人顺序可结束]或[多人并行可结束]时生效，前端可以选择true和false;
			isLast = true;
		}
		
		// TODO 按条件转移操作操作
		if(isLast){// 是最后办理人,直接提交流程
			submitNextNodeMulti(instanceInfo, submitDto.getNextNodeInfos(),re,userId,orgId,systemId);
		}else{// 不是最后办理人，节点内流转
			 inNodeFlow(nodeInfo.getHandleType(),instanceInfo, submitDto.getNextNodeInfos(),re,userId,orgId,systemId);			 
		}
		return re;		
	}
	
	/**
	 * 更新扩展参数到流程实例表
	 * @param instanceId
	 * @param param
	 * @throws JsonProcessingException
	 */
	private void updateInstanceParam(String instanceId,Map<String,Object> param) {
		if(null!=param&&!param.isEmpty()){
			NWfInstance instanceInfo = instanceService.selectByPrimaryKey(instanceId);
			Map<String,Object> paramT = null;
			if(!isNullOrEmpty(instanceInfo.getFlowParam())){
				paramT = strToMap(instanceInfo.getFlowParam());
				paramT.putAll(param);
			}else{
				paramT = param;
			}
			NWfInstance record = new NWfInstance();
			record.setInstanceId(instanceId);
			record.setFlowParam(mapToStr(paramT));
			instanceService.updateByPrimaryKeySelective(record);
		}
	}
	
	/**
	 * 字符串转map
	 * @param content
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
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
	
	private  String mapToStr(Map<String,Object> param)throws WorkflowException{	
		try {
			if(null!=param&&!param.isEmpty()){
				return jsonObj.writeValueAsString(param);
			}
		} catch (Exception e) {
			throw new WorkflowException("流程公共参数格式化字符串异常:"+param);
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param instanceInfo
	 * @param nodeInfoDtos
	 * @param msg
	 */
	private void submitNextNodeMulti(ResultInstanceDto instanceInfo, List<NextNodeInfoDto> nodeInfoDtos, List<ResultMessageDto> msg,String currentUserId,String orgId,String systemId) throws WorkflowException {
		if (null == nodeInfoDtos || nodeInfoDtos.isEmpty()) {// 未指定节点从内存获取
			List<NextNodeInfoDto> nextNodes = getWFNextNodeInfos(instanceInfo,instanceInfo.getNodeId());
			for (NextNodeInfoDto nextNode : nextNodes) {			
				submitNextOneNode(instanceInfo, nextNode, msg,currentUserId,orgId,systemId);
			}
		} else {// 指定了节点则直接提交
			for(NextNodeInfoDto nextNodeDto:nodeInfoDtos){
				submitNextOneNode(instanceInfo, nextNodeDto, msg,currentUserId,orgId,systemId);
			}
		}
		
		String completeTime = TimeUtil.getDateyyyyMMddHHmmss();				
		// 用户待办信息迁移为已办
		userTodoBackup2Done(instanceInfo.getInstanceId(), instanceInfo.getNodeId(),currentUserId, completeTime);
		
		//userTodoService.deleteByPrimaryKey(instanceInfo.getInstanceId(), instanceInfo.getNodeId(),currentUserId);
		// 删除节点下所有用户待办
		workflowBackUpService.deleteUserTodo(instanceInfo.getInstanceId(), instanceInfo.getNodeId());
		
		// 节点实例并迁移到节点结束表
		nodeBackup( instanceInfo.getInstanceId(), instanceInfo.getNodeId(), completeTime);
		// 删除节点实例信息
		nodeRemove(instanceInfo.getInstanceId(), instanceInfo.getNodeId());
	}
	
	/**
	 * 节点内流转【此节点有多个办里人】
	 * @param instanceInfo
	 * @param nodeInfoDtos
	 * @param msg
	 * @param currentUserId
	 * @param orgId
	 * @param systemId
	 * @throws WorkflowException
	 */
	private void inNodeFlow(String handleType,ResultInstanceDto instanceInfo, List<NextNodeInfoDto> nodeInfoDtos, List<ResultMessageDto> msg,String currentUserId,String orgId,String systemId) throws WorkflowException {
		// 用户待办信息迁移为已办
		String instanceId = instanceInfo.getInstanceId();
		String nodeId = instanceInfo.getNodeId();
		userTodoBackup2Done(instanceId, nodeId,currentUserId, TimeUtil.getDateyyyyMMddHHmmss());		
		// 删除用户待办
		userTodoService.deleteByPrimaryKey(instanceId, nodeId,currentUserId);
		
		// 如果当前节点是办理类型是【多人顺序】或【多人顺序可结束】，则更新当前最小的用户等级【userLevel】为0,
		if(HandleType.MORE_SX.equals(handleType)||HandleType.MORE_SX_END.equals(handleType)){			
			List<NWfUserTodo> users = workflowCoreService.getUserByMinUserlevel(instanceId,nodeId);
			if(!users.isEmpty()){
				NWfUserTodo record = users.get(0);
				record.setUserLevel(0);
				userTodoService.updateByPrimaryKeySelective(record);
			}
		}
		
		ResultMessageDto re = new ResultMessageDto();
		re.setTip(Cons.SUCCESS_MSG9);
		re.setCode(FlowState.RUN);
		msg.add(re);
	}
	
	private void submitNextOneNode(ResultInstanceDto instanceInfo,NextNodeInfoDto nodeInfoDtos,List<ResultMessageDto> msg,String currentUserId,String orgId,String systemId) throws WorkflowException{
		String instanceId = instanceInfo.getInstanceId();
		String nextNodeId = nodeInfoDtos.getNextNodeId();
		String nodeId = instanceInfo.getNodeId();
		if(nextNodeId.contains(Cons.END_SIGN)){// 流程结束标识
			msg.add(end(instanceInfo,currentUserId,orgId));
		}
		
		// 判断节点类型
		NodeInfo nodeInfoT = EngineCache.getNodeInfo(nextNodeId);
		String nodeType = nodeInfoT.getNodeType();
		if(NodeType.END_NODE.equals(nodeType)){// 结束节点
			msg.add(end(instanceInfo,currentUserId,orgId));
		}else{	
			List<WFUserDto> nextNodeUsers = new ArrayList<WFUserDto>();;

			// 节点处理人
			List<String> users = nodeInfoDtos.getNextNodeUserIds();	
			users = removeSame(users);
			if(null==users||users.isEmpty()){// 提交请求未指定办理人，从流程图中获取
				nextNodeUsers = getNodeRealUsers(instanceId,nextNodeId,instanceInfo.getOrgId(),systemId);
			}else{
				if(users.size()==1&&users.get(0).equals(Cons.SYSTEM_USER_ID)){// 如果指定了节点处理人，并且是系统指定人员，需要重新进行人员计算
					nextNodeUsers = getNodeRealUsers(instanceId,nextNodeId,instanceInfo.getOrgId(),systemId);
				}else{// 指定了节点处理人并且不是系统指定人员，直接进行人员翻译
					for(String user:users){
						nextNodeUsers.add(userService.getUserInfo(systemId, user));
					}
				}
			}
			
			if(NodeType.AUTO_NODE.equals(nodeType)){// 自动节点
				//直接向自动节点的后面节点提交
				List<String> nextNodeIds = nodeInfoT.getNextNodes();
				NextNodeInfoDto nextNodeInfoDto = new NextNodeInfoDto();
				nextNodeInfoDto.setNextNodeId(nextNodeIds.get(0));
						
				afterSubmit(nodeInfoT,instanceInfo);// 自动节点后业务处理
				submitNextOneNode( instanceInfo, nextNodeInfoDto, msg, currentUserId, orgId, systemId);
			}else if(NodeType.TOGETHER_NODE.equals(nodeType)){// 汇总节点
				// 汇总节点没有被走过
				int count = workflowCoreService.getNodeDoneCount(instanceInfo.getInstanceId(), nextNodeId);				
				if(count==0){// 获取汇总节点后面的节点，往下提交
					// 获取汇总节点的下一节点
					NodeInfo togetherNode = EngineCache.getNodeInfo(nextNodeId);
					List<String> nextNodeIds = togetherNode.getNextNodes();
					// 新增汇总节点历史实例
					addTogetherNodeHis(instanceId, nodeId,nextNodeId);
					// 向汇总节点的下一节点提交，一般认为汇总节点的后面节点不会是汇总节点
					NextNodeInfoDto nextNodeInfoDto = new NextNodeInfoDto();
					nextNodeInfoDto.setNextNodeId(nextNodeIds.get(0));
					submitNextOneNode( instanceInfo, nextNodeInfoDto, msg, currentUserId, orgId, systemId);
				}else{// 就此中断，不在往下提交，前面会对此节点做历史数据迁移
					NodeInfo togetherNode = EngineCache.getNodeInfo(nextNodeId);
					ResultMessageDto re = new ResultMessageDto();
					re.setCode("0");
					re.setTip("汇总节点提交完成");
					re.setNodeName(togetherNode.getNodeName());
					msg.add(re);
				}				
			}else {// 非以上节点
				msg.add(complete(instanceInfo, nextNodeId,nextNodeUsers,currentUserId,orgId));
			}
		}
	}

	/**
	 * 添加汇总节点处理历史
	 * @param instanceId
	 * @param nodeId
	 * @param togetherNodeId
	 * @throws WorkflowException
	 */
	private void addTogetherNodeHis(String instanceId,String nodeId,String togetherNodeId) throws WorkflowException{
		NWfNode currentNodeInfo = nodeService.selectByPrimaryKey(instanceId,nodeId);		
		NWfNodeHis nodeInfoHis = new NWfNodeHis();
		BeanUtils.copyProperties(currentNodeInfo,nodeInfoHis);	
		String completeTime = TimeUtil.getDateyyyyMMddHHmmss();
		nodeInfoHis.setLastNodeId(togetherNodeId);
		nodeInfoHis.setLastNodeName("[汇总节点]");
		nodeInfoHis.setNodeId(togetherNodeId);
		nodeInfoHis.setNodeName("[汇总节点]");
		nodeInfoHis.setNodeState(OpType.AUTO);
		nodeInfoHis.setNodeType(NodeType.TOGETHER_NODE);
		nodeInfoHis.setEndTime(completeTime);		
		nodeHisService.insertSelective(nodeInfoHis);
	}
	
	private List<String> removeSame(List<String> users){
		List<String> usersT = new ArrayList<String>();
		Map<String,String> temp = new HashMap<String,String>();
		if(null==users||users.isEmpty()){
			return new ArrayList<String>();
		}
		for(String userId:users){
			temp.put(userId, userId);
		}
		usersT.addAll(temp.keySet());
		return usersT;
	}
	
	/**
	 * 流程提交
	 * @param instanceId
	 * @param nextNodeId
	 * @param nextNodeUsers
	 * @return
	 */
	public ResultMessageDto complete(ResultInstanceDto instanceInfo,String nextNodeId,List<WFUserDto> nextNodeUsers,String currentUserId,String orgId) throws WorkflowException{
		ResultMessageDto re = new ResultMessageDto();
		
		String instanceId = instanceInfo.getInstanceId();
		String nodeId = instanceInfo.getNodeId();
		if(log.isDebugEnabled()){
			log.debug("流程最终提交:[instanceId="+instanceId+";nodeId="+nodeId+";userId="+currentUserId+"] to "+nextNodeUsers);
		}
		if(isNullOrEmpty(instanceId)||isNullOrEmpty(nodeId)||isNullOrEmpty(currentUserId)||isNullOrEmpty(orgId)){
			ResultMessageDto m1 = new ResultMessageDto();
			m1.setCode("1");
			m1.setTip(Cons.ERROR_MSG1);
			throw new WorkflowException(Cons.ERROR_MSG1);
			//return re;
		}
		if(nextNodeUsers.isEmpty()){
			re.setTip(Cons.ERROR_MSG6);
			throw new WorkflowException(Cons.ERROR_MSG6);
			//return re;
		}
		
		String completeTime = TimeUtil.getDateyyyyMMddHHmmss();		
		NodeInfo nextNodeInfo = EngineCache.getNodeInfo(nextNodeId);	
		if(NodeType.END_NODE.equals(nextNodeInfo.getNodeType())){// 结束节点,提前结束
			return end(instanceInfo,currentUserId,orgId);
		}
	
		// 新增用户待办信息
		List<NWfUserTodo> userTodeNew = new ArrayList<NWfUserTodo>();
		for(WFUserDto wFUserDto:nextNodeUsers){
			NWfUserTodo userTodo = new NWfUserTodo();
			userTodo.setInstanceId(instanceId);
			userTodo.setLastUserId(currentUserId);
			userTodo.setLastUserName(userService.getUserInfo(instanceInfo.getSystemId(),currentUserId).getUserName());
			userTodo.setNodeId(nextNodeInfo.getNodeId());// 下一节点设置为当前节点
			userTodo.setStartTime(completeTime);
			userTodo.setUserId(wFUserDto.getUserId());
			userTodo.setUserName(wFUserDto.getUserName());
			userTodo.setSignIn(SignInType.NO_SIGN_IN);// 置签收状态为不需要签收
			userTodo.setUserLevel(0);
			userTodeNew.add(userTodo);
			re.getUserNames().add(wFUserDto.getUserName());// 设置返回待办人员名称
		}
		
		// 当前节点的处理人大于一个
		if(userTodeNew.size()>1){
			//且节点【办理类型】是【单人签收办里】时，用户签收状态需要更新为待签收
			if(HandleType.ONE_SIGN.equals(nextNodeInfo.getHandleType())){
				for(int i=0;i<userTodeNew.size();i++){
					userTodeNew.get(i).setSignIn(SignInType.TODO_SIGN_IN);
				}
			}
			
			// 且如果是【多人顺序办理】【多人顺序可结束】则对待办人员进行编号
			if(HandleType.MORE_SX.equals(nextNodeInfo.getHandleType())||HandleType.MORE_SX_END.equals(nextNodeInfo.getHandleType())){			
				for(int i=0;i<userTodeNew.size();i++){
					userTodeNew.get(i).setUserLevel(i);
				}
			}
		}
		workflowBackUpService.deleteUserTodo(instanceId,nextNodeId);// 删除下一节点存在的待办【如打回后重新回到的未处理的节点】
		workflowBackUpService.insertUserTodoBatch(userTodeNew);// 批量插入待办人员
		
		// 节点实例信息新增
		NWfNode currentNodeInfo = nodeService.selectByPrimaryKey(instanceId,nodeId);
		NWfNode nodeInfo = new NWfNode();
		BeanUtils.copyProperties(nextNodeInfo, nodeInfo);
		nodeInfo.setInstanceId(instanceId);
		nodeInfo.setNodeState(OpType.RUN);
		nodeInfo.setOrgId(orgId);
		nodeInfo.setLastNodeId(currentNodeInfo.getNodeId());
		nodeInfo.setLastNodeName(currentNodeInfo.getNodeName());
		nodeInfo.setStartTime(completeTime);
		nodeInfo.setNodeLevelTotal((currentNodeInfo.getNodeLevelTotal()==null?0:currentNodeInfo.getNodeLevelTotal())+getNodeLevel(nextNodeInfo.getNodeId()));// 节点等级加上
		
		re.setNodeName(nextNodeInfo.getNodeName());// 设置返回节点名称
		re.setNodeId(nextNodeInfo.getNodeId());// 设置返回节点id
		NWfNode nextNodeInfoT = nodeService.selectByPrimaryKey(instanceId, nextNodeId);
		if (null != nextNodeInfoT) {// 节点还有未处理的实例时，先删除
			nodeService.deleteByPrimaryKey(instanceId, nextNodeId);
		}
		nodeService.insertSelective(nodeInfo);
		
		re.setTip(Cons.SUCCESS_MSG7);
		re.setCode(FlowState.RUN);
		
		// 后业务处理
		NodeInfo currentNodeInfoT = EngineCache.getNodeInfo(nodeId);		
		afterSubmit(currentNodeInfoT,instanceInfo);
		// 发送消息
		sendMessage(userTodeNew,nextNodeInfo,instanceInfo);
		return re;
	}
	
	/**
	 * 获取节点等级
	 * @param nodeId
	 * @return
	 */
	private long getNodeLevel(String nodeId){
		NodeInfo nodeInfo = EngineCache.getNodeInfo(nodeId);		
		try {
			log.debug("节点等级["+nodeId+"]["+nodeInfo.getNodeLevel()+"]");
			long nodeLevel = Long.parseLong(nodeInfo.getNodeLevel());
			return nodeLevel;
		} catch (NumberFormatException e) {
			log.error("节点等级配置不是数字："+nodeId);
		}
		return 0;
	}
	
	/**
	 * 迁移用户待办到已办
	 * @param instanceId
	 * @param nodeId
	 * @param userId
	 * @param endTime
	 */
	private void userTodoBackup2Done(String instanceId,String nodeId,String userId,String endTime){
		NWfUserTodo userTodo = userTodoService.selectByPrimaryKey(instanceId, nodeId, userId);		
		if(null!=userTodo){
			NWfUserDone userDone = new NWfUserDone();
			BeanUtils.copyProperties(userTodo,userDone);// 当前待办用户迁移到历史表
			userDone.setEndTime(endTime);
			userDoneService.insert(userDone);
		}
	}
	
	/**
	 * 节点实例并迁移到历史表
	 * @param instanceId
	 * @param nodeId
	 * @param endTime
	 */
	private void nodeBackup(String instanceId,String nodeId,String endTime){		
		NWfNode currentNodeInfo = nodeService.selectByPrimaryKey(instanceId,nodeId);
		if(null!=currentNodeInfo){
			NWfNodeHis nodeInfoHis = new NWfNodeHis();
			BeanUtils.copyProperties(currentNodeInfo,nodeInfoHis);	
			nodeInfoHis.setEndTime(endTime);		
			nodeHisService.insertSelective(nodeInfoHis);
		}
	}
	
	/**
	 * 删除节点实例信息
	 * @param instanceId
	 * @param nodeId
	 */
	private void nodeRemove(String instanceId,String nodeId){				
		nodeService.deleteByPrimaryKey(instanceId, nodeId);		
	}
	
	/**
	 * 删除流程实例并迁移到历史表
	 * @param instanceId
	 * @param endTime
	 */
	private void instanceBackup(String instanceId,String endTime){		
		NWfInstance instanceInfoT = instanceService.selectByPrimaryKey(instanceId);
		instanceService.deleteByPrimaryKey(instanceId);
		NWfInstanceHis instanceHis = new NWfInstanceHis();
		BeanUtils.copyProperties(instanceInfoT, instanceHis);
		instanceHis.setEndTime(endTime);
		instanceHisService.insert(instanceHis);
	}
	
	/**
	 * 流程结束
	 * @param instanceInfo
	 * @return
	 */
	private ResultMessageDto end(ResultInstanceDto instanceInfo,String currentUserId,String orgId) throws WorkflowException{
		String instanceId = instanceInfo.getInstanceId();
		String nodeId = instanceInfo.getNodeId();
		String userId = currentUserId;
		String endTime = TimeUtil.getDateyyyyMMddHHmmss();
		// 删除流程实例并迁移到历史表
		instanceBackup(instanceId, endTime);
		
		// 节点实例迁移到节点结束表
		nodeBackup( instanceId, nodeId, endTime);
		// 删除所有节点实例
		workflowBackUpService.deleteAllNode(instanceId);
		
		// 用户待办信息迁移到已办表
		userTodoBackup2Done(instanceId,nodeId, userId, endTime);	
		
		// 迁移流程实例下所有已办到办结
		workflowBackUpService.transUSerDone2End(instanceId);
		// 删除所有已办
		workflowBackUpService.deleteAllUserDone(instanceId);
		// 删除所有待办
		workflowBackUpService.deleteAllUserTodo(instanceId);
		
		// 删除当前实例下的所有评论,并备份到end表
		workflowBackUpService.transUSerComment2End(instanceId);
		workflowBackUpService.deleteAllUserComment(instanceId);
		
		// 后业务处理
		NodeInfo nodeInfo = EngineCache.getNodeInfo(nodeId);
		afterSubmit(nodeInfo,instanceInfo);
		afterEnd(nodeInfo,instanceInfo);
		
		ResultMessageDto re = new ResultMessageDto();
		re.setTip(Cons.SUCCESS_MSG5);
		re.setCode(FlowState.END);
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
		NodeInfo nodeInfo = EngineCache.getNodeInfo(nodeId);
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
		if(!isNullOrEmpty(nodeUser)){
			String[] usersT = nodeUser.split(Cons.SPLIT);
			users = Arrays.asList(usersT);
		}
		return users;
	}

	@Override
	public ResultMessageDto signIn(String instanceId, String nodeId, String userId) {
		ResultMessageDto re = new ResultMessageDto();
		if(log.isDebugEnabled()){
			log.debug("流程签收:[instanceId="+instanceId+";nodeId="+nodeId+";userId="+userId+"]");
		}
		if(isNullOrEmpty(instanceId)||isNullOrEmpty(nodeId)||isNullOrEmpty(userId)){
			re.setCode("1");
			re.setTip(Cons.ERROR_MSG1);
			return re;
		}
		
		ResultInstanceDto instanceInfo = getInstanceInfo(instanceId, nodeId,null);
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
		
		// 先设置节点实例下所有人待办为不可见状态    【待办查询条件含 singin in (0,1,2)】
		NWfUserTodo record = new NWfUserTodo();
		record.setInstanceId(instanceId);
		record.setNodeId(nodeId);
		record.setSignIn(SignInType.EX_SIGN_IN);
		workflowCoreService.updateUserTodoByInstanceidNodeid(record);
		
		// 再设置自己为签收状态
		record.setSignIn(SignInType.HAVE_SIGN_IN);
		record.setUserId(userId);
		userTodoService.updateByPrimaryKeySelective(record);
		re.setCode("0");
		re.setTip(Cons.SUCCESS_MSG10);
		if(log.isDebugEnabled()){
			log.debug("流程签收:[instanceId="+instanceId+";nodeId="+nodeId+";userId="+userId+"]"+re);
		}
		return re;
	}

	@Override
	public ResultMessageDto unSignIn(String instanceId, String nodeId, String userId) {
		ResultMessageDto re = new ResultMessageDto();
		if(log.isDebugEnabled()){
			log.debug("流程撤销签收:[instanceId="+instanceId+";nodeId="+nodeId+";userId="+userId+"]");
		}
		if(isNullOrEmpty(instanceId)||isNullOrEmpty(nodeId)||isNullOrEmpty(userId)){
			re.setCode("1");
			re.setTip(Cons.ERROR_MSG1);
			return re;
		}
		
		ResultInstanceDto instanceInfo = getInstanceInfo(instanceId, nodeId,null);
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
		
		// 先设置节点实例下所有人待办为待签收状态 【待办查询条件含 singin in (0,1,2)】
		NWfUserTodo record = new NWfUserTodo();
		record.setInstanceId(instanceId);
		record.setNodeId(nodeId);
		record.setSignIn(SignInType.TODO_SIGN_IN);
		workflowCoreService.updateUserTodoByInstanceidNodeid(record);

		re.setCode("0");
		re.setTip(Cons.SUCCESS_MSG11);
		if(log.isDebugEnabled()){
			log.debug("流程撤销签收:[instanceId="+instanceId+";nodeId="+nodeId+";userId="+userId+"]"+re);
		}
		return re;
	}

	public static void main(String[] args) {
		List<WFUserDto> users1 = new ArrayList<WFUserDto>();
		WFUserDto user = new WFUserDto();
		user.setUserId("1");
		users1.add(user);
		
		List<WFUserDto> users2 = new ArrayList<WFUserDto>();
		WFUserDto user2 = new WFUserDto();
		user2.setUserId("1");
		users2.add(user2);
		
		WFUserDto user3 = new WFUserDto();
		user3.setUserId("3");		
		users2.add(user3);
		
		users2.removeAll(users1);// 并集
		users2.addAll(users1);
		
		
		users2.retainAll(users1);// 交集
		System.out.println(users2.size());


	}
}
