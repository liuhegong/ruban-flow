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

import cn.com.yusys.yusp.workflow.core.Cons;
import cn.com.yusys.yusp.workflow.core.engine.init.EngineCache;
import cn.com.yusys.yusp.workflow.core.engine.node.type.FlowState;
import cn.com.yusys.yusp.workflow.core.studio.Flow;
import cn.com.yusys.yusp.workflow.core.util.FileUtil;
import cn.com.yusys.yusp.workflow.core.util.TimeUtil;
import cn.com.yusys.yusp.workflow.domain.NWfFlow;
import cn.com.yusys.yusp.workflow.domain.NWfFlowHis;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.service.NWfFlowHisService;
import cn.com.yusys.yusp.workflow.service.NWfFlowService;
import cn.com.yusys.yusp.workflow.web.dto.ResultDto;
/**
 * 流程图操作
 * @author figue
 *
 */
@RestController
@RequestMapping("/api/nwfflow")
public class StudioFlowResource {
	
	@Value("${flow.path}")
	private String flowPath = null;
	
    @Autowired
    private NWfFlowService nWfFlowService;
    
    @Autowired
    private NWfFlowHisService nWfFlowHisService;
    
    /**
     * 流程图列表
     * @param systemId
     * @param userId
     * @return
     */
    @GetMapping("/")
    public ResultDto<List<NWfFlow>> index(String systemId,String userId) {
        QueryModel queryModel = new QueryModel();
        queryModel.getCondition().put("systemId", systemId);
        queryModel.getCondition().put("flowAdmin", userId);
        queryModel.setSort("start_time desc");
		List<NWfFlow> list = nWfFlowService.selectByModel(queryModel );
        return new ResultDto<List<NWfFlow>>(list);
    }

    /**
     * 新增流程图
     * @param nWfFlow
     * @return
     */
    @PostMapping("/")
    public ResultDto<NWfFlow> create(@RequestBody NWfFlow nWfFlow) {
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
        if(null==nWfFlow.getFlowContent()||"".equals(nWfFlow.getFlowContent())){// 流程图内容为空，则根据流程id读文件
        	String content = FileUtil.getFileContent(flowPath+File.separator+Cons.DEV+File.separator+nWfFlow.getFlowId()+".xml");
        	if(null!=content){
        		nWfFlow.setFlowContent(content);
        	}
        }
        return new ResultDto<NWfFlow>(nWfFlow);
    }

    /**
     * 更新流程图
     * @param nWfFlow
     * @return
     * @throws IOException
     */
    @PostMapping("/update")
    @Transactional
    public ResultDto<Integer> update(@RequestBody NWfFlow nWfFlow) throws IOException {
        
    	NWfFlow currentFlow = nWfFlowService.selectByPrimaryKey(nWfFlow.getFlowId());
    	    	
    	nWfFlowService.deleteByPrimaryKey(nWfFlow.getFlowId());
    	nWfFlow.setStartTime(TimeUtil.getDateyyyyMMddHHmmss());
    	nWfFlow.setFlowState(FlowState.RUN); 
    	nWfFlow.setFlowName(currentFlow.getFlowName());
    	long version = currentFlow.getFlowVersion();
    	nWfFlow.setFlowVersion(version+1l);
    	nWfFlowService.insert(nWfFlow);
    	   	
    	NWfFlowHis recordHis = new NWfFlowHis();
    	BeanUtils.copyProperties(currentFlow, recordHis);
		nWfFlowHisService.insert(recordHis);
    	
		// 写入流程图绘制文件
		FileUtil.writeNewContentToFile(flowPath+File.separator+Cons.DEV+File.separator+nWfFlow.getFlowId()+".xml", nWfFlow.getFlowContent());
		
		// 生成新的流程图可以识别的文件
		Flow flow = new Flow();
		flow.setAdmin(currentFlow.getFlowAdmin());
		flow.setFlowId(currentFlow.getFlowId()+"");
		flow.setFlowName(currentFlow.getFlowName());
		flow.setOrgId(currentFlow.getOrgId());
		flow.setSystemId(currentFlow.getSystemId());
		EngineCache.generateFlowXml(flow);
        return new ResultDto<Integer>(0);
    }

    /**
     * 删除流程图
     * @param flowId
     * @return
     */
    @PostMapping("/delete/{flowId}")
    public ResultDto<Integer> delete(@PathVariable Long flowId) {
        int result = nWfFlowService.deleteByPrimaryKey(flowId);
        FileUtil.deleteFile(new File(flowPath+File.separator+Cons.DEV+File.separator+flowId+".xml"));
        FileUtil.deleteFile(new File(flowPath+File.separator+Cons.PROD+File.separator+flowId+".xml"));
        return new ResultDto<Integer>(result);
    }
    
}