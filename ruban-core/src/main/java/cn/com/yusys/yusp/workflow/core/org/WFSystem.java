package cn.com.yusys.yusp.workflow.core.org;

import java.util.List;

import com.figue.channel.transform.annotation.parse.BeanClass;
import com.figue.channel.transform.annotation.parse.xml.XmlPath;

/**
 * 系统信息
 * @author figue
 *
 */
@XmlPath("system")
public class WFSystem {
	
	@XmlPath(valueAttr="@id")
	private String systemId;
	
	@XmlPath(valueAttr="@name")
	private String systemName;
	
	public void getOrgByLevel(String level,List<WFOrg> data){
		if(null!=getOrgs()){
			for(WFOrg org:getOrgs()){
				if(level.equals(org.getLevel())){
					data.add(org);
				}else{
					org.getOrgByLevel(level,data);
				}
			}
		}
	}
	
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	@BeanClass(WFUser.class)
	private List<WFUser> users;
	
	@BeanClass(WFOrg.class)
	private List<WFOrg> orgs;
	
	@BeanClass(WFDept.class)
	private List<WFDept> depts;
	
	@BeanClass(WFRole.class)
	private List<WFRole> roles;
	
	@BeanClass(WFDuty.class)
	private List<WFDuty> dutys;
	public List<WFUser> getUsers() {
		return users;
	}
	public void setUsers(List<WFUser> users) {
		this.users = users;
	}
	public List<WFOrg> getOrgs() {
		return orgs;
	}
	public void setOrgs(List<WFOrg> orgs) {
		this.orgs = orgs;
	}
	public List<WFDept> getDepts() {
		return depts;
	}
	public void setDepts(List<WFDept> depts) {
		this.depts = depts;
	}
	public List<WFRole> getRoles() {
		return roles;
	}
	public void setRoles(List<WFRole> roles) {
		this.roles = roles;
	}
	public List<WFDuty> getDutys() {
		return dutys;
	}
	public void setDutys(List<WFDuty> dutys) {
		this.dutys = dutys;
	} 	
}
