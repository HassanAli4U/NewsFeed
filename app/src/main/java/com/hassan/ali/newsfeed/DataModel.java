package com.hassan.ali.newsfeed;

import java.io.Serializable;

public class DataModel implements Serializable {
    String imageUrl1;
    String title;
    String date;
    String abstractt;
    String link;
    String author;

    public DataModel(String imageUrl1, String title, String date, String abstractt, String link, String author) {
        this.imageUrl1 = imageUrl1;
        this.title = title;
        this.date = date;
        this.abstractt = abstractt;
        this.link = link;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }



    public String getAbstractt() {
        return abstractt;
    }

    public void setAbstractt(String abstractt) {
        this.abstractt = abstractt;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        date = date;
    }
}
