package com.coderslab.databaseModel;

import java.sql.Date;

public class Solution {
    private int id;
    private Date created;
    private Date update;
    private String description;
    private int exercise_id;
    private int user_id;

    public Solution() {
    }

    public Solution(String description, int exercise_id, int user_id) {
        this.description = description;
        this.exercise_id = exercise_id;
        this.user_id = user_id;
    }

    public Solution(int exercise_id, int user_id) {
        this.exercise_id = exercise_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "id=" + id +
                " created = " + created +
                " update = " + update +
                " description = " + description +
                " exercise_id = " + exercise_id +
                " user_id = " + user_id;
    }
}
