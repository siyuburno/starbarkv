package com.nacos.provider.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/v1/test")
@RefreshScope
public class ConfigController {
    @Value(value = "${say.hello.world}")
    private String helloWorld;

    @RequestMapping(value = "/postStr", method = GET)
    @ResponseBody
    @SentinelResource(value = "get")
    public String get(@RequestParam(value = "param", required = false) String param) {
        System.out.println(param);
        return helloWorld;
//        Random random = new Random();
//        if (random.nextInt(10)%5 == 0) {
//            return helloWorld;
//        }else {
//            throw new RuntimeException("调用失败");
//        }
    }

}
