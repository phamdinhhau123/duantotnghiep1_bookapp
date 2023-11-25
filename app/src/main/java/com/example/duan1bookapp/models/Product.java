package com.example.duan1bookapp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
public class Product implements Serializable {

    public int id;
    public String productName;
    public String employeeName;
    public String productAuthor;
    public String productShortDescription;
    public String productImageName;
    public int soTap;
    public Date updatedAt;
    public ArrayList<Chapter> chapter;
    public ArrayList<Category> categories;
    public ArrayList<MangaComment> mComment;

    public Product(int id, String productName, String productImageName) {
        this.id = id;
        this.productName = productName;
        this.productImageName = productImageName;
    }

    public Product() {
    }

    public Product(int id, String productName, String employeeName, String productAuthor, String productShortDescription, String productImageName, int soTap, Date updatedAt, ArrayList<Chapter> chapter, ArrayList<Category> categories) {
        this.id = id;
        this.productName = productName;
        this.employeeName = employeeName;
        this.productAuthor = productAuthor;
        this.productShortDescription = productShortDescription;
        this.productImageName = productImageName;
        this.soTap = soTap;
        this.updatedAt = updatedAt;
        this.chapter = chapter;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getProductAuthor() {
        return productAuthor;
    }

    public void setProductAuthor(String productAuthor) {
        this.productAuthor = productAuthor;
    }

    public String getProductShortDescription() {
        return productShortDescription;
    }

    public void setProductShortDescription(String productShortDescription) {
        this.productShortDescription = productShortDescription;
    }

    public String getProductImageName() {
        return productImageName;
    }

    public void setProductImageName(String productImageName) {
        this.productImageName = productImageName;
    }

    public int getSoTap() {
        return soTap;
    }

    public void setSoTap(int soTap) {
        this.soTap = soTap;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ArrayList<Chapter> getChapter() {
        return chapter;
    }

    public ArrayList<MangaComment> getMangaComment() {
        return null ;
    }

    public void setChapter(ArrayList<Chapter> chapter) {
        this.chapter = chapter;
    }

    public Product(int id, String productName, String employeeName, String productAuthor, String productShortDescription, String productImageName, int soTap, Date updatedAt, ArrayList<Category> categories) {
        this.id = id;
        this.productName = productName;
        this.employeeName = employeeName;
        this.productAuthor = productAuthor;
        this.productShortDescription = productShortDescription;
        this.productImageName = productImageName;
        this.soTap = soTap;
        this.updatedAt = updatedAt;
        this.categories = categories;
    }
    public Product(int id, String productName, String employeeName, String productAuthor, String productShortDescription, String productImageName, int soTap, Date updatedAt, ArrayList<Chapter> chapter, ArrayList<Category> categories, ArrayList<MangaComment> mComment) {
        this.id = id;
        this.productName = productName;
        this.employeeName = employeeName;
        this.productAuthor = productAuthor;
        this.productShortDescription = productShortDescription;
        this.productImageName = productImageName;
        this.soTap = soTap;
        this.updatedAt = updatedAt;
        this.chapter = chapter;
        this.categories = categories;
        this.mComment = null;
    }
}
