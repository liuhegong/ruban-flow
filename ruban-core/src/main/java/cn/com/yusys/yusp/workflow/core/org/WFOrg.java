package cn.com.yusys.yusp.workflow.core.org;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.figue.channel.transform.annotation.parse.BeanClass;
import com.figue.channel.transform.annotation.parse.xml.XmlPath;
/**
 * 机构
 * @author figue
 *
 */
@XmlPath("org")
public class WFOrg implements Serializable{

	private static final long serialVersionUID = -4153540862709932432L;
	@XmlPath(valueAttr="@id")
	private String orgId;
	
	@XmlPath(valueAttr="@name")
	private String orgName;
	
	@XmlPath(valueAttr="@level")
	private String level;
	
	@BeanClass(WFUser.class)
	private List<WFUser> users;
	
	@BeanClass(WFOrg.class)
	private List<WFOrg> orgs;
	
	public WFOrg findParent(String childrenOrgId) {
		if(null!=getOrgs()){
			for(WFOrg org:getOrgs()){
				if(childrenOrgId.equals(org.getOrgId())){
					return this;
				}else{
					org.findParent(childrenOrgId);
				}
			}
		}
		return null;
	}

	public WFOrg getMe(String orgId){
		if(orgId.equals(this.getOrgId())){
			return this;
		}else{
			if(null!=getOrgs()){
				for(WFOrg org:getOrgs()){
					WFOrg orgT = org.getMe(orgId);
					if(null!=orgT){
						return orgT;
					}
				}
			}
			return null;
		}
	}
	
	
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
	
	public List<WFOrg> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<WFOrg> orgs) {
		this.orgs = orgs;
	}

	public List<WFUser> getUsers() {
		return users;
	}

	public void setUsers(List<WFUser> users) {
		this.users = users;
	}

	private Map<String,Object> ext;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Map<String, Object> getExt() {
		return ext;
	}

	public void setExt(Map<String, Object> ext) {
		this.ext = ext;
	}

	@Override
	public String toString() {
		return "WFOrg [orgId=" + orgId + ", orgName=" + orgName + ", level=" + level + ", users=" + users + ", orgs="
				+ orgs + ", ext=" + ext + "]";
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public void getUserInfos(Map<String,WFUser> users){		
		if(null!=getUsers()){
			for(WFUser user:getUsers()){
				users.put(user.getUserId(), user);
			}
		}		
		if(null!=getOrgs()){
			for(WFOrg dept:getOrgs()){
				dept.getUserInfos(users);
			}
		}
	}
	
}
