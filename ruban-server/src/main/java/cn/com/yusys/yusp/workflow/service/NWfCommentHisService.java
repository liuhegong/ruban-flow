package cn.com.yusys.yusp.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.workflow.domain.NWfCommentHis;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.repository.mapper.NWfCommentHisMapper;

@Service
public class NWfCommentHisService {
    @Autowired
    private NWfCommentHisMapper nWfCommentHisMapper;

    public int deleteByPrimaryKey(String commentId) {
        return nWfCommentHisMapper.deleteByPrimaryKey(commentId);
    }

    public int insert(NWfCommentHis record) {
        return nWfCommentHisMapper.insert(record);
    }

    public int insertSelective(NWfCommentHis record) {
        return nWfCommentHisMapper.insertSelective(record);
    }

    public NWfCommentHis selectByPrimaryKey(String commentId) {
        return nWfCommentHisMapper.selectByPrimaryKey(commentId);
    }

    public int updateByPrimaryKeySelective(NWfCommentHis record) {
        return nWfCommentHisMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NWfCommentHis record) {
        return nWfCommentHisMapper.updateByPrimaryKey(record);
    }

    public List<NWfCommentHis> selectByModel(QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<NWfCommentHis> list = nWfCommentHisMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }

    public int deleteByIds(String commentIds) {
        return nWfCommentHisMapper.deleteByIds(commentIds);
    }
}