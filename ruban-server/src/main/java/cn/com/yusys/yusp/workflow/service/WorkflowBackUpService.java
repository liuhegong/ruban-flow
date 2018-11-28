package cn.com.yusys.yusp.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.workflow.domain.NWfUserTodo;
import cn.com.yusys.yusp.workflow.repository.mapper.WorkflowBackUpMapper;

@Service
public class WorkflowBackUpService {
	@Autowired
	private WorkflowBackUpMapper workflowBackUpMapper;
	
	public int transUSerDone2End(String instanceId){
		return workflowBackUpMapper.transUSerDone2End(instanceId);
	}
	public int insertUserTodoBatch(List<NWfUserTodo> data){
		return workflowBackUpMapper.insertUserTodoBatch(data);
	}
	public int deleteUserTodo(String instanceId, String nodeId){
		return workflowBackUpMapper.deleteUserTodo(instanceId,nodeId);
	}
	public int deleteAllUserDone(String instanceId){
		return workflowBackUpMapper.deleteAllUserDone(instanceId);
	}
	public int transUSerComment2End(String instanceId){
		return workflowBackUpMapper.transUSerComment2End(instanceId);
	}
	public int deleteAllUserComment(String instanceId){
		return workflowBackUpMapper.deleteAllUserComment(instanceId);
	}
	
	public int deleteAllUserTodo(String instanceId){
		return workflowBackUpMapper.deleteAllUserTodo(instanceId);
	}
	
	public int deleteAllNode(String instanceId){
		return workflowBackUpMapper.deleteAllNode(instanceId);
	}
}