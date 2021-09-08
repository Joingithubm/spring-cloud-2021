package com.athome.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/9/8 17:40
 * @Version 1.0
 */
public interface MyLoadBalance {

    ServiceInstance instance(List<ServiceInstance> instanceList);
}
