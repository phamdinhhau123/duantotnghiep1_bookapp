package com.example.duan1bookapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImageData implements Serializable {
    private int id;
    private String name;
    private String bookName;
    private String muc;
    private String type;
    private String url;
    private int chapterID;
    private int linkID;

    @SerializedName("file-name")
    private byte[] imageData;

    public ImageData(byte[] imageData) {
        this.imageData = imageData;
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getMuc() {
        return muc;
    }

    public void setMuc(String muc) {
        this.muc = muc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getChapterID() {
        return chapterID;
    }

    public void setChapterID(int chapterID) {
        this.chapterID = chapterID;
    }

    public int getLinkID() {
        return linkID;
    }

    public void setLinkID(int linkID) {
        this.linkID = linkID;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
