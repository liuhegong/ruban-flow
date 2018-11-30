package cn.com.yusys.yusp.workflow.demo;

import cn.com.yusys.yusp.workflow.biz.BeanRouteInterface;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
/**
 * [路由条件]示例
 * @author figue
 * 返回true则走此节点,返回false则不走此节点
 */
public class BeanRoute1DemoImpl implements BeanRouteInterface {
	@Override
	public boolean runScript(ResultInstanceDto instanceInfo, String nextNodeId) {
		return true;
	}
	
}
