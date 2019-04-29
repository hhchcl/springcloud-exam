package com.deepexi.springcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.deepexi.springcloud.entity.Components;
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

    //private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "http://DEEPEXI-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/add.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    public JSONObject addComponent(@RequestBody Components components) {
        JSONObject json = new JSONObject();
        int count = restTemplate.postForObject(REST_URL_PREFIX + "/provider/getComponentsByID.do", components, int.class);
        if (count > 0) {
            json.put("count", count);
            json.put("errorMsg", "该组件名称已存在!");
        } else {
            json = restTemplate.postForObject(REST_URL_PREFIX + "/provider/add.do", components, JSONObject.class);
        }
        return json;
    }

    @RequestMapping(value = "/consumer/update.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    public JSONObject updComponent(@RequestBody Components components) {
        JSONObject json = new JSONObject();
        json = restTemplate.postForObject(REST_URL_PREFIX + "/provider/update.do", components, JSONObject.class);
        return json;
    }

    @RequestMapping(value = "/consumer/delete.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    public JSONObject delComponent(@RequestBody String[] multipleSelection) {
        JSONObject json = new JSONObject();
        json = restTemplate.postForObject(REST_URL_PREFIX + "/provider/delete.do", multipleSelection, JSONObject.class);
        return json;
    }

    @RequestMapping(value = "/consumer/queryList.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    public JSONObject queryList(@RequestBody Map<String, String> params) {
        JSONObject json = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(params, headers);
        json = restTemplate.postForObject(REST_URL_PREFIX + "/provider/queryList.do", request, JSONObject.class);
        return json;
    }

    // eureka服务发现
    @RequestMapping(value = "/consumer/discovery.do")
    public Object discovery() { //eureka服务发现
        return restTemplate.getForObject(REST_URL_PREFIX + "/provider/discovery.do", Object.class);
    }

    @RequestMapping(value = "/consumer/get.do", method = RequestMethod.GET)
    public List getValue(@RequestBody  int COM_ID) { //eureka服务发现
        return restTemplate.getForObject(REST_URL_PREFIX + "/provider/get.do", List.class, COM_ID);
    }


}
