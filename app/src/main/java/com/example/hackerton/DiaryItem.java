package com.example.hackerton;

public class DiaryItem {

    int date;
    String tittle;
    int category;
    String imageUrl;
    String content;

    public DiaryItem(int date, String tittle, int category, String imageUrl, String content) {
        this.date = date;
        this.tittle = tittle;
        this.category = category;
        this.imageUrl = imageUrl;
        this.content = content;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
