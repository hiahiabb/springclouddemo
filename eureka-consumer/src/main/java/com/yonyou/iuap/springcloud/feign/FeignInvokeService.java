package com.yonyou.iuap.springcloud.feign;

import com.yonyou.iuap.springcloud.feign.hystrix.HystrixService;
import com.yonyou.iuap.springcloud.thirdpart.service.IOrderService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="spring-eureka-provider",contextId="spring-eureka-provider1",fallback = HystrixService.class)
public interface FeignInvokeService extends IOrderService{

	@RequestMapping(value="/order")
	public String order(@RequestParam(name="userName")String userName,@RequestParam(name="goodsName")String goodsName);
}
