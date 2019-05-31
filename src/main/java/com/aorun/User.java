package com.aorun;

import java.io.Serializable;

/**
 * 类描述:
 * Created by duxihu on 2019/5/11 0011.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
