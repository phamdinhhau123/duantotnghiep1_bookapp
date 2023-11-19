package com.example.duan1bookapp.models;

import java.io.Serializable;

public class Chapter implements Serializable {

    public int id;
    public int mangaid;
    public String name;
    public int bag;

    public Chapter() {
    }

    public Chapter(int id, int mangaid, String name) {
        this.id = id;
        this.mangaid = mangaid;
        this.name = name;
    }

    public Chapter(int id, int mangaid, String name, int bag) {
        this.id = id;
        this.mangaid = mangaid;
        this.name = name;
        this.bag = bag;
    }
}
