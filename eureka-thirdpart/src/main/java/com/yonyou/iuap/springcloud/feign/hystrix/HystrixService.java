package com.yonyou.iuap.springcloud.feign.hystrix;

import org.springframework.stereotype.Component;

import com.yonyou.iuap.springcloud.feign.FeignInvokeService;

@Component
public class HystrixService implements FeignInvokeService {
	@Override
	public String order(String userName,String goodsName) {
		// TODO Auto-generated method stub
		return "hello,"+ goodsName + "'s info query error,please check network connections !";
	}
}
