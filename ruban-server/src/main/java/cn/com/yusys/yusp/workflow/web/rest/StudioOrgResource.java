package cn.com.yusys.yusp.workflow.web.rest;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.workflow.core.exception.WorkflowException;
import cn.com.yusys.yusp.workflow.core.org.OrgCache;
import cn.com.yusys.yusp.workflow.core.org.WFDept;
import cn.com.yusys.yusp.workflow.core.org.WFDuty;
import cn.com.yusys.yusp.workflow.core.org.WFOrg;
import cn.com.yusys.yusp.workflow.core.org.WFRole;
import cn.com.yusys.yusp.workflow.core.org.WFSystem;
import cn.com.yusys.yusp.workflow.core.org.WFUser;
import cn.com.yusys.yusp.workflow.domain.dto.QueryModel;
import cn.com.yusys.yusp.workflow.web.dto.ResultDto;
/**
 * 流程图操作
 * @author figue
 *
 */
@RestController
@RequestMapping("/api/org")
public class StudioOrgResource {
		
	private static final Log log = LogFactory.getLog(StudioOrgResource.class);

    @GetMapping("/users")
    public ResultDto<Collection<WFUser>> allUsers(QueryModel queryModel) {
    	List<WFUser> users = OrgCache.getUserAllUsers(queryModel.getCondition().get("systemId").toString());
    	ResultDto result = new ResultDto<Collection<WFUser>>(getPagedList( queryModel.getPage(), 10, users));
    	result.setTotal(users.size());
    	return result;
    }
    
    private Collection<WFUser> getPagedList(int page, int size, List<WFUser> data) {
		
		if(null==data||data.isEmpty()){
			return Collections.emptyList();// 空数组
		}
		
		if (page == 0) {
			page = 1;
		}
		int fromIndex = (page - 1) * size;
		if (fromIndex >= data.size()) {
			return Collections.emptyList();// 空数组
		}
		if (fromIndex < 0) {
			fromIndex = 0;// 空数组
		}
		int toIndex = page * size;
		if (toIndex >= data.size()) {
			toIndex = data.size();
		}
		return data.subList(fromIndex, toIndex);
	}
    
    @GetMapping("/depts")
    public ResultDto<List<WFDept>> allDepts(QueryModel queryModel) {
    	WFSystem systemInfo = OrgCache.getSystemInfo(queryModel.getCondition().get("systemId").toString());
    	List<WFDept> depts = systemInfo.getDepts();
        return new ResultDto<List<WFDept>>(depts);
    }
    
    @GetMapping("/orgs")
    public ResultDto<List<WFOrg>> allOrgs(QueryModel queryModel) {
    	WFSystem systemInfo = OrgCache.getSystemInfo(queryModel.getCondition().get("systemId").toString());
    	List<WFOrg> depts = systemInfo.getOrgs();
        return new ResultDto<List<WFOrg>>(depts);
    }
    
    @GetMapping("/roles")
    public ResultDto<List<WFRole>> allRoles(QueryModel queryModel) {
    	WFSystem systemInfo = OrgCache.getSystemInfo(queryModel.getCondition().get("systemId").toString());
    	List<WFRole> depts = systemInfo.getRoles();
        return new ResultDto<List<WFRole>>(depts);
    }
    
    @GetMapping("/dutys")
    public ResultDto<List<WFDuty>> allDutys(QueryModel queryModel) {
    	WFSystem systemInfo = OrgCache.getSystemInfo(queryModel.getCondition().get("systemId").toString());
    	List<WFDuty> depts = systemInfo.getDutys();
        return new ResultDto<List<WFDuty>>(depts);
    }
    
    @GetMapping("/info")
    public ResultDto<Map<String,String>> userInfo(QueryModel queryModel) {
    	String systemId = queryModel.getCondition().get("systemId").toString();
    	String code = queryModel.getCondition().get("code").toString();
    	log.debug("入参："+queryModel);
    	
    	Map<String,String> userInfo = new HashMap<String,String>();
    	
    	if(code.startsWith("U.")){
    		WFUser userT = OrgCache.getUserInfo(systemId, code.substring(2));
    		userInfo.put("code", "U."+userT.getUserId());
    		userInfo.put("name", userT.getUserName());
    		userInfo.put("type", "success");
    	}else if(code.startsWith("R.")){
    		List<WFRole> temp = OrgCache.getSystemInfo(systemId).getRoles();
    		for(WFRole tempI:temp){
    			if(tempI.getRoleId().equals(code.substring(2))){
    				userInfo.put("code", "R."+tempI.getRoleId());
    	    		userInfo.put("name", tempI.getRoleName());
    	    		userInfo.put("type", "info");
    	    		break;
    			}
    		}
    	}else if(code.startsWith("O.")){
    		List<WFOrg> temp = OrgCache.getSystemInfo(systemId).getOrgs();
    		for(WFOrg tempI:temp){
    			if(tempI.getOrgId().equals(code.substring(2))){
    				userInfo.put("code", "O."+tempI.getOrgId());
    	    		userInfo.put("name", tempI.getOrgName());
    	    		userInfo.put("type", "warning");
    	    		break;
    			}
    		}
    	}else if(code.startsWith("G.")){
    		List<WFDuty> temp = OrgCache.getSystemInfo(systemId).getDutys();
    		for(WFDuty tempI:temp){
    			if(tempI.getDutyId().equals(code.substring(2))){
    				userInfo.put("code", "G."+tempI.getDutyId());
    	    		userInfo.put("name", tempI.getDutyName());
    	    		userInfo.put("type", "");
    	    		break;
    			}
    		}
    	}else if(code.startsWith("D.")){
    		List<WFDept> temp = OrgCache.getSystemInfo(systemId).getDepts();
    		for(WFDept tempI:temp){
    			if(tempI.getDeptId().equals(code.substring(2))){
    				userInfo.put("code", "D."+tempI.getDeptId());
    	    		userInfo.put("name", tempI.getDeptName());
    	    		userInfo.put("type", "danger");
    	    		break;
    			}
    		}
    	}else{
    		log.error("未知用户类型["+code+"]");
    		throw new WorkflowException("未知用户类型["+code+"]");
    	}
    	
        return new ResultDto<Map<String,String>>(userInfo);
    }
    
    public static void main(String[] args) {
		System.out.println("U.2".substring(2));
	}
}