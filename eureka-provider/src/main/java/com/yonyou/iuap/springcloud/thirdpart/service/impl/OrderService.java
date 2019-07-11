package com.yonyou.iuap.springcloud.thirdpart.service.impl;

import org.springframework.stereotype.Service;

import com.yonyou.iuap.springcloud.thirdpart.service.IOrderService;

@Service
public class OrderService implements IOrderService {

	@Override
	public String order(String userName,String goodsName) {
		// TODO Auto-generated method stub
		String str = "hello, " + userName +"购买了一件商品,商品名为:" + goodsName;
		
		return str;
	}

}
