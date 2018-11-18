package cn.com.yusys.yusp.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.workflow.domain.NWfNodeHis;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.repository.mapper.NWfNodeHisMapper;

@Service
public class NWfNodeHisService {
    @Autowired
    private NWfNodeHisMapper nWfNodeHisMapper;

    public int deleteByPrimaryKey(String instanceId, String nodeId) {
        return nWfNodeHisMapper.deleteByPrimaryKey(instanceId,nodeId);
    }

    public int insert(NWfNodeHis record) {
        return nWfNodeHisMapper.insert(record);
    }

    public int insertSelective(NWfNodeHis record) {
        return nWfNodeHisMapper.insertSelective(record);
    }

    public NWfNodeHis selectByPrimaryKey(String instanceId, String nodeId) {
        return nWfNodeHisMapper.selectByPrimaryKey(instanceId,nodeId);
    }

    public int updateByPrimaryKeySelective(NWfNodeHis record) {
        return nWfNodeHisMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NWfNodeHis record) {
        return nWfNodeHisMapper.updateByPrimaryKey(record);
    }

    public List<NWfNodeHis> selectByModel(QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<NWfNodeHis> list = nWfNodeHisMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }

    public int deleteByIds(String instanceIds) {
        return nWfNodeHisMapper.deleteByIds(instanceIds);
    }
}