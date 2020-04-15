package com.coderslab.databaseModel;

public class UsersGroups {
    private int id;
    private String name;

    public UsersGroups() {
    }

    public UsersGroups(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "UsersGroups{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
