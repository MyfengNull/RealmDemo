package com.realmdemo.bean;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @PrimaryKey 用来标识主键
 * 默认的所有的字段都会被存储
 * 如果某个字段不需要被存储到本地，则需在在这个字段上面加上 @Ignore 注解
 * 创建的实体对象继承于RealmObject
 * Created by yangshirong on 2016/10/20 10:06.
 * 邮箱 ysr200808@163.com
 */

public class User extends RealmObject {
    @PrimaryKey
    private String name;
    private String id;
    private int age;
    private RealmList<User> friend;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public RealmList<User> getFriend() {
        return friend;
    }

    public void setFriend(RealmList<User> friend) {
        this.friend = friend;
    }
}
