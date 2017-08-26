package com.mahendradevan.eathindhar.librov5;

import android.net.Uri;

/**
 * Created by Eathindhar on 7/19/2017.
 */

public class imagepickeradapter {
    String name;
    Uri uri;

    public String getName(){
        return name;
    }
    public void setName(String name){this.name=name;}
    public Uri getUri(){return uri;}
    public void setUri(Uri uri){
        this.uri=uri;
    }
}
