package cn.com.yusys.yusp.workflow.repository.mapper;

import cn.com.yusys.yusp.workflow.domain.NWfInstance;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import java.util.List;

public interface NWfInstanceMapper {
    int deleteByPrimaryKey(String instanceId);

    int insert(NWfInstance record);

    int insertSelective(NWfInstance record);

    NWfInstance selectByPrimaryKey(String instanceId);

    int updateByPrimaryKeySelective(NWfInstance record);

    int updateByPrimaryKey(NWfInstance record);

    List<NWfInstance> selectByModel(QueryModel model);

    int deleteByIds(String instanceIds);
}