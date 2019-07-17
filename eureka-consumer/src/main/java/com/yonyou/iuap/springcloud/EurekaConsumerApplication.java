package com.yonyou.iuap.springcloud;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.yonyou.cloud.middleware.rpc.RPCBeanFactory;
import com.yonyou.iuap.springcloud.thirdpart.service.IGoodsService;

@SpringBootApplication
@EnableFeignClients
public class EurekaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaConsumerApplication.class, args);
	}

	@Bean
	public RPCBeanFactory factory() {
		return new RPCBeanFactory("spring-eureka-consumer-bbc",
				Collections.singletonList(IGoodsService.class.getName()));
	}
}
