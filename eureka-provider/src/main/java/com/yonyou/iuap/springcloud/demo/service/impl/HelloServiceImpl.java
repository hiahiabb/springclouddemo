package com.yonyou.iuap.springcloud.demo.service.impl;

import org.springframework.stereotype.Service;

import com.yonyou.iuap.springcloud.demo.service.IHelloService;

@Service
public class HelloServiceImpl implements IHelloService {

	@Override
	public String hello(String name) {
		return "hello " + name +",this is first message";
	}

}
