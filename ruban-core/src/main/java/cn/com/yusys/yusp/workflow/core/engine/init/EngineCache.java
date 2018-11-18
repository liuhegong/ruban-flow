package cn.com.yusys.yusp.workflow.core.engine.init;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.figue.channel.transform.transform.xml.XmlTransform;

import cn.com.yusys.yusp.workflow.core.engine.EngineInfo;
import cn.com.yusys.yusp.workflow.core.exception.WorkflowException;
import cn.com.yusys.yusp.workflow.core.flow.FlowInfo;
import cn.com.yusys.yusp.workflow.core.node.NodeInfo;
import cn.com.yusys.yusp.workflow.core.org.WFSystem;

/**
 * 获取流程信息到缓存
 * @author figue
 *
 */
public class EngineCache{

	private static final Log log = LogFactory.getLog(EngineCache.class);
	
	private static EngineCache engineCache=new EngineCache();//唯一实例
	private static EngineInfo engineInfo  = null;
	
	public static EngineCache getInstance(){
		return engineCache;
	}
	
	/**
	 * 流程引擎初始化
	 * @param path
	 * @throws WorkflowException
	 */
	public static void init(String path) throws WorkflowException{
		engineInfo  = new EngineInfo();
		List<FlowInfo> flows = new ArrayList<FlowInfo>();
		engineInfo.setFlows(flows);
		
		File floder = new File(path);
		if(floder.isDirectory()&&floder.exists()){
			for(File file:floder.listFiles()){
				if(!file.getName().endsWith(".xml")){
					continue;
				}
				XmlTransform<FlowInfo> transForm = new XmlTransform<FlowInfo>();
				//xml报文转对象
				FlowInfo flowInfo = transForm.trans2Bean(file,FlowInfo.class);
				flows.add(flowInfo);
				if(log.isDebugEnabled()){
					log.debug("成功加载流程文件:"+file.getAbsolutePath());
				}
			}
		}
	}
	
	/**
	 * 获取流程图基本信息
	 * @param flowId
	 * @param systemId
	 * @return
	 */
	public static FlowInfo getFlowInfo(String flowId,String systemId){
		List<FlowInfo> flows = engineInfo.getFlows();
		for(FlowInfo flowInfoT:flows){
			if(flowInfoT.getFlowId().equals(flowId)){
				if(flowInfoT.getSystemId().equals(systemId)){
					final FlowInfo flowInfo = flowInfoT;
					return flowInfo;
				}
			}
		}
		log.error("未找到流程图[flowId="+flowId+",systemId="+systemId+"]");
		return null;
	}
	
	public static NodeInfo getNodeInfo(String nodeId){
		List<FlowInfo> flows = engineInfo.getFlows();
		for(FlowInfo flowInfoT:flows){
			for(NodeInfo nodeT:flowInfoT.getNodes()){
				if(nodeT.getNodeId().equals(nodeId)){
					final NodeInfo nodeInfo = nodeT;
					return nodeInfo;
				}
			}
		}
		log.error("未找到节点[nodeId="+nodeId+"]");
		return null;
	}
	
	
}
