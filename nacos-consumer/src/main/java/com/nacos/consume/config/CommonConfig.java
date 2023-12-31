package com.nacos.consume.config;

import com.alibaba.cloud.sentinel.datasource.converter.JsonConverter;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        return restTemplate;
    }

    @Bean
    public NacosDataSource nacosFlowDataSource() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new NacosDataSource("127.0.0.1:8848", "SENTINEL_GROUP", "service-consumer-flow-rules", new JsonConverter(objectMapper, FlowRule.class));
    }

    @Bean
    public NacosDataSource nacosDegradeDataSource() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new NacosDataSource("127.0.0.1:8848", "SENTINEL_GROUP", "service-consumer-degrade-rules", new JsonConverter(objectMapper, DegradeRule.class));
    }

    @Bean
    public NacosDataSource nacosParamDataSource() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new NacosDataSource("127.0.0.1:8848", "SENTINEL_GROUP", "service-consumer-param-rules", new JsonConverter(objectMapper, ParamFlowRule.class));
    }

}
