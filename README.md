# SpringScheduleAdmin(简称ssa)

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
![java version](https://img.shields.io/badge/JAVA-1.8+-green.svg)

#### 介绍

SpringScheduleAdmin 是一个用于管理spring自带的Scheduled注解，定时任务的管理插件，能够方便的在项目中管理你的SpringSchedule的定时任务

#### 安装教程

* 第一步：引入依赖到项目中

```xml

<dependency>
    <groupId>com.plumelog</groupId>
    <artifactId>spring-boot-schedule-admin-starter</artifactId>
    <version>${version}</version>
    <scope>compile</scope>
</dependency>
```
* 第二步:检查你项目中的静态文件配置

情况一：如果你的项目访问ssa页面空白，说明没有配置可以访问静态文件请做如下配置 在application.properties配置：

```properties
spring.mvc.static-path-pattern=/**
spring.resources.static-locations=classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/
```

情况二：拦截器会覆盖spring.resources.static-locations，如果项目中有拦截器，需要在拦截器里配置静态文件访问

示例：

```java
import com.plumelog.core.PlumeLogTraceIdInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class TraceIdInterceptorsConfig extends WebMvcConfigurerAdapter{
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"};
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //就是这句addResourceLocations，加上静态文件访问路径
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PlumeLogTraceIdInterceptor());
        super.addInterceptors(registry);
    }

}
```
* 第三步:启动你的项目

访问你的项目路径后面加上  `ssa/#/`

例如：`http://localhost:8080/ssa/#/` <- 这里的`/#/`不可省略

![avatar](/pic/img.png)
![avatar](/pic/img_1.png)
