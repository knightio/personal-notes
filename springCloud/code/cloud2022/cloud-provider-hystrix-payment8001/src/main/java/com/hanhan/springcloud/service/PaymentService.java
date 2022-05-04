package com.hanhan.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 访问正常
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_OK,id:"+ id + "\t";
    }

    /**
     * 访问出错
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id){
        //计算异常，超时异常，都可以降级处理
//        int num = 10/0;
        int timeOut = 3;
        try {
            TimeUnit.SECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_OK,id:"+ id + "\t 耗时(秒)：" + timeOut;
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池：" + Thread.currentThread().getName() + " 8001系统繁忙！,id:"+ id ;
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率到达多少跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if(id<0){
            throw new RuntimeException("id不能小于0！");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(Integer id){
        return "id不能为负数，稍后重试！";
    }
}
