package cn.com.yusys.yusp.workflow.core.org;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.figue.channel.transform.annotation.parse.BeanClass;
import com.figue.channel.transform.annotation.parse.xml.XmlPath;
@XmlPath("role")
public class WFRole implements Serializable{

	private static final long serialVersionUID = 3780956164577612355L;
	@XmlPath(valueAttr="@id")
	private String roleId;
	@XmlPath(valueAttr="@name")
	private String roleName;
	
	@BeanClass(WFUser.class)
	private List<WFUser> users;
	
	@BeanClass(WFRole.class)
	private List<WFRole> roles;

	public WFRole getMe(String orgId){
		if(orgId.equals(this.getRoleId())){
			return this;
		}else{
			if(null!=getRoles()){
				for(WFRole org:getRoles()){
					WFRole orgT = org.getMe(orgId);
					if(null!=orgT){
						return orgT;
					}
				}
			}
			return null;
		}
	}
	
	public List<WFRole> getRoles() {
		return roles;
	}
	public void setRoles(List<WFRole> roles) {
		this.roles = roles;
	}
	public List<WFUser> getUsers() {
		return users;
	}
	public void setUsers(List<WFUser> users) {
		this.users = users;
	}
	private String ext;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	@Override
	public String toString() {
		return "WFRole [roleId=" + roleId + ", roleName=" + roleName + ", ext=" + ext + "]";
	}
	
	public void getUserInfos(Map<String,WFUser> users){		
		if(null!=getUsers()){
			for(WFUser user:getUsers()){
				users.put(user.getUserId(), user);
			}
		}		
		if(null!=getRoles()){
			for(WFRole dept:getRoles()){
				dept.getUserInfos(users);
			}
		}
	}
}
