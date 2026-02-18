package com.example.lifeos.learnUnitTests;

import java.util.UUID;

public class TodoSimple {

    private final String id;
    private String title;
    private boolean completed;

    public TodoSimple(String title) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.completed = false;
    }

    // Getter und Setter
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}

