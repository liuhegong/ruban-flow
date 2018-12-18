package cn.com.yusys.yusp.workflow.service;

import cn.com.yusys.yusp.workflow.domain.NWfProcess;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.repository.mapper.NWfProcessMapper;
import com.github.pagehelper.PageHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NWfProcessService {
    @Autowired
    private NWfProcessMapper nWfProcessMapper;

    public int deleteByPrimaryKey(String pkId) {
        return nWfProcessMapper.deleteByPrimaryKey(pkId);
    }

    public int insert(NWfProcess record) {
        return nWfProcessMapper.insert(record);
    }

    public int insertSelective(NWfProcess record) {
        return nWfProcessMapper.insertSelective(record);
    }

    public NWfProcess selectByPrimaryKey(String pkId) {
        return nWfProcessMapper.selectByPrimaryKey(pkId);
    }

    public int updateByPrimaryKeySelective(NWfProcess record) {
        return nWfProcessMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NWfProcess record) {
        return nWfProcessMapper.updateByPrimaryKey(record);
    }

    public List<NWfProcess> selectByModel(QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<NWfProcess> list = nWfProcessMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }

    public int deleteByIds(String pkIds) {
        return nWfProcessMapper.deleteByIds(pkIds);
    }
}