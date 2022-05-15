package com.hanhan.springcloud.controller;

import com.hanhan.springcloud.domain.CommonResult;
import com.hanhan.springcloud.domain.Order;
import com.hanhan.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    public OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult<>(200,"订单创建成功");
    }
}
