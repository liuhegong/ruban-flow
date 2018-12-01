package cn.com.yusys.yusp.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.workflow.domain.NWfFlowHis;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.repository.mapper.NWfFlowHisMapper;

@Service
public class NWfFlowHisService {
    @Autowired
    private NWfFlowHisMapper nWfFlowHisMapper;

    public int deleteByPrimaryKey(Long flowId) {
        return nWfFlowHisMapper.deleteByPrimaryKey(flowId);
    }

    public int insert(NWfFlowHis record) {
        return nWfFlowHisMapper.insert(record);
    }

    public int insertSelective(NWfFlowHis record) {
        return nWfFlowHisMapper.insertSelective(record);
    }

    public NWfFlowHis selectByPrimaryKey(Long flowId) {
        return nWfFlowHisMapper.selectByPrimaryKey(flowId);
    }

    public int updateByPrimaryKeySelective(NWfFlowHis record) {
        return nWfFlowHisMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NWfFlowHis record) {
        return nWfFlowHisMapper.updateByPrimaryKey(record);
    }

    public List<NWfFlowHis> selectByModel(QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<NWfFlowHis> list = nWfFlowHisMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }

    public int deleteByIds(Long flowIds) {
        return nWfFlowHisMapper.deleteByIds(flowIds);
    }
}