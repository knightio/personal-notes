package com.hanhan.springcloud.service;

import com.hanhan.entities.CommonResult;
import com.hanhan.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSql(Long id) {
        return new CommonResult<>(444,"服务降级返回");
    }
}
