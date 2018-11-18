package cn.com.yusys.yusp.workflow.core.org;

import java.io.Serializable;
import java.util.Map;

import com.figue.channel.transform.annotation.parse.xml.XmlPath;
/**
 * 用户
 * @author figue
 *
 */
@XmlPath("user")
public class WFUser implements Serializable{

	private static final long serialVersionUID = -3259186046241511374L;
	@XmlPath(valueAttr="@name")
	private String userName;
	
	@XmlPath(valueAttr="@id")
	private String userId;
	
	@XmlPath(valueAttr="@email")
	private String userEmail;
	
	@XmlPath(valueAttr="@mobile")
	private String userMobile;
	
	private Map<String,Object> ext;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public Map<String, Object> getExt() {
		return ext;
	}

	public void setExt(Map<String, Object> ext) {
		this.ext = ext;
	}

	@Override
	public String toString() {
		return "WFUser [userName=" + userName + ", userId=" + userId + ", userEmail=" + userEmail + ", userMobile="
				+ userMobile + ", ext=" + ext + "]";
	}
}
