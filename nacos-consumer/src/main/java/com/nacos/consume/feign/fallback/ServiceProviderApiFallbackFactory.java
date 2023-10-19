package com.nacos.consume.feign.fallback;

import com.nacos.consume.feign.api.ServiceProviderApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ServiceProviderApiFallbackFactory implements FallbackFactory<ServiceProviderApi> {
    @Override
    public ServiceProviderApi create(Throwable cause) {
        return new ServiceProviderApi() {
            @Override
            public String get(String param) {
                return "被回滚了";
            }
        };
    }
}
