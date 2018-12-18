package cn.com.yusys.yusp.workflow.service.ext;

import java.util.List;

import cn.com.yusys.yusp.workflow.dto.WFUserDto;

/**
 * [处理人员]自定义
 * @author figue
 *
 */
public interface WorkflowCustomUserInterface {
	
	List<String> customUser(String beanName, String orgId, String systemId);
}
