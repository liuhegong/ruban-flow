package cn.com.yusys.yusp.workflow.service.ext;

import cn.com.yusys.yusp.workflow.core.exception.WorkflowException;
import cn.com.yusys.yusp.workflow.dto.WFReturnDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultMessageDto;
/**
 * 流程扩展接口
 * @author figue
 *
 */
public interface WorkflowEngineExtInterface {
	/**
	 * 流程退回
	 * @param instanceId
	 * @param nodeId
	 * @param userId
	 * @return
	 * @throws WorkflowException
	 */
	ResultMessageDto returnBack(WFReturnDto returnDto)  throws WorkflowException;
}
