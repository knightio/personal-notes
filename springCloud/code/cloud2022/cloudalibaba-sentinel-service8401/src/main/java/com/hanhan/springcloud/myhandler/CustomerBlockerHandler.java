package com.hanhan.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hanhan.entities.CommonResult;
import com.hanhan.entities.Payment;

public class CustomerBlockerHandler {
    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(444,"按客户自定义限流,global handlerException--1");
    }
    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(444,"按客户自定义限流,global handlerException--2");
    }
}
