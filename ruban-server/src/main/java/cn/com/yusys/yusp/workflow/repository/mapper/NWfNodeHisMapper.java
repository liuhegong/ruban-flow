package cn.com.yusys.yusp.workflow.repository.mapper;

import cn.com.yusys.yusp.workflow.domain.NWfNodeHis;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NWfNodeHisMapper {
    int deleteByPrimaryKey(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId);

    int insert(NWfNodeHis record);

    int insertSelective(NWfNodeHis record);

    NWfNodeHis selectByPrimaryKey(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId);

    int updateByPrimaryKeySelective(NWfNodeHis record);

    int updateByPrimaryKey(NWfNodeHis record);

    List<NWfNodeHis> selectByModel(QueryModel model);

    int deleteByIds(String instanceIds);
}