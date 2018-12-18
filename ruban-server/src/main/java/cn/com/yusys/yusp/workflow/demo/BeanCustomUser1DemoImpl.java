package cn.com.yusys.yusp.workflow.demo;

import java.util.ArrayList;
import java.util.List;

import cn.com.yusys.yusp.workflow.biz.BeanCustomUserInterface;
/**
 * [处理人员]自定义示例
 * @author figue
 *
 */
public class BeanCustomUser1DemoImpl implements BeanCustomUserInterface {

	@Override
	public List<String> customUser(String orgId, String systemId) {
		List<String> usersT = new ArrayList<String>();
		usersT.add("admin");
		return usersT;
	}

}
