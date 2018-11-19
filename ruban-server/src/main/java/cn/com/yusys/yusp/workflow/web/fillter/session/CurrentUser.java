package cn.com.yusys.yusp.workflow.web.fillter.session;

public interface CurrentUser {
	public ThreadLocal<UserInfo> info = new ThreadLocal<UserInfo>();
}
