package cn.com.yusys.yusp.workflow.service;

import cn.com.yusys.yusp.workflow.domain.NWfInstanceHis;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.repository.mapper.NWfInstanceHisMapper;
import com.github.pagehelper.PageHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NWfInstanceHisService {
    @Autowired
    private NWfInstanceHisMapper nWfInstanceHisMapper;

    public int deleteByPrimaryKey(String instanceId) {
        return nWfInstanceHisMapper.deleteByPrimaryKey(instanceId);
    }

    public int insert(NWfInstanceHis record) {
        return nWfInstanceHisMapper.insert(record);
    }

    public int insertSelective(NWfInstanceHis record) {
        return nWfInstanceHisMapper.insertSelective(record);
    }

    public NWfInstanceHis selectByPrimaryKey(String instanceId) {
        return nWfInstanceHisMapper.selectByPrimaryKey(instanceId);
    }

    public int updateByPrimaryKeySelective(NWfInstanceHis record) {
        return nWfInstanceHisMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NWfInstanceHis record) {
        return nWfInstanceHisMapper.updateByPrimaryKey(record);
    }

    public List<NWfInstanceHis> selectByModel(QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<NWfInstanceHis> list = nWfInstanceHisMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }

    public int deleteByIds(String instanceIds) {
        return nWfInstanceHisMapper.deleteByIds(instanceIds);
    }
}