package com.yonyou.iuap.springcloud.demo.controller;

import com.yonyou.iuap.springcloud.demo.service.IHelloService;
import com.yonyou.iuap.springcloud.feign.FeignInvoke2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.iuap.springcloud.feign.FeignInvokeService;

@RestController
public class ConsumerController {

	@Autowired
	//@Qualifier("spring-eureka-provider")
	private FeignInvoke2Service feignInvoke2Service;
	
	@RequestMapping("/hello/{name}")
	public Object index(@PathVariable("name") String name) {
		return feignInvoke2Service.hello(name);
	}
}
