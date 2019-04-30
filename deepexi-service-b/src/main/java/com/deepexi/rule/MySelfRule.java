package com.deepexi.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: hc
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule getRule() {
        //return new RoundRobinRule(); // 随机
        //return new RandomRule(); // 定义访问规则，默认轮询、随机、分权等
        return new MySelfRandomRule(); // 每台机器访问5次
    }

}
