package cn.com.yusys.yusp.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.workflow.domain.NWfUserHis;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.repository.mapper.NWfUserHisMapper;

@Service
public class NWfUserHisService {
    @Autowired
    private NWfUserHisMapper nWfUserHisMapper;

    public int deleteByPrimaryKey(String instanceId, String nodeId, String userId) {
        return nWfUserHisMapper.deleteByPrimaryKey(instanceId,nodeId,userId);
    }

    public int insert(NWfUserHis record) {
        return nWfUserHisMapper.insert(record);
    }

    public int insertSelective(NWfUserHis record) {
        return nWfUserHisMapper.insertSelective(record);
    }

    public NWfUserHis selectByPrimaryKey(String instanceId, String nodeId, String userId) {
        return nWfUserHisMapper.selectByPrimaryKey(instanceId,nodeId,userId);
    }

    public int updateByPrimaryKeySelective(NWfUserHis record) {
        return nWfUserHisMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NWfUserHis record) {
        return nWfUserHisMapper.updateByPrimaryKey(record);
    }

    public List<NWfUserHis> selectByModel(QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<NWfUserHis> list = nWfUserHisMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }

    public int deleteByIds(String instanceIds) {
        return nWfUserHisMapper.deleteByIds(instanceIds);
    }
}