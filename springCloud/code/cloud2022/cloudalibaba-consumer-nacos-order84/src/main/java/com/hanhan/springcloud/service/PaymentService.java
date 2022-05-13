package com.hanhan.springcloud.service;

import com.hanhan.entities.CommonResult;
import com.hanhan.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentService.class)
public interface PaymentService {

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id")Long id);
}
