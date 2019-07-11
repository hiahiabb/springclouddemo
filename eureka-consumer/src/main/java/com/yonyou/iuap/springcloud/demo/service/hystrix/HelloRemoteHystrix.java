package com.yonyou.iuap.springcloud.demo.service.hystrix;

import com.yonyou.iuap.springcloud.demo.service.HelloRemote;

public class HelloRemoteHystrix implements HelloRemote{

	@Override
	public String hello(String name) {
		// TODO Auto-generated method stub
		return "hello " + name + ",this message send failed";
	}

}
