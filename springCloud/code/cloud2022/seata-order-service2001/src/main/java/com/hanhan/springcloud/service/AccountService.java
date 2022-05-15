package com.hanhan.springcloud.service;

import com.hanhan.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-account-service")
public interface AccountService {
    @PostMapping("/account/decrease")
    CommonResult decrease(@RequestParam("productId")Long productId, @RequestParam("money") BigDecimal money);

}
