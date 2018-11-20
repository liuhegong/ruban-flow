package cn.com.yusys.yusp.workflow.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.workflow.dto.NextNodeInfoDto;
import cn.com.yusys.yusp.workflow.dto.WFCommentDto;
import cn.com.yusys.yusp.workflow.dto.WFStratDto;
import cn.com.yusys.yusp.workflow.dto.WFSubmitDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultNodeDto;
import cn.com.yusys.yusp.workflow.dto.result.ResultWFMessageDto;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowCoreServiceInterface;
import cn.com.yusys.yusp.workflow.web.dto.ResultDto;
import cn.com.yusys.yusp.workflow.web.fillter.session.CurrentUser;

@RestController
@RequestMapping("/api/core")
public class WorkflowCoreResource {
	@Autowired
	private WorkflowCoreServiceInterface WorkflowCoreServicee;
	
	@GetMapping("/")
	public ResultDto index(){
		ResultDto resultDto = new ResultDto();
		resultDto.setData(1);
		return resultDto;
	}	
	
	@GetMapping("/1")
	public ResultDto WorkflowCoreServicee(){
		WFStratDto stratDto = new WFStratDto();
		stratDto.setBizType("bizType");
		stratDto.setBizId("bizId");
		stratDto.setBizUserId("客户id");
		stratDto.setBizUserName("客户名称");
		
		stratDto.setFlowId("1");
		stratDto.setOrgId(CurrentUser.info.get().getOrgId());
		stratDto.setSystemId(CurrentUser.info.get().getSystemId());
		stratDto.setUserId(CurrentUser.info.get().getUserId());
		stratDto.setUserName(CurrentUser.info.get().getUserName());
		ResultInstanceDto data = WorkflowCoreServicee.start(stratDto);
		
		ResultDto resultDto = new ResultDto();
		resultDto.setData(data);
		return resultDto;
	}
	
	@GetMapping("/2")
	public ResultDto getNextNodeInfos(){		
		List<ResultNodeDto> data = WorkflowCoreServicee.getNextNodeInfos("1542366521504","22_a8");
		ResultDto resultDto = new ResultDto();
		resultDto.setData(data);
		return resultDto;
	}
	
	@GetMapping("/3")
	public ResultDto submit(){	
		WFSubmitDto sb = new WFSubmitDto();
		WFCommentDto comment = new WFCommentDto();
		comment.setCommentSign("1");
		comment.setInstanceId("1542366521504");
		comment.setNodeId("22_a8");
		comment.setOrgId("1orgId");
		comment.setUserComment("userComment");
		comment.setUserId("userId");
		sb.setComment(comment );
		List<NextNodeInfoDto> nextNodeInfos = new ArrayList<NextNodeInfoDto>();
		NextNodeInfoDto nextNodeInfoDto = new NextNodeInfoDto();
		nextNodeInfoDto.setNextNodeId("22_a9");
		nextNodeInfos.add(nextNodeInfoDto);
		sb.setNextNodeInfos(nextNodeInfos);
		List<ResultWFMessageDto> data = WorkflowCoreServicee.submit(sb);
		ResultDto resultDto = new ResultDto();
		resultDto.setData(data);
		return resultDto;
	}
	
	@GetMapping("/4")
	public ResultDto flow(){	
		WFStratDto stratDto = new WFStratDto();
		stratDto.setBizId("bizId");
		stratDto.setBizUserId("bizUserId");
		stratDto.setBizUserName("bizUserName");
		stratDto.setFlowId("22");
		stratDto.setOrgId("orgId");
		stratDto.setSystemId("cmis");
		stratDto.setUserId("userId");
		stratDto.setUserName("王菲");
		ResultInstanceDto data = WorkflowCoreServicee.start(stratDto);
		
		List<ResultNodeDto> data1 = WorkflowCoreServicee.getNextNodeInfos(data.getInstanceId(),data.getNodeId());

		WFSubmitDto sb = new WFSubmitDto();
		WFCommentDto comment = new WFCommentDto();
		comment.setCommentSign("1");
		comment.setInstanceId(data.getInstanceId());
		comment.setNodeId(data.getNodeId());
		comment.setOrgId("1orgId");
		comment.setUserComment("userComment");
		comment.setUserId("userId");
		sb.setComment(comment );
		List<NextNodeInfoDto> nextNodeInfos = new ArrayList<NextNodeInfoDto>();
		NextNodeInfoDto nextNodeInfoDto = new NextNodeInfoDto();
		nextNodeInfoDto.setNextNodeId(data1.get(0).getNodeId());
		nextNodeInfos.add(nextNodeInfoDto);
		sb.setNextNodeInfos(nextNodeInfos);
		List<ResultWFMessageDto> data2 = WorkflowCoreServicee.submit(sb);
		ResultDto resultDto = new ResultDto();		
		resultDto.setData(data2);
		return resultDto;
	}
	
}