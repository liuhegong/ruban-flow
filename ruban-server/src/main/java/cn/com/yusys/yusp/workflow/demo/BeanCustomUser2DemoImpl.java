package cn.com.yusys.yusp.workflow.demo;

import java.util.ArrayList;
import java.util.List;

import cn.com.yusys.yusp.workflow.biz.BeanCustomUserInterface;
import cn.com.yusys.yusp.workflow.dto.WFUserDto;
/**
 * [处理人员]自定义示例
 * @author figue
 *
 */
public class BeanCustomUser2DemoImpl implements BeanCustomUserInterface {

	@Override
	public List<WFUserDto> customUser(String orgId, String systemId) {
		WFUserDto user = new WFUserDto();
		user.setUserId("2");
		user.setUserName("王亚飞2");
		List<WFUserDto> usersT = new ArrayList<WFUserDto>();
		usersT.add(user);
		return usersT;
	}

}
