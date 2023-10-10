package com.nacos.consume.feign.api;

import com.nacos.consume.feign.fallback.ServiceProviderApiFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "service-provider", path = "/v1/test", fallbackFactory = ServiceProviderApiFallbackFactory.class)
public interface ServiceProviderApi {

    @RequestMapping(value = "/postStr", method = GET)
    @ResponseBody
    String get(@RequestParam("param")String param);
}
