package com.deepexi.springcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.deepexi.springcloud.entity.Components;
import com.deepexi.springcloud.service.ComponentsServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class ComponentsController {

    @Autowired
    private ComponentsServiceFeign serviceFeign;

    @RequestMapping(value = "/consumer/get.do", method = RequestMethod.GET, consumes = "application/json", produces = "application/json;charset=UTF-8")
    public List getValue(@RequestBody int COM_ID) {
        return this.serviceFeign.getValue(COM_ID);
    }

}
