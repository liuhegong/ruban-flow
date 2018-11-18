package cn.com.yusys.yusp.workflow.repository.mapper;

import cn.com.yusys.yusp.workflow.domain.NWfNode;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NWfNodeMapper {
    int deleteByPrimaryKey(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId);

    int insert(NWfNode record);

    int insertSelective(NWfNode record);

    NWfNode selectByPrimaryKey(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId);

    int updateByPrimaryKeySelective(NWfNode record);

    int updateByPrimaryKey(NWfNode record);

    List<NWfNode> selectByModel(QueryModel model);

    int deleteByIds(String instanceIds);
}