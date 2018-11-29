package cn.com.yusys.yusp.workflow.biz;

import java.util.Map;

import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
/**
 * spring bean 方式调用后业务处理
 * @author figue
 *
 */
public interface BeanBizInterface {
	/**
	 * 流程初始化
	 * @param instanceInfo
	 * @return
	 */
	Map<String,Object> afterInit(ResultInstanceDto instanceInfo);
	
	/**
	 * 流程提交后处理
	 * @param instanceInfo
	 * @return
	 */
	Map<String,Object> afterSubmit(ResultInstanceDto instanceInfo);
	
	/**
	 * 流程结束后处理
	 * @param instanceInfo
	 * @return
	 */
	Map<String,Object> afterEnd(ResultInstanceDto instanceInfo);
}
