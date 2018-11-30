package cn.com.yusys.yusp.workflow.demo;

import java.util.List;
import java.util.Random;

import cn.com.yusys.yusp.workflow.biz.BeanUserFilterInterface;
import cn.com.yusys.yusp.workflow.dto.WFUserDto;
/**
 * [分配策略]示例
 * @author figue
 *
 */
public class BeanUserFilter2DemoImpl implements BeanUserFilterInterface {

	@Override
	public List<WFUserDto> selectUser(List<WFUserDto> users, String instanceId, String nodeId, String orgId,
			String systemId) {
		// 如果大于一个人，则随机踢出去一个
		int num = users.size();
		if(num>1){
			Random random = new Random();
			users.remove(random.nextInt(num));
			return users;
		}else{
			return users;
		}
	}
}
