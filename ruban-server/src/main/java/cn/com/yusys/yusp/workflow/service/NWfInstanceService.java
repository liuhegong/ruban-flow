package cn.com.yusys.yusp.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.workflow.domain.NWfInstance;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.repository.mapper.NWfInstanceMapper;

@Service
public class NWfInstanceService {
    @Autowired
    private NWfInstanceMapper nWfInstanceMapper;

    public int deleteByPrimaryKey(String instanceId) {
        return nWfInstanceMapper.deleteByPrimaryKey(instanceId);
    }

    public int insert(NWfInstance record) {
        return nWfInstanceMapper.insert(record);
    }

    public int insertSelective(NWfInstance record) {
        return nWfInstanceMapper.insertSelective(record);
    }

    public NWfInstance selectByPrimaryKey(String instanceId) {
        return nWfInstanceMapper.selectByPrimaryKey(instanceId);
    }

    public int updateByPrimaryKeySelective(NWfInstance record) {
        return nWfInstanceMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NWfInstance record) {
        return nWfInstanceMapper.updateByPrimaryKey(record);
    }

    public List<NWfInstance> selectByModel(QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<NWfInstance> list = nWfInstanceMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }

    public int deleteByIds(String instanceIds) {
        return nWfInstanceMapper.deleteByIds(instanceIds);
    }
}