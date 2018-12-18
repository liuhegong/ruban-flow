package cn.com.yusys.yusp.workflow.repository.mapper;

import cn.com.yusys.yusp.workflow.domain.NWfNodeDone;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NWfNodeDoneMapper {
    int deleteByPrimaryKey(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId);

    int insert(NWfNodeDone record);

    int insertSelective(NWfNodeDone record);

    NWfNodeDone selectByPrimaryKey(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId);

    int updateByPrimaryKeySelective(NWfNodeDone record);

    int updateByPrimaryKey(NWfNodeDone record);

    List<NWfNodeDone> selectByModel(QueryModel model);

    int deleteByIds(String instanceIds);
}