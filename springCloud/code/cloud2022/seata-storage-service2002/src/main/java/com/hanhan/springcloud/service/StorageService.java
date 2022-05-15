package com.hanhan.springcloud.service;

import com.hanhan.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface StorageService {

    void decrease(Long productId, Integer count);
}
