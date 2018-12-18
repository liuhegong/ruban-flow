package cn.com.yusys.yusp.workflow.service;

import cn.com.yusys.yusp.workflow.domain.NWfNodeDone;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.repository.mapper.NWfNodeDoneMapper;
import com.github.pagehelper.PageHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NWfNodeDoneService {
    @Autowired
    private NWfNodeDoneMapper nWfNodeDoneMapper;

    public int deleteByPrimaryKey(String instanceId, String nodeId) {
        return nWfNodeDoneMapper.deleteByPrimaryKey(instanceId,nodeId);
    }

    public int insert(NWfNodeDone record) {
        return nWfNodeDoneMapper.insert(record);
    }

    public int insertSelective(NWfNodeDone record) {
        return nWfNodeDoneMapper.insertSelective(record);
    }

    public NWfNodeDone selectByPrimaryKey(String instanceId, String nodeId) {
        return nWfNodeDoneMapper.selectByPrimaryKey(instanceId,nodeId);
    }

    public int updateByPrimaryKeySelective(NWfNodeDone record) {
        return nWfNodeDoneMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NWfNodeDone record) {
        return nWfNodeDoneMapper.updateByPrimaryKey(record);
    }

    public List<NWfNodeDone> selectByModel(QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<NWfNodeDone> list = nWfNodeDoneMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }

    public int deleteByIds(String instanceIds) {
        return nWfNodeDoneMapper.deleteByIds(instanceIds);
    }
}