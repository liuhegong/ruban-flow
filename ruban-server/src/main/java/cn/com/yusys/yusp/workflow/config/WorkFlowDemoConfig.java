package cn.com.yusys.yusp.workflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.yusys.yusp.workflow.demo.BeanBizDemoImpl;
import cn.com.yusys.yusp.workflow.demo.BeanRoute1DemoImpl;
import cn.com.yusys.yusp.workflow.demo.BeanRoute2DemoImpl;
import cn.com.yusys.yusp.workflow.demo.BeanUserFilter1DemoImpl;
import cn.com.yusys.yusp.workflow.demo.BeanUserFilter2DemoImpl;

@Configuration
public class WorkFlowDemoConfig{
	
	@Bean("demo")
	public BeanBizDemoImpl BeanBizDemoImpl(){
		return new BeanBizDemoImpl();
	}
	
	@Bean("route1")
	public BeanRoute1DemoImpl beanRoute1DemoImpl(){
		return new BeanRoute1DemoImpl();
	}
	
	@Bean("route2")
	public BeanRoute2DemoImpl beanRoute2DemoImpl(){
		return new BeanRoute2DemoImpl();
	}
	
	@Bean("userFilter1")
	public BeanUserFilter1DemoImpl beanUserFilter1DemoImpl(){
		return new BeanUserFilter1DemoImpl();
	}
	
	@Bean("userFilter2")
	public BeanUserFilter2DemoImpl beanUserFilter2DemoImpl(){
		return new BeanUserFilter2DemoImpl();
	}

}
