package cn.com.yusys.yusp.workflow.dto.result;

import java.io.Serializable;
import java.util.List;

public class ResultWFMessageDto  implements Serializable {
   
	private static final long serialVersionUID = -4268768078065559363L;
	
	private String code = "0";
    private String tip;
    private String nodeName;
    private List<String> userNames;
	
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
	public List<String> getUserNames() {
		return userNames;
	}
	public void setUserNames(List<String> userNames) {
		this.userNames = userNames;
	}
	
}