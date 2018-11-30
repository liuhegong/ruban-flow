package cn.com.yusys.yusp.workflow.service.ext;

import java.util.List;

import cn.com.yusys.yusp.workflow.dto.WFUserDto;

/**
 * [分配策略]人员过滤
 * @author figue
 *
 */
public interface WorkflowUserFilterInterface {
	
	List<WFUserDto> selectUser(String beanName,List<WFUserDto> users,String instanceId, String nodeId, String orgId, String systemId);
}
