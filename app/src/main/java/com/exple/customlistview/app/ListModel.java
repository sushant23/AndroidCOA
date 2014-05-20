package com.exple.customlistview.app;

/**
 * Created by sushant on 5/16/14.
 */
public class ListModel {

    private String title = "";
    private String description = "";
    private String image = "";

    public int getId() {
        return id;
    }

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
