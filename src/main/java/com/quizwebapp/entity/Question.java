package com.quizwebapp.entity;

public class Question {
    private int categoryId;
    private String description;
    private boolean isActive;
    private int id;

    public Question() {}

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "categoryId=" + categoryId +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", id=" + id +
                '}';
    }
}
