package cn.com.yusys.yusp.workflow.service.ext.impl;

import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.workflow.biz.BeanRouteInterface;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowRouteInterface;
import cn.com.yusys.yusp.workflow.util.ApplicationContextUtil;
@Service
public class WorkflowRouteImpl implements WorkflowRouteInterface {

	@Override
	public boolean run(ResultInstanceDto instanceInfo,String nextNodeId,String isContinueBeanId) {
		if(isContinueBeanId.startsWith("/")){// fegin方式
			
		}else{// bean方式
			BeanRouteInterface biz = (BeanRouteInterface) ApplicationContextUtil.getBean(isContinueBeanId);
			return biz.runScript(instanceInfo, nextNodeId);
		}
		return true;
	}

}
