package com.hanhan.springcloud.controller;

import com.hanhan.entities.CommonResult;
import com.hanhan.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        /**
         * A277388F-5FF8-9503-2E12-0ADF4934FD3F
         * 406FB0E6-1DC0-DA00-039A-7E795776D981
         * F20DEC21-26A9-6751-70FD-FD9852E5EEF1
         */
        hashMap.put(1L,new Payment(1L, "A277388F-5FF8-9503-2E12-0ADF4934FD3F"));
        hashMap.put(2L,new Payment(2L, "406FB0E6-1DC0-DA00-039A-7E795776D981"));
        hashMap.put(3L,new Payment(3L, "F20DEC21-26A9-6751-70FD-FD9852E5EEF1"));
    }
    @GetMapping("/payment/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id")Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult(200,"success,serverPort:"+serverPort,payment);
        return result;
    }
}
