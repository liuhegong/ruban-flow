package cn.com.yusys.yusp.workflow.repository.mapper;

import cn.com.yusys.yusp.workflow.domain.NWfUserHis;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NWfUserHisMapper {
    int deleteByPrimaryKey(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId, @Param("userId") String userId);

    int insert(NWfUserHis record);

    int insertSelective(NWfUserHis record);

    NWfUserHis selectByPrimaryKey(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId, @Param("userId") String userId);

    int updateByPrimaryKeySelective(NWfUserHis record);

    int updateByPrimaryKey(NWfUserHis record);

    List<NWfUserHis> selectByModel(QueryModel model);

    int deleteByIds(String instanceIds);
}