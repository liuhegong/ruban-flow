package cn.com.yusys.yusp.workflow.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.yusys.yusp.workflow.core.exception.WorkflowException;
import cn.com.yusys.yusp.workflow.dto.result.ResultMessageDto;
import cn.com.yusys.yusp.workflow.web.dto.ResultDto;
/**
 * 工作流异常统一处理
 * @author figue
 *
 */
@ControllerAdvice
public class WorkFlowExceptionHandler {
	private static final Log log = LogFactory.getLog(WorkFlowExceptionHandler.class);
	
	@ExceptionHandler(WorkflowException.class)
	@ResponseBody
	public ResultDto<List<ResultMessageDto>> handlerMyException(WorkflowException ex,HttpServletResponse response) {
		log.error("流程异常：["+ex.getMessage()+"]");
		ResultMessageDto message = new ResultMessageDto();
		message.setTip(ex.getMessage());
		message.setCode("1");
		message.setNodeName("");
		response.setStatus(200);	
		List<ResultMessageDto> result = new ArrayList<ResultMessageDto>();
		result.add(message);
		return new ResultDto<List<ResultMessageDto>>(result);
	}

}
