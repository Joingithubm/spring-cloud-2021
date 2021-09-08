package com.athome.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/9/8 16:43
 * @Version 1.0
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule rule(){
        return new RandomRule();
    }
}
