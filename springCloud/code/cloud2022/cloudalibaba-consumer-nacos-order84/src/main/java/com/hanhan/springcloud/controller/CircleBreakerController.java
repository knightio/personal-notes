package com.hanhan.springcloud.controller;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hanhan.entities.CommonResult;
import com.hanhan.entities.Payment;
import com.hanhan.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")//没有配置
//    @SentinelResource(value = "fallback",fallback = "handlerFallback")//fallback只负责业务异常
//    @SentinelResource(value = "fallback",blockHandler = "blockHandler")//只负责sentinel控制台违规
    @SentinelResource(value = "fallback",blockHandler = "blockHandler",
            fallback = "handlerFallback",
            exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL+"/paymentSql/"+id,CommonResult.class,id);
        if (id == 4){
            throw new IllegalArgumentException("IllegalArgumentException 非法参数异常");
        }else if(result.getData() == null){
            throw new NullPointerException("NullPointerException,该id无对应数据，空指针异常");
        }
        return result;
    }
    public CommonResult<Payment> handlerFallback(@PathVariable("id") Long id,Throwable e){
        Payment payment = new Payment(id,null);
        return new CommonResult(444,"兜底异常，异常信息："+e.getMessage(),payment);
    }
    public CommonResult<Payment> blockHandler(@PathVariable("id") Long id, BlockException e){
        Payment payment = new Payment(id,null);
        return new CommonResult(444,"blockHandler-sentinel限流，异常信息："+e.getMessage(),payment);

    }

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id")Long id){
        return paymentService.paymentSql(id);
    }


}
