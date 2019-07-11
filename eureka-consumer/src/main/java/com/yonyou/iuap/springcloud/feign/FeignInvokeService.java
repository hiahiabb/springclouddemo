package com.yonyou.iuap.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yonyou.iuap.springcloud.demo.service.IHelloService;
import com.yonyou.iuap.springcloud.feign.hystrix.HystrixService;
import com.yonyou.iuap.springcloud.thirdpart.service.IOrderService;

@FeignClient(name="spring-eureka-provider",fallback = HystrixService.class)
public interface FeignInvokeService extends IOrderService,IHelloService{

	@RequestMapping(value="/hello")
	public String hello(@RequestParam(name="name") String name);
	
	@RequestMapping(value="/order")
	public String order(@RequestParam(name="userName")String userName,@RequestParam(name="goodsName")String goodsName);
}
