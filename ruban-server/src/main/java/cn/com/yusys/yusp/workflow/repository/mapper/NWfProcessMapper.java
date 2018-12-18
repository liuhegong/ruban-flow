package cn.com.yusys.yusp.workflow.repository.mapper;

import cn.com.yusys.yusp.workflow.domain.NWfProcess;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import java.util.List;

public interface NWfProcessMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(NWfProcess record);

    int insertSelective(NWfProcess record);

    NWfProcess selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(NWfProcess record);

    int updateByPrimaryKey(NWfProcess record);

    List<NWfProcess> selectByModel(QueryModel model);

    int deleteByIds(String pkIds);
}