package cn.com.yusys.yusp.workflow.repository.mapper;

import cn.com.yusys.yusp.workflow.domain.NWfCommentHis;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import java.util.List;

public interface NWfCommentHisMapper {
    int deleteByPrimaryKey(String commentId);

    int insert(NWfCommentHis record);

    int insertSelective(NWfCommentHis record);

    NWfCommentHis selectByPrimaryKey(String commentId);

    int updateByPrimaryKeySelective(NWfCommentHis record);

    int updateByPrimaryKeyWithBLOBs(NWfCommentHis record);

    int updateByPrimaryKey(NWfCommentHis record);

    List<NWfCommentHis> selectByModel(QueryModel model);

    int deleteByIds(String commentIds);
}