package com.mahendradevan.eathindhar.librov5;

/**
 * Created by Eathindhar on 27-08-2017.
 */

public class Posts {
    private String psttit,pstauth, pstdesc;
    private int image;

    public Posts(String psttit, String pstauth, String pstdesc, int image) {
        this.psttit = psttit;
        this.pstauth = pstauth;
        this.pstdesc = pstdesc;
        this.image = image;
    }

    public String getPsttit() {
        return psttit;
    }

    public void setPsttit(String psttit) {
        this.psttit = psttit;
    }

    public String getPstauth() {
        return pstauth;
    }

    public void setPstauth(String pstauth) {
        this.pstauth = pstauth;
    }

    public String getPstdesc() {
        return pstdesc;
    }

    public void setPstdesc(String pstdesc) {
        this.pstdesc = pstdesc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
