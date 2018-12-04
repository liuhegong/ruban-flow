package cn.com.yusys.yusp.workflow.biz;

import java.util.List;

import cn.com.yusys.yusp.workflow.dto.WFUserDto;
/**
 * [处理人员]自定义
 * @author figue
 *
 */
public interface BeanCustomUserInterface {
	List<WFUserDto> customUser(String orgId, String systemId);	
}
