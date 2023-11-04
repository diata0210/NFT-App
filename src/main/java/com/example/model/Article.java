package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class Article {
    private String title;
    private String desc;
    private String author;
    private List<String> relatedTags;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy 'at' h:mm a 'UTC'", timezone = "UTC")
    private Date date;

    // Getters and setters for each property
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getRelatedTags() {
        return relatedTags;
    }

    public void setRelatedTags(List<String> relatedTags) {
        this.relatedTags = relatedTags;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", relatedTags=" + relatedTags +
                '}';
    }
}
