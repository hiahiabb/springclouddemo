package com.yonyou.iuap.springcloud.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yonyou.iuap.springcloud.demo.service.hystrix.HelloRemoteHystrix;

@FeignClient(name="spring-eureka-provider",fallback = HelloRemoteHystrix.class)
public interface HelloRemote extends IHelloService{
	
	@RequestMapping(value="/hello")
	public String hello(@RequestParam(name="name") String name);
}
