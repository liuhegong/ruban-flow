package cn.com.yusys.yusp.workflow.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.com.yusys.yusp.workflow.biz.BeanUserFilterInterface;
import cn.com.yusys.yusp.workflow.dto.WFUserDto;
/**
 * [分配策略]示例
 * @author figue
 *
 */
public class BeanUserFilter1DemoImpl implements BeanUserFilterInterface {

	@Override
	public List<WFUserDto> selectUser(List<WFUserDto> users, String instanceId, String nodeId, String orgId,
			String systemId) {
		// 随机返回人员中的一个
		int num = users.size();
		Random random = new Random();
		WFUserDto user = users.get(random.nextInt(num));
		
		List<WFUserDto> usersT = new ArrayList<WFUserDto>();
		usersT.add(user);
		return usersT;
	}

}
