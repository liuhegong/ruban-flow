package cn.com.yusys.yusp.workflow.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;

public interface WorkflowCoreMapper {
	ResultInstanceDto getInstanceInfo(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId);
	
	List<String> getNodeUsers(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId);
}