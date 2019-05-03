package com.EftimieVlad.Assignment.entity;


import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity

public class RSS {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String title;

    @Lob
    private String description;

    private Date published_date;

    private Date created_date;

    @Lob
    private byte[] image;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublished_date() {
        return published_date;
    }

    public void setPublished_date(Date published_date) {
        this.published_date = published_date;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    public RSS() {}
    public RSS(String title, String description, Date published_date, byte[] image) {
        this.title = title;
        this.description = description;
        this.published_date = published_date;
        this.image = image;
        this.created_date = new Date();
    }
    public static boolean isUniquie(List<RSS> rss, String title, Date published_date) {

        for (RSS temp : rss) {
            if (title.equals(temp.getTitle()) && published_date.equals(temp.getPublished_date())) {
               return false;
            }
        }
        return true;

    }

    @Override
    public String toString() {
        return new StringBuilder().append("Feed [id: ").append(id)
                .append(", title: ").append(title)
                .append(", description: ").append(description)
                .append(", publication date: ").append(published_date).toString();

    }
}
