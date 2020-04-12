package com.example.demo.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "User") // 指定关联的数据库的表名
public class User { 
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "Id")
	private String id; // 主键ID
 
	@Column(name = "Name")
	private String name; // 姓名
 
	@Column(name = "Age")
    private Integer age; // 年龄
	
	@Column(name = "Createtime")
    private Date createtime; //创建时间
 
	public String getId() {
		return id;
	}
 
	public void setId(String id) {
		this.id = id;
	}
 
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

