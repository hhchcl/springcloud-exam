package com.deepexi.springcloud.service;

import com.deepexi.springcloud.entity.Components;
import com.deepexi.springcloud.mapper.ComponentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentsService {

    @Autowired
    private ComponentsMapper componentsMapper;

    public int getComponentsById(String COM_NAME) {
        return componentsMapper.getComponentsById(COM_NAME);
    }

    public boolean addComponent(Components components) {
        int number = componentsMapper.addComponent(components);
        if (number > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updCompoment(Components components) {
        int number = componentsMapper.updCompoment(components);
        if (number > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delCompoment(String[] array) {
        int number = componentsMapper.delCompoment(array);
        if (number > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getAllCount() {
        return componentsMapper.getAllCount();
    }

    public int getAllCountCondition(String name, String type, String status) {
        return componentsMapper.getAllCountCondition(name, type, status);
    }

    public List queryList(int currPage, int showNum) {
        return componentsMapper.queryList(currPage, showNum);
    }

    public List queryListByCondition(int currPage, int showNum, String name, String type, String status) {
        return componentsMapper.queryListByCondition(currPage, showNum, name, type, status);
    }

    public List queryById(int COM_ID) {
        return componentsMapper.queryById(COM_ID);
    }

}
