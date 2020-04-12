package com.example.demo.dto;

import javax.persistence.Column;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDto extends BaseDto {

    private String name; // 姓名

    private Integer age; // 年龄

    private Date createtime; //创建时间


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  format.format(createtime.getTime());
    }

    public void setCreateTime(Date createtime) {
        this.createtime = createtime;
    }

}
