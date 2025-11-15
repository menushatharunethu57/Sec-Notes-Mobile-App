package com.example.secnotes.recycleview;

public class Message {


    private String topic;
    private String content;
    private int delete;
    private int update;

    public Message(String topic, String content,int delete,int update) {
        this.topic = topic;
        this.content = content;
        this.delete = delete;
        this.update = update;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }
}
