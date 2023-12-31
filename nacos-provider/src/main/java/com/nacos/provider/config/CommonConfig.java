package com.nacos.provider.config;

import com.alibaba.cloud.sentinel.datasource.converter.JsonConverter;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {
    @Value(value = "${spring.application.name}")
    private String appName;

    @Bean
    public NacosDataSource nacosFlowDataSource() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new NacosDataSource("127.0.0.1:8848", "SENTINEL_GROUP", appName+"-flow-rules", new JsonConverter(objectMapper, FlowRule.class));
    }

    @Bean
    public NacosDataSource nacosDegradeDataSource() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new NacosDataSource("127.0.0.1:8848", "SENTINEL_GROUP", appName+"-degrade-rules", new JsonConverter(objectMapper, DegradeRule.class));
    }

}
