package com.yonyou.iuap.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yonyou.iuap.springcloud.feign.hystrix.HystrixService;

@FeignClient(name="spring-eureka-consumer",fallback= HystrixService.class)
public interface FeignInvokeService {
	@RequestMapping("/order")
	String order(@RequestParam(name="userName")String userName,@RequestParam(name="goodsName")String goodsName);
}
