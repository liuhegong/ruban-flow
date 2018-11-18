package cn.com.yusys.yusp.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.workflow.domain.NWfUserDone;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.repository.mapper.NWfUserDoneMapper;

@Service
public class NWfUserDoneService {
    @Autowired
    private NWfUserDoneMapper nWfUserDoneMapper;

    public int deleteByPrimaryKey(String instanceId, String nodeId, String userId) {
        return nWfUserDoneMapper.deleteByPrimaryKey(instanceId,nodeId,userId);
    }

    public int insert(NWfUserDone record) {
        return nWfUserDoneMapper.insert(record);
    }

    public int insertSelective(NWfUserDone record) {
        return nWfUserDoneMapper.insertSelective(record);
    }

    public NWfUserDone selectByPrimaryKey(String instanceId, String nodeId, String userId) {
        return nWfUserDoneMapper.selectByPrimaryKey(instanceId,nodeId,userId);
    }

    public int updateByPrimaryKeySelective(NWfUserDone record) {
        return nWfUserDoneMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NWfUserDone record) {
        return nWfUserDoneMapper.updateByPrimaryKey(record);
    }

    public List<NWfUserDone> selectByModel(QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<NWfUserDone> list = nWfUserDoneMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }

    public int deleteByIds(String instanceIds) {
        return nWfUserDoneMapper.deleteByIds(instanceIds);
    }
}