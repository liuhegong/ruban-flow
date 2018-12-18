package cn.com.yusys.yusp.workflow.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.yusys.yusp.workflow.domain.NWfUserTodo;

public interface WorkflowBackUpMapper {
	int insertUserTodoBatch(List<NWfUserTodo> data);
    int deleteUserTodo(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId);
    int transUSerDone2End(String instanceId);
    int transUSerComment2End(String instanceId);
    int deleteAllUserDone(@Param("instanceId") String instanceId);
    int deleteAllUserComment(@Param("instanceId") String instanceId);
    
    int deleteAllUserTodo(@Param("instanceId") String instanceId);
    int deleteAllNode(@Param("instanceId") String instanceId);
    int deleteAllNodeDone(@Param("instanceId") String instanceId);
    int transNodeDone2End(String instanceId);  
}