package com.deepexi.springcloud;

import com.deepexi.rule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="DEEPEXI-SERVICE", configuration = MySelfRule.class)
// 在这个微服务启动时就能加载自定义的配置类, 这个MySelfRule不能放在@ComponentScan所
// 扫描的包及其子包下，否则我们自定义的Ribbon类就会被客户端共享，达不到私有定制化效果
public class DeepexiServiceB {
    public static void main(String[] args) {
        SpringApplication.run(DeepexiServiceB.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean servletServletRegistrationBean = new ServletRegistrationBean(dispatcherServlet);
        servletServletRegistrationBean.addUrlMappings("*.do");
        return servletServletRegistrationBean;
    }
}
