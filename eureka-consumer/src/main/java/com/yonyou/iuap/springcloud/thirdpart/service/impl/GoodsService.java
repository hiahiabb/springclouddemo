package com.yonyou.iuap.springcloud.thirdpart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonyou.iuap.springcloud.feign.FeignInvokeService;
import com.yonyou.iuap.springcloud.thirdpart.service.IGoodsService;
@Service
public class GoodsService implements IGoodsService{

	@Autowired
	private FeignInvokeService feginInvokeService;
	
	@Override
	public String getGoodsInfoByName(String userName,String goodsName) {
		// TODO Auto-generated method stub
		feginInvokeService.order(userName, goodsName);
		return goodsName + " made in China";
	}

}
