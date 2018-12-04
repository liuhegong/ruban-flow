package cn.com.yusys.yusp.workflow.service.ext.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.workflow.biz.BeanCustomUserInterface;
import cn.com.yusys.yusp.workflow.dto.WFUserDto;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowCustomUserInterface;
import cn.com.yusys.yusp.workflow.util.ApplicationContextUtil;
/**
 * [处理人员]自定义
 * @author figue
 *
 */
@Service
public class WorkflowCustomUserImpl implements WorkflowCustomUserInterface {

	@Override
	public List<WFUserDto> customUser(String beanName, String orgId, String systemId) {
		if(beanName.startsWith("/")){// fegin方式
			
		}else{// bean方式
			BeanCustomUserInterface biz = (BeanCustomUserInterface) ApplicationContextUtil.getBean(beanName);
			return biz.customUser(orgId, systemId);
		}
		return null;
	}

}
