package com.sean.house.movieservice.MyRule;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * @author : sean
 * @version V1.0
 * @Project: SpringCloudDemo
 * @Package com.sean.house.movieservice.config
 * @Description: TODO
 * @date Date : 2021年05月17日 21:29
 */

public class CustomLoadBalancerConfiguration {

        @Bean
        ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
                                                                LoadBalancerClientFactory loadBalancerClientFactory) {
            String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
            return new RandomLoadBalancer(loadBalancerClientFactory
                    .getLazyProvider(name, ServiceInstanceListSupplier.class),
                    name);

    }
}
