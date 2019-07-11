package com.yonyou.iuap.springcloud.demo.service.hystrix;

import org.springframework.stereotype.Component;

import com.yonyou.iuap.springcloud.demo.service.HelloRemote;

@Component
public class HelloRemoteHystrix implements HelloRemote{

	@Override
	public String hello(String name) {
		// TODO Auto-generated method stub
		return "hello " + name + ",this message send failed";
	}

}
