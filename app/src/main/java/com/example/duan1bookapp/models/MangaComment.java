package com.example.duan1bookapp.models;

import java.util.Date;

public class MangaComment {
    private int id;
    private String body;
    private Customer user;
    private Date created_at;
    private Date updated_at;

    private int mangaid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public MangaComment() {
    }

    public MangaComment(int id, String body, Customer user, Date created_at, Date updated_at) {
        this.id = id;
        this.body = body;
        this.user = user;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public MangaComment(String body) {
        this.body = body;
    }

    public int getMangaid() {
        return mangaid;
    }

    public void setMangaid(int mangaid) {
        this.mangaid = mangaid;
    }

    public MangaComment(String body, int mangaid) {
        this.body = body;
        this.mangaid = mangaid;
    }
}

