package com.deepexi.springcloud.service;

import com.alibaba.fastjson.JSONObject;
import com.deepexi.springcloud.entity.Components;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @author: hc
 */

@FeignClient(value = "DEEPEXI-SERVICE")
public interface ComponentsServiceFeign {

    @RequestMapping(value = "/provider/get.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json;charset=UTF-8")
    public List getValue(@RequestBody int COM_ID);

}