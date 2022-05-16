package com.hanhan.springcloud.service.impl;

import com.hanhan.springcloud.dao.AccountDao;
import com.hanhan.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("account扣除金额开始");
        accountDao.decrease(userId, money);
        log.info("account扣除金额结束");
    }
}
