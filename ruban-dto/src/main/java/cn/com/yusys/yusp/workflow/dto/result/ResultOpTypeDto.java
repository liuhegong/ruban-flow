package cn.com.yusys.yusp.workflow.dto.result;

public class ResultOpTypeDto {
	/**
	 * 挂起
	 */
	private String hangUp ;
	
	/**
	 * 唤醒
	 */
	private String wackUp ;
	
	/**
	 * 拿回
	 */
	private String tackBack;
	
	/**
	 * 打回
	 */
	private String callBack ;
	
	/**
	 * 退回
	 */
	private String returnBack ;
	
	
	/**
	 * 催办
	 */
	private String urged ;
	
	/**
	 * 转办
	 */
	private String change ;
	
	/**
	 * 协办
	 */
	private String assist ;
	
	/**
	 * 否决
	 */
	private String refuse ;
	
	/**
	 * 跳转
	 */
	private String jump  ;
	
	/**
	 * 委托
	 */
	private String entrust ;
		
	/**
	 * 抄送
	 */
	private String copy ;

	public String getHangUp() {
		return hangUp;
	}

	public void setHangUp(String hangUp) {
		this.hangUp = hangUp;
	}

	public String getWackUp() {
		return wackUp;
	}

	public void setWackUp(String wackUp) {
		this.wackUp = wackUp;
	}

	public String getTackBack() {
		return tackBack;
	}

	public void setTackBack(String tackBack) {
		this.tackBack = tackBack;
	}

	public String getCallBack() {
		return callBack;
	}

	public void setCallBack(String callBack) {
		this.callBack = callBack;
	}

	public String getReturnBack() {
		return returnBack;
	}

	public void setReturnBack(String returnBack) {
		this.returnBack = returnBack;
	}

	public String getUrged() {
		return urged;
	}

	public void setUrged(String urged) {
		this.urged = urged;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getAssist() {
		return assist;
	}

	public void setAssist(String assist) {
		this.assist = assist;
	}

	public String getRefuse() {
		return refuse;
	}

	public void setRefuse(String refuse) {
		this.refuse = refuse;
	}

	public String getJump() {
		return jump;
	}

	public void setJump(String jump) {
		this.jump = jump;
	}

	public String getEntrust() {
		return entrust;
	}

	public void setEntrust(String entrust) {
		this.entrust = entrust;
	}

	public String getCopy() {
		return copy;
	}

	public void setCopy(String copy) {
		this.copy = copy;
	}

	@Override
	public String toString() {
		return "OpTypeDto [hangUp=" + hangUp + ", wackUp=" + wackUp + ", tackBack=" + tackBack + ", callBack="
				+ callBack + ", returnBack=" + returnBack + ", urged=" + urged + ", change=" + change + ", assist="
				+ assist + ", refuse=" + refuse + ", jump=" + jump + ", entrust=" + entrust + ", copy=" + copy + "]";
	}	

}
