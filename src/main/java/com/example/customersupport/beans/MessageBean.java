package com.example.customersupport.beans;

import java.io.Serializable;

public class MessageBean implements Serializable {

    private Integer id;
    private String content;
    private boolean read;
    private String userMail;

    public MessageBean() {
    }

    public MessageBean(Integer id, String content, boolean read, String userMail) {
        this.id = id;
        this.content = content;
        this.read = read;
        this.userMail = userMail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", read=" + read +
                ", userMail='" + userMail + '\'' +
                '}';
    }
}