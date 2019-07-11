package com.yonyou.iuap.springcloud.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.iuap.springcloud.feign.FeignInvokeService;

@RestController
public class ConsumerController {

	@Autowired
	private FeignInvokeService feginInvokeService;
	
	@RequestMapping("/hello/{name}")
	public Object index(@PathVariable("name") String name) {
		return feginInvokeService.hello(name);
	}
}
