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
	
	public int backUpUserTodo(List<NWfUserTodo> data){
		return workflowBackUpMapper.backUpUserTodo(data);
	}
	public int insertUserTodoBatch(List<NWfUserTodo> data){
		return workflowBackUpMapper.insertUserTodoBatch(data);
	}
	public int deleteUserTodo(String instanceId, String nodeId){
		return workflowBackUpMapper.deleteUserTodo(instanceId,nodeId);
	}
}