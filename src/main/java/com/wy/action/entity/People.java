package com.wy.action.entity;

import java.util.Map;

/**
 * @Author wangyong
 * @Date 2019-08-03
 */
public class People {
    private long id;
    private String name;
    private int age;
    private Map<String, Object> values;

    public People(long id) {
        this.id = id;
    }

    public People(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", values=" + values +
                '}';
    }
}
