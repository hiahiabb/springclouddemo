package com.yonyou.iuap.springcloud.feign.hystrix;

import com.yonyou.iuap.springcloud.feign.FeignInvoke2Service;
import org.springframework.stereotype.Component;

@Component
public class Hystrix2Service implements FeignInvoke2Service {

	@Override
	public String hello(String name) {
		return "hello " + name + "'s message send failed";
	}
}
