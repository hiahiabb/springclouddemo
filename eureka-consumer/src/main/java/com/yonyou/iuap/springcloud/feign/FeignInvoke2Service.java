package com.yonyou.iuap.springcloud.feign;

import com.yonyou.iuap.springcloud.demo.service.IHelloService;
import com.yonyou.iuap.springcloud.feign.hystrix.HystrixService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="spring-eureka-provider-bbc",contextId="spring-eureka-provider2",fallback = HystrixService.class)
public interface FeignInvoke2Service extends IHelloService{

	@RequestMapping(value="/hello")
	public String hello(@RequestParam(name = "name") String name);
}
