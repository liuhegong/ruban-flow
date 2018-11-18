package cn.com.yusys.yusp.workflow.core.org;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.figue.channel.transform.annotation.parse.BeanClass;
import com.figue.channel.transform.annotation.parse.xml.XmlPath;
/**
 * 部门信息
 * @author figue
 *
 */
@XmlPath("dept")
public class WFDept implements Serializable{

	private static final long serialVersionUID = -7134411030059733147L;
	@XmlPath(valueAttr="@id")
	private String deptId;
	
	@XmlPath(valueAttr="@name")
	private String deptName;
	private Map<String,Object> ext;
	
	@BeanClass(WFDept.class)
	private List<WFDept> depts;
	
	public List<WFDept> getDepts() {
		return depts;
	}
	public void setDepts(List<WFDept> depts) {
		this.depts = depts;
	}
	@BeanClass(WFUser.class)
	private List<WFUser> users;
	
	public WFDept getMe(String orgId){
		if(orgId.equals(this.getDeptId())){
			return this;
		}else{
			if(null!=getDepts()){
				for(WFDept org:getDepts()){
					WFDept orgT = org.getMe(orgId);
					if(null!=orgT){
						return orgT;
					}
				}
			}
			return null;
		}
	}
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Map<String, Object> getExt() {
		return ext;
	}
	public void setExt(Map<String, Object> ext) {
		this.ext = ext;
	}
	@Override
	public String toString() {
		return "WFDept [deptId=" + deptId + ", deptName=" + deptName + ", ext=" + ext + "]";
	}
	public List<WFUser> getUsers() {
		return users;
	}
	public void setUsers(List<WFUser> users) {
		this.users = users;
	}
}
