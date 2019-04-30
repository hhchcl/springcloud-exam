package com.deepexi.springcloud.controller;

import com.alibaba.fastjson.JSONPObject;
import com.deepexi.springcloud.entity.Components;
import com.deepexi.springcloud.service.ComponentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

@RestController
public class ComponentsController {

    @Autowired
    private ComponentsService componentsService;
    @Autowired
    private DiscoveryClient client; // 服务发现

    @RequestMapping(value = "/provider/getComponentsByID.do")
    public int getComponentsById(@RequestBody Components components) {
        int count = componentsService.getComponentsById(components.getCOM_NAME());
        return count;
    }

    @RequestMapping(value = "/provider/add.do", consumes = "application/json", produces = "application/json;charset=UTF-8")
    public JSONObject addComponent(@RequestBody Components components) {
        JSONObject json = new JSONObject();
        boolean flag = componentsService.addComponent(components);
        if (flag) {
            json.put("code", "200");
            json.put("errorMsg", "新增成功!");
        } else {
            json.put("code", "500");
            json.put("errorMsg", "新增失败!");
        }
        return json;
    }

    @RequestMapping(value = "/provider/update.do", consumes = "application/json", produces = "application/json;charset=UTF-8")
    public JSONObject updCompoment(@RequestBody Components components) {
        JSONObject json = new JSONObject();
        boolean flag = componentsService.updCompoment(components);
        if (flag) {
            json.put("code", "200");
            json.put("errorMsg", "修改成功!");
        } else {
            json.put("code", "500");
            json.put("errorMsg", "修改失败!");
        }
        return json;
    }

    @RequestMapping(value = "/provider/delete.do", consumes = "application/json", produces = "application/json;charset=UTF-8")
    public JSONObject delCompoment(@RequestBody String[] array) {
        JSONObject json = new JSONObject();
        boolean flag = componentsService.delCompoment(array);
        if (flag) {
            json.put("code", "200");
            json.put("errorMsg", "删除成功!");
        } else {
            json.put("code", "500");
            json.put("errorMsg", "删除失败!");
        }
        return json;
    }

    @RequestMapping(value = "/provider/queryList.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    public JSONObject queryList(@RequestBody Map<String, Object> params) {
        JSONObject json = new JSONObject();
        int currPage = Integer.valueOf(params.get("currentPage").toString());
        int showNum = Integer.valueOf(params.get("turnPageShowNum").toString());
        String name = params.get("COM_NAME").toString();
        String type = params.get("COM_TYPE").toString();
        String status = params.get("COM_STATUS").toString();
        List list = new ArrayList();
        if ((null == name || "".equals(name)) && (null == type || "".equals(type)) && (null == status || "".equals(status))) {
            list = componentsService.queryList((currPage - 1) * showNum, showNum);
            if (list.size() > 0) {
                json.put("queryList", list);
                json.put("code", "200");
                int count = componentsService.getAllCount();
                json.put("turnPageTotalNum", count);
            } else {
                json.put("turnPageTotalNum", 0);
                json.put("code", "500");
                json.put("errorMsg", "没有相关数据");
            }
        } else {
            System.out.println("name：" + name + "type：" + type + "status：" + status);
            list = componentsService.queryListByCondition((currPage - 1) * showNum, showNum, name, type, status);
            if (list.size() > 0) {
                json.put("queryList", list);
                json.put("code", "200");
                int count = componentsService.getAllCountCondition(name, type, status);
                json.put("turnPageTotalNum", count);
            } else {
                json.put("turnPageTotalNum", 0);
                json.put("code", "500");
                json.put("errorMsg", "没有相关数据");
            }
        }
        return json;
    }

    @RequestMapping(value = "/provider/discovery.do", method = RequestMethod.GET)
    public Object discovery() { //eureka服务发现
        List<String> list = client.getServices();
        System.out.println("*******" + list);

        List<ServiceInstance> serviceList = client.getInstances("DEEPEXI-SERVICE");
        for (ServiceInstance element: serviceList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
        }
        return this.client;
    }

    @RequestMapping(value = "/provider/get.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    public JSONObject getValue(@RequestBody Components components) {
        JSONObject json = new JSONObject();
        List list = new ArrayList();
        list = componentsService.queryById(Integer.valueOf(components.getCOM_ID()));
        json.put("list", list);
        return json;
    }

    @RequestMapping(value = "/provider/queryById.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
    public List queryById(int COM_ID) {
        return componentsService.queryById(COM_ID);
    }


}
