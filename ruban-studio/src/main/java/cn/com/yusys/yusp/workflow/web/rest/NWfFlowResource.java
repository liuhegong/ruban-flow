package cn.com.yusys.yusp.workflow.web.rest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.workflow.core.node.type.FlowState;
import cn.com.yusys.yusp.workflow.core.util.TimeUtil;
import cn.com.yusys.yusp.workflow.domain.NWfFlow;
import cn.com.yusys.yusp.workflow.domain.NWfFlowHis;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.service.NWfFlowHisService;
import cn.com.yusys.yusp.workflow.service.NWfFlowService;
import cn.com.yusys.yusp.workflow.util.FileUtil;
import cn.com.yusys.yusp.workflow.web.dto.ResultDto;

@RestController
@RequestMapping("/api/nwfflow")
public class NWfFlowResource {
	
	@Value("${flow.path}")
	private String flowPath = null;
	
    @Autowired
    private NWfFlowService nWfFlowService;
    
    @Autowired
    private NWfFlowHisService nWfFlowHisService;
    
    @GetMapping("/")
    protected ResultDto<List<NWfFlow>> index(String systemId,String userId) {
        QueryModel queryModel = new QueryModel();
        queryModel.getCondition().put("systemId", systemId);
        queryModel.getCondition().put("flowAdmin", userId);
        queryModel.setSort("start_time desc");
		List<NWfFlow> list = nWfFlowService.selectByModel(queryModel );
        return new ResultDto<List<NWfFlow>>(list);
    }

    @PostMapping("/")
    protected ResultDto<NWfFlow> create(@RequestBody NWfFlow nWfFlow) {
    	nWfFlow.setStartTime(TimeUtil.getDateyyyyMMddHHmmss());
    	nWfFlow.setFlowState(FlowState.RUN);
    	nWfFlow.setFlowVersion(0l);
    	Integer flowId = nWfFlowService.maxFlowId();
    	if(null==flowId){
    		nWfFlow.setFlowId(1l);	
    	}else{
    		nWfFlow.setFlowId(flowId+1l);
    	}
        nWfFlowService.insert(nWfFlow);
        return new ResultDto<NWfFlow>(nWfFlow);
    }
    
    @GetMapping("/{flowId}")
    public ResultDto<NWfFlow> show(@PathVariable Long flowId) {
        NWfFlow nWfFlow = nWfFlowService.selectByPrimaryKey(flowId);
        return new ResultDto<NWfFlow>(nWfFlow);
    }


    @PostMapping("/update")
    @Transactional
    protected ResultDto<Integer> update(@RequestBody NWfFlow nWfFlow) throws IOException {
        
    	NWfFlow currentFlow = nWfFlowService.selectByPrimaryKey(nWfFlow.getFlowId());
    	    	
    	nWfFlowService.deleteByPrimaryKey(nWfFlow.getFlowId());
    	nWfFlow.setStartTime(TimeUtil.getDateyyyyMMddHHmmss());
    	nWfFlow.setFlowState(FlowState.RUN); 
    	nWfFlow.setFlowName(currentFlow.getFlowName());
    	long version = currentFlow.getFlowVersion();
    	nWfFlow.setFlowVersion(version+1l);
    	nWfFlowService.insert(nWfFlow);
    	   	
    	NWfFlowHis record = new NWfFlowHis();
    	BeanUtils.copyProperties(currentFlow, record);
		nWfFlowHisService.insert(record);
    	
		FileUtil.writeNewContentToFile(flowPath+File.separator+record.getFlowId()+".xml", record.getFlowContent());
		
        return new ResultDto<Integer>(0);
    }

    @PostMapping("/delete/{flowId}")
    protected ResultDto<Integer> delete(@PathVariable Long flowId) {
        int result = nWfFlowService.deleteByPrimaryKey(flowId);
        return new ResultDto<Integer>(result);
    }
}