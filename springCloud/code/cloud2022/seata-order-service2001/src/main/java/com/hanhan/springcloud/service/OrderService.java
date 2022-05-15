package com.hanhan.springcloud.service;

import com.hanhan.springcloud.domain.Order;
import org.springframework.stereotype.Service;

public interface OrderService {
    void create(Order order);
}
