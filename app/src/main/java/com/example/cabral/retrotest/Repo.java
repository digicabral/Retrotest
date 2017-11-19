package com.example.cabral.retrotest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Millena on 19/11/2017.
 */

public class Repo {
    @SerializedName("id")
    int mId;
    @SerializedName("name")
    String mName;
    public Repo(int id, String name ) {
        this.mId = id;
        this.mName = name;
    }
    public int getmId() {
        return mId;
    }
    public void setmId(int mId) {
        this.mId = mId;
    }
    public String getmName() {
        return mName;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }
}
