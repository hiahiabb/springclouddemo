package com.yonyou.iuap.springcloud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yonyou.cloud.middleware.rpc.RPCBeanFactory;
import com.yonyou.iuap.springcloud.demo.service.IHelloService;
import com.yonyou.iuap.springcloud.thirdpart.service.IOrderService;

@SpringBootApplication
//@EnableDiscoveryClient
public class EurekaProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaProviderApplication.class, args);
	}

	@Bean
	public RPCBeanFactory factory() {
		return new RPCBeanFactory("spring-eureka-provider",
				new ArrayList<String>(Arrays.asList(IHelloService.class.getName(),IOrderService.class.getName())));
	}
	
}
