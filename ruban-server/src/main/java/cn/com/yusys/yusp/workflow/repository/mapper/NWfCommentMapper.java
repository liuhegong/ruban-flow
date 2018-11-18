package cn.com.yusys.yusp.workflow.repository.mapper;

import cn.com.yusys.yusp.workflow.domain.NWfComment;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import java.util.List;

public interface NWfCommentMapper {
    int deleteByPrimaryKey(String commentId);

    int insert(NWfComment record);

    int insertSelective(NWfComment record);

    NWfComment selectByPrimaryKey(String commentId);

    int updateByPrimaryKeySelective(NWfComment record);

    int updateByPrimaryKeyWithBLOBs(NWfComment record);

    int updateByPrimaryKey(NWfComment record);

    List<NWfComment> selectByModel(QueryModel model);

    int deleteByIds(String commentIds);
}