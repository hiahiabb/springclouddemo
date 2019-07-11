package com.yonyou.iuap.springcloud.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.iuap.springcloud.demo.service.IHelloService;

@RestController
public class HelloController {
	
	@Autowired
	private IHelloService helloService;
	
	@RequestMapping("/hello")
	public Object index(@RequestParam String name) {
		
		return helloService.hello(name);
	}

}
