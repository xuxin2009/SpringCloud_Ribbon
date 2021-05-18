package com.sean.house.movieservice.controller;

import com.sean.house.movieservice.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author : sean
 * @version V1.0
 * @Project: SpringCloudDemo
 * @Package com.sean.house.moiveservice.controller
 * @Description: TODO
 * @date Date : 2021年05月13日 23:08
 */
@RestController
@Slf4j
public class MoiveController {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable long id)
    {
        //hardCode硬编码方式不可取
       //return restTemplate.getForObject("http://localhost:8000/" + id,User.class);

        //修改成通过微服务的虚拟主机名来访问
        return restTemplate.getForObject("http://userservice/"+id,User.class);
    }

    @GetMapping("/log-user-instance")
    public void logUserInstances()
    {
        ServiceInstance serviceInstance = loadBalancerClient.choose("userservice");
        log.info("serviceId-->{},Host-->{},port-->{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());
    }
}