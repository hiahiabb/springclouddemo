package com.yonyou.iuap.springcloud.thirdpart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.iuap.springcloud.feign.FeignInvokeService;

@RestController
public class OrderConsumerController {

	@Autowired
	private FeignInvokeService feignInvokeService;

	@RequestMapping("/order")
	public Object order(@RequestParam String userName,@RequestParam String goodsName) {
		
		return feignInvokeService.order(userName, goodsName);
	}
}
