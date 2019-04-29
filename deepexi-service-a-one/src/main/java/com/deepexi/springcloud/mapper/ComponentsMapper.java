package com.deepexi.springcloud.mapper;

import com.deepexi.springcloud.entity.Components;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ComponentsMapper {

    public int getComponentsById(String COM_NAME);           //新增时判断是否重复

    public int addComponent(Components components); // 新增组件

    public int updCompoment(Components components); // 修改组件

    public int delCompoment(String[] array);            // 删除组件

    public int getAllCount();                           // 获取组件总记录数

    public int getAllCountCondition(@Param("COM_NAME") String name, @Param("COM_TYPE") String type, @Param("COM_STATUS") String status); // 根据条件获取记录数

    public List queryList(int currPage, int showNum);   // 实现分页效果

    public List queryListByCondition(@Param("currPage") int currPage, @Param("showNum") int showNum, @Param("COM_NAME") String name, @Param("COM_TYPE") String type, @Param("COM_STATUS") String status); // 按条件分页

    public List queryById(int COM_ID);                  // 根据条件获取一条记录

}
