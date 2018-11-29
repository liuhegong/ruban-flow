package cn.com.yusys.yusp.workflow.biz;

import java.util.Map;

import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;

public interface FeginBizInterface {
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
