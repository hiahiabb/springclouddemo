package com.yonyou.iuap.springcloud.thirdpart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.iuap.springcloud.thirdpart.service.IOrderService;

@RestController
public class OrderController {

	@Autowired
	private IOrderService orderService;
	
	@RequestMapping("/order")
	public Object order(@RequestParam String userName,@RequestParam String goodsName) {
		return orderService.order(userName, goodsName);
	}
}
