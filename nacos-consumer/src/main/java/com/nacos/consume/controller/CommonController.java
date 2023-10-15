package com.nacos.consume.controller;

import com.nacos.consume.feign.api.ServiceProviderApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/v1/test")
public class CommonController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceProviderApi serviceProviderApi;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String test(@RequestParam(value = "param", required = false) String param, @RequestParam(value = "a", required = false) String a) {
        return serviceProviderApi.get(param);
    }

}
