package com.mahendradevan.eathindhar.librov5;

/**
 * Created by Eathindhar on 27-08-2017.
 */

public class Books {
    private String bktit,bkauth, bkdesc;
    private int image;

    public Books(String bktit, String bkauth, String bkdesc, int image) {
        this.bktit = bktit;
        this.bkauth = bkauth;
        this.bkdesc = bkdesc;
        this.image = image;
    }

    public String getBktit() {
        return bktit;
    }

    public void setBktit(String bktit) {
        this.bktit = bktit;
    }

    public String getBkauth() {
        return bkauth;
    }

    public void setBkauth(String bkauth) {
        this.bkauth = bkauth;
    }

    public String getBkdesc() {
        return bkdesc;
    }

    public void setBkdesc(String bkdesc) {
        this.bkdesc = bkdesc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
