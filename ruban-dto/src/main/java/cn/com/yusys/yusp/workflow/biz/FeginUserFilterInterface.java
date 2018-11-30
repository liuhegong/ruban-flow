package cn.com.yusys.yusp.workflow.biz;

import java.util.List;

import cn.com.yusys.yusp.workflow.dto.WFUserDto;
/**
 * 人员分配策略自定义
 * @author figue
 *
 */
public interface FeginUserFilterInterface {
	/**
	 * fegin方式执行用户过滤
	 * @param users 流程引擎计算返回的人员
	 * @param instanceId 实例id
	 * @param nodeId   提交到的节点
	 * @param orgId    提交人机构
	 * @param systemId  系统id
	 * @return
	 */
	public List<WFUserDto> selectUser(List<WFUserDto> users, String instanceId, String nodeId, String orgId, String systemId) ;
	
}
