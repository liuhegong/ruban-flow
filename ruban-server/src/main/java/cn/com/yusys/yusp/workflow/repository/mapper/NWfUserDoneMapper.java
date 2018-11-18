package cn.com.yusys.yusp.workflow.repository.mapper;

import cn.com.yusys.yusp.workflow.domain.NWfUserDone;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NWfUserDoneMapper {
    int deleteByPrimaryKey(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId, @Param("userId") String userId);

    int insert(NWfUserDone record);

    int insertSelective(NWfUserDone record);

    NWfUserDone selectByPrimaryKey(@Param("instanceId") String instanceId, @Param("nodeId") String nodeId, @Param("userId") String userId);

    int updateByPrimaryKeySelective(NWfUserDone record);

    int updateByPrimaryKey(NWfUserDone record);

    List<NWfUserDone> selectByModel(QueryModel model);

    int deleteByIds(String instanceIds);
}