package cn.com.yusys.yusp.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.workflow.domain.NWfUserTodo;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.repository.mapper.NWfUserTodoMapper;

@Service
public class NWfUserTodoService {
    @Autowired
    private NWfUserTodoMapper nWfUserTodoMapper;

    public int deleteByPrimaryKey(String instanceId, String nodeId, String userId) {
        return nWfUserTodoMapper.deleteByPrimaryKey(instanceId,nodeId,userId);
    }

    public int insert(NWfUserTodo record) {
        return nWfUserTodoMapper.insert(record);
    }

    public int insertSelective(NWfUserTodo record) {
        return nWfUserTodoMapper.insertSelective(record);
    }

    public NWfUserTodo selectByPrimaryKey(String instanceId, String nodeId, String userId) {
        return nWfUserTodoMapper.selectByPrimaryKey(instanceId,nodeId,userId);
    }

    public int updateByPrimaryKeySelective(NWfUserTodo record) {
        return nWfUserTodoMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NWfUserTodo record) {
        return nWfUserTodoMapper.updateByPrimaryKey(record);
    }

    public List<NWfUserTodo> selectByModel(QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<NWfUserTodo> list = nWfUserTodoMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }

    public int deleteByIds(String instanceIds) {
        return nWfUserTodoMapper.deleteByIds(instanceIds);
    }
}