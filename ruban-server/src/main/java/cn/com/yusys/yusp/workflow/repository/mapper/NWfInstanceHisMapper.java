package cn.com.yusys.yusp.workflow.repository.mapper;

import cn.com.yusys.yusp.workflow.domain.NWfInstanceHis;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import java.util.List;

public interface NWfInstanceHisMapper {
    int deleteByPrimaryKey(String instanceId);

    int insert(NWfInstanceHis record);

    int insertSelective(NWfInstanceHis record);

    NWfInstanceHis selectByPrimaryKey(String instanceId);

    int updateByPrimaryKeySelective(NWfInstanceHis record);

    int updateByPrimaryKey(NWfInstanceHis record);

    List<NWfInstanceHis> selectByModel(QueryModel model);

    int deleteByIds(String instanceIds);
}