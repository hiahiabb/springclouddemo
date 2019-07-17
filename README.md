# springclouddemo

**注意!注意!注意! 重要的事情说三遍,在微服务治理平台中,resources/applicationproperties文件中的 spring.application.name 属性不能在微服务治理平台中注册,如果已经被别人注册,则服务无法启动!如果启动不成功,需要修改该属性对应的值.**

### SpringCloudDemo 目录结构说明

* **eureka-server**  eureka服务提供方
* **eureka-provider** eureka服务提供者
* **eureka-consumer** eureka服务消费者



### SpringCloudDemo 注意事项

* 目前iuap 微服务平台支持的是 接口调用方式，需要在provider和consumer同时都存在接口。



## SpringCloud 迁移到 iuap微服务治理平台

Spring cloud 迁移到iuap微服务治理平台，会存在两种情况，一种是eureka server仍然启用，一种是彻底拥抱iuap微服务治理平台。下面会对这两种情况分别进行描述。

```
注意:
此处暂时约定server 为eureka服务提供者; provider 为服务提供方;consumer为服务消费方
```

### eureka server仍然启用的情况

#### provider 端调整

* 修改 provider 的 `pom.xml`文件,增加如下配置

```xml
<!-- iuap 微服务治理平台 引入包 start -->
		<dependency>
			<groupId>com.yonyou.cloud.middleware</groupId>
			<artifactId>middleware</artifactId>
			<version>5.2.1-SNAPSHOT</version>
		</dependency>
		<dependency>
			<groupId>com.yonyou.cloud.middleware</groupId>
			<artifactId>iris-springboot-support</artifactId>
			<version>5.2.1-SNAPSHOT</version>
		</dependency>
		<!-- 服务调用方需要依赖下方的包，如果是服务提供方则不需要 -->
		<!-- <dependency> 
			<groupId>com.yonyou.cloud.middleware</groupId> 
			<artifactId>iris-springcloud-openfeign</artifactId> 
			<version>5.2.1-SNAPSHOT</version> 
		</dependency> -->
		<!-- iuap 微服务治理平台 引入包 end -->
```

* 在`application.properties `件中增加如下配置

```properties
#test会将微服务信息注册到测试环境中,且必须在相同的profile下才能进行通信
spring.profiles.active=test
access.key=XXXXXXX
access.secret=XXXXXX
```

`spring.profiles.active` provider和consumer必须在一profile下才能进行通信

`access.key`和`access.secret`是在开发者中心下申请的.

![1562835729471](imgs/AccessKey1)



![1562835827763](imgs/AcessKey2)

* 在启动类上增加一个 `@Bean`

``` java
@Bean
public RPCBeanFactory factory() {
    return new RPCBeanFactory("spring-eureka-provider",
                              Collections.singletonList(IHelloService.class.getName()));
}
```

`spring-eureka-provider`是指服务提供方在`application.properties`文件中的`spring.application.name`.

`IHelloService`为服务提供方提供的对外接口,这个会作为元数据注入到iuap微服务治理平台中的API列表中.

![1562836302646](imgs/microsoft1)

#### consumer端调整

* 修改`pom.xml`文件

``` xml
<!-- iuap 微服务治理平台 引入包 start -->
		<dependency>
			<groupId>com.yonyou.cloud.middleware</groupId>
			<artifactId>middleware</artifactId>
			<version>5.2.1-SNAPSHOT</version>
		</dependency>
		<dependency>
			<groupId>com.yonyou.cloud.middleware</groupId>
			<artifactId>iris-springboot-support</artifactId>
			<version>5.2.1-SNAPSHOT</version>
		</dependency>
		<!-- 服务调用方需要依赖下方的包，如果是服务提供方则不需要 -->
		<dependency>
			<groupId>com.yonyou.cloud.middleware</groupId>
			<artifactId>iris-springcloud-openfeign</artifactId>
			<version>5.2.1-SNAPSHOT</version>
		</dependency>

		<!-- iuap 微服务治理平台 引入包 end -->
```



* 修改`application.properties`文件

``` properties
#test会将微服务信息注册到测试环境中,且必须在相同的profile下才能进行通信
spring.profiles.active=test
access.key=XXXXXXX
access.secret=XXXXXX
```



### eureka server 不启用的情况

因为eureka server 不启动,所以要把provider 和 consumer 工程下的pom.xml文件中涉及到eureka的maven依赖去掉

#### provider 调整

* 去掉`spring-cloud-starter-netflix-eureka-server`依赖,在启动类中去掉`@EnableDiscoveryClient`注解和其引入的包;
* 在pom.xml文件中引入 spring-boot-starter-web 包

``` xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

* 其他步骤参考 eureka server 启用下 provider的调整步骤

#### consumer 调整

 

- 去掉`spring-cloud-starter-netflix-eureka-server`依赖,在启动类中去掉`@EnableDiscoveryClient`注解和其引入的包;
- 在pom.xml文件中引入 `spring-boot-starter-web` 和`spring-cloud-starter-netflix-ribbon`包

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

- 其他步骤参考 eureka server 启用下 consumer的调整步骤



### 多接口实现方案

因为iuap微服务治理平台是基于接口进行设计的,在实际业务中会存在多个接口,需要指定多个Feign接口的情况,但是如果多个接口在使用`@FeignClient`注解只指定`name`的时候且`name`值相同的情况下,程序在启动的时候会报错,这时候就需要在`@FeignClient`注解上增加一个参数`contextId`,用`contextId`区分不同的业务功能名称.`name`值不需要调整.

参考示例:

``` java
@FeignClient(name="spring-eureka-provider",contextId="spring-eureka-provider2",fallback = HystrixService.class)
public interface FeignInvoke2Service extends IHelloService{

	@RequestMapping(value="/hello")
	public String hello(@RequestParam(name = "name") String name);
}
....
    
@FeignClient(name="spring-eureka-provider",contextId="spring-eureka-provider1",fallback = HystrixService.class)
public interface FeignInvokeService extends IOrderService{
	
	@RequestMapping(value="/order")
	public String order(@RequestParam(name="userName")String userName,@RequestParam(name="goodsName")String goodsName);
}
```



### 三节点链路追踪

spring-eureka-thirpart --> spring-eureka-consumer --> spring-eureka-provider



项目启动后,访问 http://localhost:9002/order/tom?goodsName=abc;

然后在微服务治理中心 打开测试环境的 spring-eureka-provider,查看API 列表,选择 IOrderService下的接口 order,点击链路追踪,选择最近的一条调用记录,点击查看可以查看到如下调用链路图.



![1562848195323](imgs/三节点链路追踪)
