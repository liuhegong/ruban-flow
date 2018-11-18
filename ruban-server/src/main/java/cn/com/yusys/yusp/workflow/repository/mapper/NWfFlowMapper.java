package cn.com.yusys.yusp.workflow.repository.mapper;

import cn.com.yusys.yusp.workflow.domain.NWfFlow;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import java.util.List;

public interface NWfFlowMapper {
    int deleteByPrimaryKey(Long flowId);

    int insert(NWfFlow record);

    int insertSelective(NWfFlow record);

    NWfFlow selectByPrimaryKey(Long flowId);

    int updateByPrimaryKeySelective(NWfFlow record);

    int updateByPrimaryKeyWithBLOBs(NWfFlow record);

    int updateByPrimaryKey(NWfFlow record);

    List<NWfFlow> selectByModel(QueryModel model);

    int deleteByIds(Long flowIds);
    
    Integer maxVersion(Long flowId);
    
    Integer maxFlowId();
}