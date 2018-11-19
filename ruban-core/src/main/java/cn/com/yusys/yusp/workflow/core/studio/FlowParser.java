package cn.com.yusys.yusp.workflow.core.studio;

import java.io.File;

import com.figue.channel.transform.transform.xml.XmlTransform;

/**
 * 流程图解析器，将流程图文件解析后，生成流程引擎可以识别的流程图
 * @author figue
 *
 */
public class FlowParser {
	/**
	 * 根据流程图文件生成流程对象
	 * @param fileNameWithPath
	 * @return
	 */
	public static Flow parseXml(String fileNameWithPath){		
		XmlTransform<Flow> transForm = new XmlTransform<Flow>();
		//xml报文转对象
		Flow flow = transForm.trans2Bean(new File(fileNameWithPath),Flow.class);
		return flow;
	}
	
	/**
	 * 将流程对象直接生成流程引擎可以识别的xml文件
	 * @param flow
	 * @return
	 */
	public static String generateFile(Flow flow,String filePath){
		return null;
	}
	
	public static void main(String[] args) {
		FlowParser.parseXml("F:\\workspacecmis4cmismaven4.6-springboot2.0-ruban\\ruban-flow\\ruban-studio\\src\\main\\resources\\flow\\dev\\2.xml");
	}
}
