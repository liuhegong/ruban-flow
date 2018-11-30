package cn.com.yusys.yusp.workflow.demo;

import java.util.Map;

import cn.com.yusys.yusp.workflow.biz.BeanBizInterface;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
/**
 * [业务处理]示例
 * @author figue
 *
 */
public class BeanBizDemoImpl implements BeanBizInterface {

	@Override
	public Map<String, Object> afterInit(ResultInstanceDto instanceInfo) {
		System.out.println("流程后处理afterInit:"+instanceInfo);
		return null;
	}

	@Override
	public Map<String, Object> afterSubmit(ResultInstanceDto instanceInfo) {
		System.out.println("流程后处理afterSubmit:"+instanceInfo);
		return null;
	}

	@Override
	public Map<String, Object> afterEnd(ResultInstanceDto instanceInfo) {
		System.out.println("流程后处理afterEnd:"+instanceInfo);
		return null;
	}
	
}
