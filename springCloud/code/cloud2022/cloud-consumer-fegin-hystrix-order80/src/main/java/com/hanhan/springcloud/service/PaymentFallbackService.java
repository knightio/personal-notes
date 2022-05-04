package com.hanhan.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    /**
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "fallback_ok";
    }

    /**
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "fallback_timeout";
    }
}
