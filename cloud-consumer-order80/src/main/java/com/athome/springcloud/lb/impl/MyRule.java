package com.athome.springcloud.lb.impl;

import com.athome.springcloud.lb.MyLoadBalance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/9/8 17:41
 * @Version 1.0
 */
@Component
public class MyRule implements MyLoadBalance {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private final int cntIndex(){
        int current ;
        int nextIndex;
        for (;;) {
            current = atomicInteger.get();
            nextIndex = current + 1;
            if (atomicInteger.compareAndSet(current,nextIndex)){
                return nextIndex;
            }
        }
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> instanceList) {

        int all = cntIndex();
        int index = all % instanceList.size();
        return instanceList.get(index);
    }
}
