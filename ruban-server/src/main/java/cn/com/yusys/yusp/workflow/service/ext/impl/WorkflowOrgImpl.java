package cn.com.yusys.yusp.workflow.service.ext.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.workflow.core.Cons;
import cn.com.yusys.yusp.workflow.core.org.OrgCache;
import cn.com.yusys.yusp.workflow.core.org.WFDept;
import cn.com.yusys.yusp.workflow.core.org.WFDuty;
import cn.com.yusys.yusp.workflow.core.org.WFOrg;
import cn.com.yusys.yusp.workflow.core.org.WFRole;
import cn.com.yusys.yusp.workflow.core.org.WFUser;
import cn.com.yusys.yusp.workflow.dto.WFUserDto;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowOrgInterface;

/**
 * 用户基本信息获取
 * @author figue
 *
 */
@Service
public class WorkflowOrgImpl implements WorkflowOrgInterface {

	private static final Log log = LogFactory.getLog(WorkflowOrgImpl.class);
	@Override
	public WFUserDto getUserInfo(String systemId,String userId) {
		if(Cons.SYSTEM_USER_ID.equalsIgnoreCase(userId)){// 系统指定用户
			WFUserDto wFUserDto = new WFUserDto();
			wFUserDto.setUserId(Cons.SYSTEM_USER_ID);
			wFUserDto.setUserName(Cons.SYSTEM_USER_NAME);
			return wFUserDto;
		}
		
		if(log.isDebugEnabled()){
			log.debug("获取用户[systemId="+systemId+",userId="+userId+"] ");
		}
		WFUser user = OrgCache.getUserInfo(systemId,userId);	
		if(log.isDebugEnabled()){
			log.debug("获取用户[systemId="+systemId+",userId="+userId+"] "+user);
		}
		WFUserDto wFUserDto = new WFUserDto();
		BeanUtils.copyProperties(user, wFUserDto);
		return wFUserDto;
	}
	
	@Override
	public List<WFUserDto> getUsersByOrgId(String systemId, String orgId) {
		WFOrg orgT = getOrgById( systemId, orgId);	
		List<WFUserDto> users = new ArrayList<WFUserDto>();
		if(null!=orgT.getUsers()){
			List<WFUser> usersT = orgT.getUsers();
			
			if(null!=usersT){
				for(WFUser wFUser:usersT){
					WFUserDto wFUserDto = new WFUserDto();
					BeanUtils.copyProperties(wFUser, wFUserDto);
					users.add(wFUserDto);
				}
			}
			if(log.isDebugEnabled()){
				log.debug("获取用户[systemId="+systemId+",orgId="+orgId+"] "+users);
			}
		}
		return users;
	}

	@Override
	public List<WFUserDto> getUsersByRoleId(String systemId, String roleId) {
		if(log.isDebugEnabled()){
			log.debug("获取用户[systemId="+systemId+",roleId="+roleId+"]");
		}
		List<WFRole> roleInfo = OrgCache.getSystemInfo(systemId).getRoles();
		WFRole orgT = null;
		for(WFRole org:roleInfo){
			orgT = org.getMe(roleId);
			if(null!=orgT){
				continue;
			}
		}
		if(null==orgT){
			log.error("机构缓存中无信息：[systemId="+systemId+",roleId="+roleId+"]");
		}
		List<WFUser> usersT = orgT.getUsers();
		List<WFUserDto> users = new ArrayList<WFUserDto>();
		if(null!=usersT){
			for(WFUser wFUser:usersT){
				WFUserDto wFUserDto = new WFUserDto();
				BeanUtils.copyProperties(wFUser, wFUserDto);
				users.add(wFUserDto);
			}
		}
		if(log.isDebugEnabled()){
			log.debug("获取用户[systemId="+systemId+",roleId="+roleId+"] "+users);
		}
		return users;
	}

	@Override
	public List<WFUserDto> getUsersByDutyId(String systemId, String dutyId) {
		if(log.isDebugEnabled()){
			log.debug("获取用户[systemId="+systemId+",dutyId="+dutyId+"]");
		}
		List<WFDuty> roleInfo = OrgCache.getSystemInfo(systemId).getDutys();
		WFDuty orgT = null;
		for(WFDuty org:roleInfo){
			orgT = org.getMe(dutyId);
			if(null!=orgT){
				continue;
			}
		}
		if(null==orgT){
			log.error("机构缓存中无信息：[systemId="+systemId+",dutyId="+dutyId+"]");
		}
		List<WFUser> usersT = orgT.getUsers();
		List<WFUserDto> users = new ArrayList<WFUserDto>();
		if(null!=usersT){
			for(WFUser wFUser:usersT){
				WFUserDto wFUserDto = new WFUserDto();
				BeanUtils.copyProperties(wFUser, wFUserDto);
				users.add(wFUserDto);
			}
		}
		if(log.isDebugEnabled()){
			log.debug("获取用户[systemId="+systemId+",dutyId="+dutyId+"] "+users);
		}
		return users;
	}
	
