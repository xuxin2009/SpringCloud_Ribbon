package com.sean.house.movieservice;


import com.sean.house.movieservice.MyRule.CustomLoadBalancerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author sean
 */
@SpringBootApplication
@LoadBalancerClients(defaultConfiguration = {CustomLoadBalancerConfiguration.class})
public class MoiveserviceApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(MoiveserviceApplication.class, args);
    }

}
