package cn.com.yusys.yusp.workflow.demo;

import cn.com.yusys.yusp.workflow.biz.BeanRouteInterface;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;

public class BeanRoute1DemoImpl implements BeanRouteInterface {

	@Override
	public boolean runScript(ResultInstanceDto instanceInfo, String nextNodeId) {
		return true;
	}
	
}
