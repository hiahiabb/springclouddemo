package com.yonyou.iuap.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EurekaThirdpartApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaThirdpartApplication.class, args);
	}

}
