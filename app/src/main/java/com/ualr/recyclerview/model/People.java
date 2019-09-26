package com.ualr.recyclerview.model;

import android.graphics.drawable.Drawable;

/**
 * Created by irconde on 2019-09-25.
 */
// TODO 02: Modify People class. Extend from Item abstract class and set section value and set section value
public class People {
    private int image;
    private String name;
    private Drawable imageDrw;
    private String email;

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Drawable getImageDrw() {
        return imageDrw;
    }

    public void setImageDrw(Drawable imageDrw) {
        this.imageDrw = imageDrw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public People() {
    }

    public People(String name) {
        this.name = name;
    }
}
