package cn.com.yusys.yusp.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.workflow.domain.NWfFlow;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.repository.mapper.NWfFlowMapper;

@Service
public class NWfFlowService {
    @Autowired
    private NWfFlowMapper nWfFlowMapper;

    public int deleteByPrimaryKey(Long flowId) {
        return nWfFlowMapper.deleteByPrimaryKey(flowId);
    }

    public int insert(NWfFlow record) {
        return nWfFlowMapper.insert(record);
    }

    public int insertSelective(NWfFlow record) {
        return nWfFlowMapper.insertSelective(record);
    }

    public NWfFlow selectByPrimaryKey(Long flowId) {
        return nWfFlowMapper.selectByPrimaryKey(flowId);
    }

    public int updateByPrimaryKeySelective(NWfFlow record) {
        return nWfFlowMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(NWfFlow record) {
        return nWfFlowMapper.updateByPrimaryKey(record);
    }

    public List<NWfFlow> selectByModel(QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<NWfFlow> list = nWfFlowMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }

    public int deleteByIds(Long flowIds) {
        return nWfFlowMapper.deleteByIds(flowIds);
    }
    
    public Integer maxVersion(Long flowId){
    	return nWfFlowMapper.maxVersion(flowId);
    }
    
    public Integer maxFlowId(){
    	return nWfFlowMapper.maxFlowId();
    } 
}