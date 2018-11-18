package cn.com.yusys.yusp.workflow.repository.mapper;

import cn.com.yusys.yusp.workflow.domain.NWfFlowHis;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import java.util.List;

public interface NWfFlowHisMapper {
    int deleteByPrimaryKey(Long flowId);

    int insert(NWfFlowHis record);

    int insertSelective(NWfFlowHis record);

    NWfFlowHis selectByPrimaryKey(Long flowId);

    int updateByPrimaryKeySelective(NWfFlowHis record);

    int updateByPrimaryKeyWithBLOBs(NWfFlowHis record);

    int updateByPrimaryKey(NWfFlowHis record);

    List<NWfFlowHis> selectByModel(QueryModel model);

    int deleteByIds(Long flowIds);
}