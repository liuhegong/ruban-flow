package cn.com.yusys.yusp.workflow.service.ext.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.dto.result.ResultInstanceTodoDto;
import cn.com.yusys.yusp.workflow.repository.mapper.WorkflowBenchMapper;
import cn.com.yusys.yusp.workflow.service.ext.WorkflowBenchInterface;

/**
 * 我的控制台
 * @author figue
 *
 */
@Service
public class WorkflowBenchImpl implements WorkflowBenchInterface{

	@Autowired
	private WorkflowBenchMapper workflowBenchMapper;
	
	@Override
	public List<ResultInstanceTodoDto> getInstanceInfoUserTodo(QueryModel model) {
		PageHelper.startPage(model.getPage(), model.getSize());
		List<ResultInstanceTodoDto> list = workflowBenchMapper.getInstanceInfoUserTodo(model);
		PageHelper.clearPage();
		return list;
	}
	
	@Override
	public List<ResultInstanceTodoDto> getInstanceInfoUserDone(QueryModel model) {
		PageHelper.startPage(model.getPage(), model.getSize());
		List<ResultInstanceTodoDto> list = workflowBenchMapper.getInstanceInfoUserDone(model);
		PageHelper.clearPage();
		return list;
	}

	@Override
	public List<ResultInstanceTodoDto> getInstanceInfoUserHis(QueryModel model) {
		PageHelper.startPage(model.getPage(), model.getSize());
		List<ResultInstanceTodoDto> list = workflowBenchMapper.getInstanceInfoUserHis(model);
		PageHelper.clearPage();
		return list;
	}
}
