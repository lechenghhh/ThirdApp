package com.lecheng.hello.thirdapp.Bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

/**
 * Created by Cheng on 2017/12/21.
 */

public class Bean068 extends BaseObservable {
    private String name;
    private String sex;
    private String address;
    private String id;
    private String height;
    private int age;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    private String birth;

    @Override
    public String toString() {
        return "Bean068{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", id='" + id + '\'' +
                ", height='" + height + '\'' +
                ", age=" + age +
                ", birth='" + birth + '\'' +
                '}';
    }
}
/*
public class Bean068 extends BaseObservable {
    private ObservableField<String> name;
    private ObservableField<String> sex;
    private ObservableField<String> address;
    private ObservableField<String> id;
    private ObservableField<Integer> age;

    @Bindable
    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }

    @Bindable
    public ObservableField<String> getSex() {
        return sex;
    }

    public void setSex(ObservableField<String> sex) {
        this.sex = sex;
    }

    @Bindable
    public ObservableField<String> getAddress() {
        return address;
    }

    public void setAddress(ObservableField<String> address) {
        this.address = address;
    }

    @Bindable
    public ObservableField<String> getId() {
        return id;
    }

    public void setId(ObservableField<String> id) {
        this.id = id;
    }

    @Bindable
    public ObservableField<Integer> getAge() {
        return age;
    }

    public void setAge(ObservableField<Integer> age) {
        this.age = age;
    }


}
*/