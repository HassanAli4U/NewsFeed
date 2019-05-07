package com.hassan.ali.newsfeed;

import java.io.Serializable;

public class DataModel implements Serializable {
    String imageUrl1,imageUrl4;
    String title;
    String subsection;
    String date;
    String abstractt;
    String byLine;
    String link;

    public DataModel(String imageUrl1, String imageUrl4, String title, String subsection, String date, String abstractt, String byLine, String link) {
        this.imageUrl1 = imageUrl1;
        this.imageUrl4 = imageUrl4;
        this.title = title;
        this.subsection = subsection;
        this.date = date;
        this.abstractt = abstractt;
        this.byLine = byLine;
        this.link = link;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public String getImageUrl4() {
        return imageUrl4;
    }

    public void setImageUrl4(String imageUrl4) {
        this.imageUrl4 = imageUrl4;
    }

    public String getAbstractt() {
        return abstractt;
    }

    public void setAbstractt(String abstractt) {
        this.abstractt = abstractt;
    }

    public String getByLine() {
        return byLine;
    }

    public void setByLine(String byLine) {
        this.byLine = byLine;
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

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        date = date;
    }
}
