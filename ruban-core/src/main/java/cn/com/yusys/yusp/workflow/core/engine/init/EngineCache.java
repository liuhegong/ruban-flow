package cn.com.yusys.yusp.workflow.core.engine.init;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.figue.channel.transform.transform.xml.XmlTransform;

import cn.com.yusys.yusp.workflow.core.Cons;
import cn.com.yusys.yusp.workflow.core.engine.EngineInfo;
import cn.com.yusys.yusp.workflow.core.engine.flow.FlowInfo;
import cn.com.yusys.yusp.workflow.core.engine.node.NodeInfo;
import cn.com.yusys.yusp.workflow.core.engine.node.RouteInfo;
import cn.com.yusys.yusp.workflow.core.exception.WorkflowException;
import cn.com.yusys.yusp.workflow.core.studio.Flow;
import cn.com.yusys.yusp.workflow.core.studio.FlowParser;
import cn.com.yusys.yusp.workflow.core.studio.Node;
import cn.com.yusys.yusp.workflow.core.studio.Route;
import cn.com.yusys.yusp.workflow.core.util.FileUtil;


/**
 * 获取流程信息到缓存
 * @author figue
 *
 */
public class EngineCache{

	private static String  PATH = null;
	
	private static final Log log = LogFactory.getLog(EngineCache.class);
	
	private static EngineCache engineCache=new EngineCache();//唯一实例
	private static EngineInfo engineInfo  = null;
	
	private static Map<String,NodeInfo> nodeInfoCache = new HashMap<>();
	public static EngineCache getInstance(){
		return engineCache;
	}
	
	public static EngineCache getInstance(String path){
		EngineCache.PATH = path;
		return engineCache;
	}
	
	/**
	 * 流程引擎初始化，引用启动时必须执行
	 * @param path
	 * @throws WorkflowException
	 */
	public static void init() throws WorkflowException{		
		engineInfo  = new EngineInfo();
		List<FlowInfo> flows = new ArrayList<FlowInfo>();
		engineInfo.setFlows(flows);
		
		File floder = new File(EngineCache.PATH+File.separator+Cons.PROD);
		if(floder.isDirectory()&&floder.exists()){// 遍历解析流程图文件
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
		}else{
			log.warn("文件夹不存在:"+floder.getAbsolutePath());
		}
		
		// 缓存节点信息
		cacheNodeInfo(engineInfo);
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
	
	/**
	 * 缓存流程节点信息
	 * @param engineInfo
	 */
	private static void cacheNodeInfo(EngineInfo engineInfo){
		List<FlowInfo> flows = engineInfo.getFlows();
		for(FlowInfo flowInfoT:flows){
			for(NodeInfo nodeT:flowInfoT.getNodes()){
				nodeInfoCache.put(nodeT.getNodeId(), nodeT);
			}
		}
		if(log.isDebugEnabled()){
			log.debug("缓存节点信息完成:["+flows+"]");
		}
	}
	
	/**
	 * 获取节点信息
	 * @param nodeId
	 * @return
	 */
	public static NodeInfo getNodeInfo(String nodeId){
		NodeInfo nodeInfo = nodeInfoCache.get(nodeId);
		if(null==nodeInfo){
			log.error("未找到节点信息[nodeId="+nodeId+"]");
		}
		return nodeInfo;
	}
	
	/**
	 * 获取节点的所有后续节点
	 * @param nodeId
	 * @param nodeIds
	 */
	public static void getAllNextNodeId(String nodeId,List<String> nodeIds){
		NodeInfo nodeInfo = nodeInfoCache.get(nodeId);
		List<RouteInfo> routeInfos = nodeInfo.getRouteInfos();
		if(null!=routeInfos){
			for(RouteInfo route:routeInfos){
				String nextNodeId = route.getNextNodeId();
				if(nodeIds.contains(nextNodeId)){
					break;
				}
				nodeIds.add(nextNodeId);
				getAllNextNodeId(nextNodeId, nodeIds);
			}
		}
		
	}
	
	/**
	 * 将studio绘制的流程文件解析后，生成流程引擎可以识别的xml文件
	 * @param flow
	 * @throws IOException
	 */
	public static void generateFlowXml(Flow flow) throws IOException{
		log.debug("开始生成流程引擎使用的xml文件："+EngineCache.PATH+File.separator+Cons.DEV+File.separator+flow.getFlowId()+".xml");
		Flow flowT = FlowParser.parseXml(EngineCache.PATH+File.separator+Cons.DEV+File.separator+flow.getFlowId()+".xml");
		flowT.setFlowId(flow.getFlowId());
		flowT.setFlowName(flow.getFlowName());
		flowT.setOrgId(flow.getOrgId());
		flowT.setSystemId(flow.getSystemId());
		flowT.setAdmin(flow.getAdmin());
		
		FlowInfo flowInfo = new FlowInfo();
		
		BeanUtils.copyProperties(flowT, flowInfo);
		
		
		List<RouteInfo> routeInfos = new ArrayList<RouteInfo>();
		for(Route route:flowT.getRoutes()){// 遍历所有路由信息
			RouteInfo routeInfoT = new RouteInfo();
			BeanUtils.copyProperties(route, routeInfoT);
			routeInfos.add(routeInfoT);
		}
		
		List<NodeInfo> nodeInfos = new ArrayList<NodeInfo>();
		flowInfo.setNodes(nodeInfos);
		for(Node node:flowT.getNodes()){// 遍历所有节点
			NodeInfo nodeT = new NodeInfo();
			List<RouteInfo> routeInfoT = new ArrayList<RouteInfo>();
			nodeT.setRouteInfos(routeInfoT);
			
			BeanUtils.copyProperties(node, nodeT);
			nodeInfos.add(nodeT);	
			
			routeInfos.stream().forEach(routeInfo->{// 将路由开始节点是此节点的路由信息 赋给此节点
				if(routeInfo.getNodeId().equals(nodeT.getNodeId())){
					routeInfoT.add(routeInfo);
				}
				if(routeInfo.getNextNodeId().equals(nodeT.getNodeId())){
					nodeT.setLastNodeId(routeInfo.getNodeId());
				}
			});
		}
		
		// 遍历所有节点和路由，将节点id改为流程id加节点id，保证节点id全局唯一
		for(NodeInfo nodeInfoTT:nodeInfos){
			nodeInfoTT.setNodeId(flowT.getFlowId()+"_"+nodeInfoTT.getNodeId());
			if(null!=nodeInfoTT.getLastNodeId()&&!"".equals(nodeInfoTT.getLastNodeId())){
				nodeInfoTT.setLastNodeId(flowT.getFlowId()+"_"+nodeInfoTT.getLastNodeId());
			}else{
				nodeInfoTT.setLastNodeId("");
			}
			for(RouteInfo routeInfoTT:nodeInfoTT.getRouteInfos()){
				routeInfoTT.setNodeId(flowT.getFlowId()+"_"+routeInfoTT.getNodeId());
				routeInfoTT.setNextNodeId(flowT.getFlowId()+"_"+routeInfoTT.getNextNodeId());
			}
		}
		
		XmlTransform<FlowInfo> transForm = new XmlTransform<FlowInfo>();
		String content = transForm.trans2String(flowInfo);		
		log.debug("得到xml文件内容："+content);
		FileUtil.writeNewContentToFile(EngineCache.PATH+File.separator+Cons.PROD+File.separator+flow.getFlowId()+".xml", content);
	}

	public static EngineInfo getEngineInfo() {
		return engineInfo;
	}

	public static Map<String, NodeInfo> getNodeInfoCache() {
		return nodeInfoCache;
	}
	
}
