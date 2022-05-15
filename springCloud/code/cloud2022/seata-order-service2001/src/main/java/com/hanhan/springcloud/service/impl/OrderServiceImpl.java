package com.hanhan.springcloud.service.impl;

import com.hanhan.springcloud.dao.OrderDao;
import com.hanhan.springcloud.domain.Order;
import com.hanhan.springcloud.service.AccountService;
import com.hanhan.springcloud.service.OrderService;
import com.hanhan.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private AccountService accountService;

    @Resource
    private StorageService storageService;


    /**
     * @param order
     */
    @Override
    public void create(Order order) {
        log.info("新建订单！");
        orderDao.create(order);
        log.info("调用库存服务，减少库存！");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("调用账户服务，减少金额！");
        accountService.decrease(order.getProductId(), order.getMoney());
        log.info("修改订单状态！");
        orderDao.update(order.getId(), 0);
        log.info("结束！");


    }
}
