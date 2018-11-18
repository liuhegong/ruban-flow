package cn.com.yusys.yusp.workflow.dto;

import java.io.Serializable;

public class WFUserDto implements Serializable{
	
	private static final long serialVersionUID = -5938365165168658333L;
	private String userName;
	private String userId;
	private String userEmail;
	private String userMobile;
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
	@Override
	public String toString() {
		return "WFUserDto [userName=" + userName + ", userId=" + userId + ", userEmail=" + userEmail + ", userMobile="
				+ userMobile + "]";
	}
	
}
