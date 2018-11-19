package cn.com.yusys.yusp.workflow.core.org;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.figue.channel.transform.transform.xml.XmlTransform;

import cn.com.yusys.yusp.workflow.core.exception.WorkflowException;

/**
 * 获取组织机构信息到缓存
 * @author figue
 *
 */
public class OrgCache{

	private static final Log log = LogFactory.getLog(OrgCache.class);
	
	private static OrgCache orgCache = new OrgCache();//唯一实例
	
	static List<WFSystem> systems = new ArrayList<WFSystem>();
	public List<WFSystem> getSystems() {
		return systems;
	}
	public void setSystems(List<WFSystem> systems) {
		this.systems = systems;
	}
	
	public static OrgCache getInstance(){
		return orgCache;
	}
	
	/**
	 * 流程引擎初始化
	 * @param path
	 * @throws WorkflowException
	 */
	public static void init(String path) throws WorkflowException{
		/**
		 * 初始化组织机构信息
		 */
		List<WFSystem> systems = new ArrayList<WFSystem>(); 
		orgCache.setSystems(systems);
		File orgFloder = new File(path+File.separator+"org");
		if(orgFloder.isDirectory()&&orgFloder.exists()){
			for(File file:orgFloder.listFiles()){
				if(!file.getName().endsWith(".xml")){
					continue;
				}
				XmlTransform<WFSystem> transForm = new XmlTransform<WFSystem>();
				//xml报文转对象
				WFSystem wFSystem = transForm.trans2Bean(file,WFSystem.class);
				systems.add(wFSystem);
				if(log.isDebugEnabled()){
					log.debug("成功加载组织机构文件:"+file.getAbsolutePath());
				}
			}
		}
	}	
	
	public static WFSystem getSystemInfo(String systemId){
		for(WFSystem wFSystem:systems){
			if(wFSystem.getSystemId().equals(systemId)){
				return wFSystem;
			}
		}
		return null;
	}
}
