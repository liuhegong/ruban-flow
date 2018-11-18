package cn.com.yusys.yusp.workflow.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.yusys.yusp.workflow.domain.NWfUserTodo;

public interface WorkflowBackUpMapper {
	int backUpUserTodo(List<NWfUserTodo> data);
	int insertUserTodoBatch(List<NWfUserTodo> data);
    int deleteUserTodo(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId);
}