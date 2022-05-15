package com.hanhan.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hanhan.entities.CommonResult;
import com.hanhan.entities.Payment;
import com.hanhan.springcloud.myhandler.CustomerBlockerHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流",new Payment(2022L,"serial001"));
    }
    public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按url名称限流",new Payment(2022L,"serial002"));
    }

    @GetMapping("/rateLimit/customerBlockerHandler")
    @SentinelResource(value = "customerBlockerHandler",
            blockHandlerClass = CustomerBlockerHandler.class,
            blockHandler = "handlerException2")
    public CommonResult customerBlockerHandler(){
        return new CommonResult(200,"按客户自定义限流",new Payment(2022L,"serial003"));
    }

}
