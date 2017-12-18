package org.haozf.member.model;

import java.io.Serializable;

public class Member implements Serializable{
    
    private static final long serialVersionUID = -398249604523488656L;
    
    private String id;
    private String name;
    private String password;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
