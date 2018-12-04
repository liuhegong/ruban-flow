package cn.com.yusys.yusp.workflow.service.ext;

import java.util.List;

import cn.com.yusys.yusp.workflow.dto.WFUserDto;

/**
 * 节点用户获取
 * @author figue
 *
 */
public interface WorkflowOrgInterface {
	WFUserDto getUserInfo(String systemId,String userId);
	List<WFUserDto> getUsersByOrgId(String systemId,String orgId);
	List<WFUserDto> getUsersByRoleId(String systemId,String roleId);
	List<WFUserDto> getUsersByDutyId(String systemId,String dutyId);
	List<WFUserDto> getUsersByDeptId(String systemId,String deptId);
	/**
	 * 获取此结构及其上级机构人员
	 * @param systemId
	 * @param orgId
	 * @return
	 */
	List<WFUserDto> getUpOrgUsers(String systemId,String orgId);
	
	/**
	 * 获取此机构及其下级机构人员
	 * @param systemId
	 * @param orgId
	 * @return
	 */
	List<WFUserDto> getLowOrgUsers(String systemId,String orgId);
	
	/**
	 * 获取此机构及其同级机构人员
	 * @param systemId
	 * @param orgId
	 * @return
	 */
	List<WFUserDto> getSameOrgUsers(String systemId,String orgId);

	
}
