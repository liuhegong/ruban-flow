package cn.com.yusys.yusp.workflow.repository.mapper;

import cn.com.yusys.yusp.workflow.domain.NWfUserTodo;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NWfUserTodoMapper {
    int deleteByPrimaryKey(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId, @Param("userId") String userId);

    int insert(NWfUserTodo record);

    int insertSelective(NWfUserTodo record);

    NWfUserTodo selectByPrimaryKey(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId, @Param("userId") String userId);

    int updateByPrimaryKeySelective(NWfUserTodo record);

    int updateByPrimaryKey(NWfUserTodo record);

    List<NWfUserTodo> selectByModel(QueryModel model);

    int deleteByIds(String instanceIds);
}