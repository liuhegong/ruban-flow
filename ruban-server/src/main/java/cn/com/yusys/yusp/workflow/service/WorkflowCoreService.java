package cn.com.yusys.yusp.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.repository.mapper.WorkflowCoreMapper;

@Service
public class WorkflowCoreService {
    @Autowired
    private WorkflowCoreMapper workflowCoreMapper;

    public ResultInstanceDto getInstanceInfo(String instanceId,String nodeId){
    	return workflowCoreMapper.getInstanceInfo(instanceId, nodeId);
    }
    
	public List<String> getNodeUsers(String instanceId, String nodeId){
		return workflowCoreMapper.getNodeUsers( instanceId,  nodeId);
	}


}