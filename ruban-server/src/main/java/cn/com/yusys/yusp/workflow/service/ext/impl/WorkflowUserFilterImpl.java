package cn.com.yusys.yusp.workflow.service.ext.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.workflow.biz.BeanUserFilterInterface;
import cn.com.yusys.yusp.workflow.dto.WFUserDto;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowUserFilterInterface;
import cn.com.yusys.yusp.workflow.util.ApplicationContextUtil;
/**
 * 执行人员过滤，一般对【人员选择】为【系统指定】时，对计算后的人员做过滤
 * @author figue
 *
 */
@Service
public class WorkflowUserFilterImpl implements WorkflowUserFilterInterface {

	@Override
	public List<WFUserDto> selectUser(String beanName, List<WFUserDto> users, String instanceId, String nodeId,
			String orgId, String systemId) {
		if (beanName.startsWith("/")) {// fegin方式

		} else {// bean方式
			BeanUserFilterInterface biz = (BeanUserFilterInterface) ApplicationContextUtil.getBean(beanName);
			return biz.selectUser(users, instanceId, nodeId, orgId, systemId);
		}
		return users;
	}

}
