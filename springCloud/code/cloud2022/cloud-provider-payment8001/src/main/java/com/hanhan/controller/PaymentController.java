package com.hanhan.controller;

import com.hanhan.entities.CommonResult;
import com.hanhan.entities.Payment;
import com.hanhan.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author hanhan12
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("****插入结果："+result);

        if(result>0){
            return new CommonResult<>(200,"插入成功",result);
        }else {
            return new CommonResult<>(444,"插入失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果："+payment);

        if(!ObjectUtils.isEmpty(payment)){
            return new CommonResult<>(200,"查询成功",payment);
        }else {
            return new CommonResult<>(444,"没有记录，查询id:"+id,null);
        }
    }
}
