package cn.com.yusys.yusp.workflow.service.ext.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.workflow.biz.BeanBizInterface;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowBizInterface;
import cn.com.yusys.yusp.workflow.util.ApplicationContextUtil;
/**
 * 节点流转业务处理
 * @author figue
 *
 */
@Service
public class WorkflowBizImpl implements WorkflowBizInterface {

	@Override
	public Map<String, Object> afterInit(String bizBeanId,ResultInstanceDto instanceInfo) {
		if(bizBeanId.startsWith("/")){// fegin方式
			
		}else{// bean方式
			BeanBizInterface biz = (BeanBizInterface) ApplicationContextUtil.getBean(bizBeanId);
			biz.afterInit(instanceInfo);
		}
		return null;
	}

	
	@Override
	public Map<String, Object> afterSubmit(String bizBeanId,ResultInstanceDto instanceInfo) {
		if(bizBeanId.startsWith("/")){// fegin方式
			
		}else{// bean方式
			BeanBizInterface biz = (BeanBizInterface) ApplicationContextUtil.getBean(bizBeanId);
			biz.afterSubmit(instanceInfo);
		}
		return null;
	}

	@Override
	public Map<String, Object> afterEnd(String bizBeanId,ResultInstanceDto instanceInfo) {
		if(bizBeanId.startsWith("/")){// fegin方式
			
		}else{// bean方式
			BeanBizInterface biz = (BeanBizInterface) ApplicationContextUtil.getBean(bizBeanId);
			biz.afterEnd(instanceInfo);
		}
		return null;
	}
	
}
