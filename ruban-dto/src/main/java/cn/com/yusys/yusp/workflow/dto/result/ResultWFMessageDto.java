package cn.com.yusys.yusp.workflow.dto.result;

import java.io.Serializable;

public class ResultWFMessageDto  implements Serializable {
   
	private static final long serialVersionUID = -4268768078065559363L;
	
	private String code = "0";
    private String tip;
    private String nodeName;
    private String userName;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}