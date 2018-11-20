package cn.com.yusys.yusp.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.workflow.domain.NWfNode;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.repository.mapper.NWfNodeMapper;

@Service
@Transactional
public class NWfNodeService {
    @Autowired
    private NWfNodeMapper nWfNodeMapper;

    public int deleteByPrimaryKey(String instanceId, String nodeId) {
        return nWfNodeMapper.deleteByPrimaryKey(instanceId,nodeId);
    }

    public int insert(NWfNode record) {
        return nWfNodeMapper.insert(record);
    }

    public int insertSelective(NWfNode record) {
        return nWfNodeMapper.insertSelective(record);
    }

    public NWfNode selectByPrimaryKey(String instanceId, String nodeId) {
        return nWfNodeMapper.selectByPrimaryKey(instanceId,nodeId);
    }

    public int updateByPrimaryKeySelective(NWfNode record) {
        return nWfNodeMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NWfNode record) {
        return nWfNodeMapper.updateByPrimaryKey(record);
    }

    public List<NWfNode> selectByModel(QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<NWfNode> list = nWfNodeMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }

    public int deleteByIds(String instanceIds) {
        return nWfNodeMapper.deleteByIds(instanceIds);
    }
}