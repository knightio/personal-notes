package com.hanhan.springcloud.service.impl;

import com.hanhan.springcloud.dao.StorageDao;
import com.hanhan.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;
    /**
     * @param productId
     * @param count
     */
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("storage扣减库存开始");
        storageDao.decrease(productId,count);
        log.info("storage扣减库存结束");
    }
}
