package cn.com.yusys.yusp.workflow.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.yusys.yusp.workflow.domain.NWfUserTodo;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceTodoDto;

public interface WorkflowCoreMapper {
	ResultInstanceDto getInstanceInfo(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId);
	
	List<String> getNodeUsers(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId);
	
	/**
	 * 获取最小用户等级的用户待办
	 * @param instanceId
	 * @param nodeId
	 * @return
	 */
	List<NWfUserTodo> getUserByMinUserlevel(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId);

	/**
	 * 用户待办实例查询
	 * @param queryModel
	 * @return
	 */
	List<ResultInstanceTodoDto> getInstanceInfoTodo(QueryModel queryModel);
	/**
	 * 根据节点id和实例id更新待办用户信息
	 * @param nWfUserTodo
	 * @return
	 */
	int updateUserTodoByInstanceidNodeid(NWfUserTodo nWfUserTodo);
	
	/**
	 * 获取实例id下节点等级做大的节点id
	 * @param instanceId
	 * @return
	 */
	List<String> getMaxLevelNodeId(@Param("instanceId") String instanceId);

}