	/**
	 * 获取原始机构信息
	 * @param systemId
	 * @param orgId
	 * @return
	 */
	private WFOrg getOrgById(String systemId,String orgId){
		if(log.isDebugEnabled()){
			log.debug("获取用户[systemId="+systemId+",orgId="+orgId+"]");
		}
		List<WFOrg> orgInfo = OrgCache.getSystemInfo(systemId).getOrgs();
		WFOrg orgT = null;
		for(WFOrg org:orgInfo){
			orgT = org.getMe(orgId);
			if(null!=orgT){
				continue;
			}
		}
		if(null==orgT){
			log.error("机构缓存中无信息：[systemId="+systemId+",orgId="+orgId+"]");
		}
		return orgT;
	}

	@Override
	public List<WFUserDto> getUsersByDeptId(String systemId, String deptId) {
		if(log.isDebugEnabled()){
			log.debug("获取用户[systemId="+systemId+",deptId="+deptId+"]");
		}
		List<WFDept> roleInfo = OrgCache.getSystemInfo(systemId).getDepts();
		WFDept orgT = null;
		for(WFDept org:roleInfo){
			orgT = org.getMe(deptId);
			if(null!=orgT){
				continue;
			}
		}
		if(null==orgT){
			log.error("机构缓存中无信息：[systemId="+systemId+",deptId="+deptId+"]");
		}
		List<WFUser> usersT = orgT.getUsers();
		List<WFUserDto> users = new ArrayList<WFUserDto>();
		if(null!=usersT){
			for(WFUser wFUser:usersT){
				WFUserDto wFUserDto = new WFUserDto();
				BeanUtils.copyProperties(wFUser, wFUserDto);
				users.add(wFUserDto);
			}
		}
		if(log.isDebugEnabled()){
			log.debug("获取机构下用户[systemId="+systemId+",orgId="+deptId+"] "+users);
		}
		return users;
	}

	@Override
	public List<WFUserDto> getUpOrgUsers(String systemId, String orgId) {
		List<WFUserDto> users = new ArrayList<WFUserDto>();
		
		List<WFOrg> orgInfo = OrgCache.getSystemInfo(systemId).getOrgs();
		WFOrg parentOrg = null;
		for(WFOrg org:orgInfo){
			parentOrg = org.findParent(orgId);
			if(null!=parentOrg){
				continue;
			}
		}
		
		// 获取上级机构一下机构人员
		for(WFUser wFUser:parentOrg.getUsers()){
			WFUserDto wFUserDto = new WFUserDto();
			BeanUtils.copyProperties(wFUser, wFUserDto);
			users.add(wFUserDto);
		}
		
		
		// 获取本机构
		WFOrg orgT = getOrgById( systemId, orgId);
		List<WFUser> usersT = orgT.getUsers();
		if(null!=usersT){
			for(WFUser wFUser:usersT){
				WFUserDto wFUserDto = new WFUserDto();
				BeanUtils.copyProperties(wFUser, wFUserDto);
				users.add(wFUserDto);
			}
		}
		
		if(log.isDebugEnabled()){
			log.debug("获取用户[systemId="+systemId+",orgId="+orgId+"] "+users);
		}
		return users;
	}

	@Override
	public List<WFUserDto> getLowOrgUsers(String systemId, String orgId) {
		WFOrg orgT = getOrgById( systemId, orgId);
		// 获取本机构一下机构人员
		List<WFOrg> temp = orgT.getOrgs();
		List<WFUserDto> users = new ArrayList<WFUserDto>();
		if(null!=temp){
			for(WFOrg tempT:temp){
				if(null!=tempT.getUsers()){
					for(WFUser wFUser:tempT.getUsers()){
						WFUserDto wFUserDto = new WFUserDto();
						BeanUtils.copyProperties(wFUser, wFUserDto);
						users.add(wFUserDto);
					}
				}
			}
		}
		
		// 获取本机构
		List<WFUser> usersT = orgT.getUsers();
		if(null!=usersT){
			for(WFUser wFUser:usersT){
				WFUserDto wFUserDto = new WFUserDto();
				BeanUtils.copyProperties(wFUser, wFUserDto);
				users.add(wFUserDto);
			}
		}
		
		if(log.isDebugEnabled()){
			log.debug("获取用户[systemId="+systemId+",orgId="+orgId+"] "+users);
		}
		return users;
	}

	@Override
	public List<WFUserDto> getSameOrgUsers(String systemId, String orgId) {
		WFOrg orgT = getOrgById( systemId, orgId);
		List<WFOrg> data = new ArrayList<WFOrg>();
		OrgCache.getSystemInfo(systemId).getOrgByLevel(orgT.getLevel(), data);
		/**
		 * 获取和本机构同级别的机构
		 */
		List<WFUserDto> users = new ArrayList<WFUserDto>();
		for(WFOrg wFOrg:data){
			List<WFUser> usersT = wFOrg.getUsers();
			if(null!=usersT){
				for(WFUser wFUser:usersT){
					WFUserDto wFUserDto = new WFUserDto();
					BeanUtils.copyProperties(wFUser, wFUserDto);
					users.add(wFUserDto);
				}
			}
		}
		
		return users;
	}

}
