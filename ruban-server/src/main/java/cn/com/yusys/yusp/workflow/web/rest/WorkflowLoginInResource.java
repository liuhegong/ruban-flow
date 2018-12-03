package cn.com.yusys.yusp.workflow.web.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.workflow.web.dto.ResultDto;

@RestController
public class WorkflowLoginInResource {
	
	@PostMapping("/user/login")
	public ResultDto login(@RequestBody Map userInfo){
		ResultDto resultDto = new ResultDto();
		Map<String,Object> da = new HashMap<String,Object>();
		da.put("token", "admin");
		resultDto.setData(da);
		return resultDto;
	}
	
	@GetMapping("/user/info")
	public ResultDto login(String token){
		ResultDto resultDto = new ResultDto();
		Map<String,Object> da = new HashMap<String,Object>();
		da.put("name", "admin");
		
		List<String> roles = new ArrayList<>();
		roles.add("admin");
		da.put("roles",roles);
		
		da.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
		
		resultDto.setData(da);
		return resultDto;
	}
	
	@PostMapping("/user/logout")
	public ResultDto logout(){
		ResultDto resultDto = new ResultDto();
		Map<String,Object> da = new HashMap<String,Object>();
		da.put("name", "admin");
		
		List<String> roles = new ArrayList<>();
		roles.add("admin");
		da.put("roles",roles);
		
		da.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
		
		resultDto.setData(da);
		return resultDto;
	}
}