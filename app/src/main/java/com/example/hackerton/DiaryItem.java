package com.example.hackerton;

public class DiaryItem {
    int date;
    String title;
    int category;
    String imageUrl;
    String content;

    public DiaryItem(int date, String title, int category, String imageUrl, String content) {
        this.date = date;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
