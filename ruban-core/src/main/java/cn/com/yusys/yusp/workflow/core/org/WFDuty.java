package cn.com.yusys.yusp.workflow.core.org;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.figue.channel.transform.annotation.parse.BeanClass;
import com.figue.channel.transform.annotation.parse.xml.XmlPath;
/**
 * 岗位
 * @author figue
 *
 */
@XmlPath("duty")
public class WFDuty implements Serializable{

	private static final long serialVersionUID = 697196316492101122L;
	@XmlPath(valueAttr="@id")
	private String dutyId;
	@XmlPath(valueAttr="@name")
	private String dutyName;
	private Map<String,Object> ext;
	
	@BeanClass(WFUser.class)
	private List<WFUser> users;
	
	@BeanClass(WFDuty.class)
	private List<WFDuty> dutys;
	
	
	public List<WFUser> getUsers() {
		return users;
	}
	public void setUsers(List<WFUser> users) {
		this.users = users;
	}
	public String getDutyId() {
		return dutyId;
	}
	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}
	public String getDutyName() {
		return dutyName;
	}
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	public Map<String, Object> getExt() {
		return ext;
	}
	public void setExt(Map<String, Object> ext) {
		this.ext = ext;
	}
	@Override
	public String toString() {
		return "WFDuty [dutyId=" + dutyId + ", dutyName=" + dutyName + ", ext=" + ext + "]";
	}
	
	public WFDuty getMe(String orgId){
		if(orgId.equals(this.getDutyId())){
			return this;
		}else{
			if(null!=getDutys()){
				for(WFDuty org:getDutys()){
					WFDuty orgT = org.getMe(orgId);
					if(null!=orgT){
						return orgT;
					}
				}
			}
			return null;
		}
	}
	
	public List<WFDuty> getDutys() {
		return dutys;
	}
	public void setDutys(List<WFDuty> dutys) {
		this.dutys = dutys;
	}
	
}
