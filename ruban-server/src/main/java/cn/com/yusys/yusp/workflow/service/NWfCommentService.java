package cn.com.yusys.yusp.workflow.service;

import cn.com.yusys.yusp.workflow.domain.NWfComment;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.repository.mapper.NWfCommentMapper;
import com.github.pagehelper.PageHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NWfCommentService {
    @Autowired
    private NWfCommentMapper nWfCommentMapper;

    public int deleteByPrimaryKey(String commentId) {
        return nWfCommentMapper.deleteByPrimaryKey(commentId);
    }

    public int insert(NWfComment record) {
        return nWfCommentMapper.insert(record);
    }

    public int insertSelective(NWfComment record) {
        return nWfCommentMapper.insertSelective(record);
    }

    public NWfComment selectByPrimaryKey(String commentId) {
        return nWfCommentMapper.selectByPrimaryKey(commentId);
    }

    public int updateByPrimaryKeySelective(NWfComment record) {
        return nWfCommentMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NWfComment record) {
        return nWfCommentMapper.updateByPrimaryKey(record);
    }

    public List<NWfComment> selectByModel(QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<NWfComment> list = nWfCommentMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }

    public int deleteByIds(String commentIds) {
        return nWfCommentMapper.deleteByIds(commentIds);
    }
}