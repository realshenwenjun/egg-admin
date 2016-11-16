package com.dskj.user.entity;

import java.io.Serializable;

public class RegionEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -977353671766464287L;
    private int id;
    private String name;
    private String location;
    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

}
