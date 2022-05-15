package com.hanhan.springcloud.controller;

import com.hanhan.springcloud.domain.CommonResult;
import com.hanhan.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class StorageController {

    @Resource
    private StorageService storageService;

    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId,Integer count){
        storageService.decrease(productId, count);
        return new CommonResult(200,"扣减库存成功！");
    }
}
