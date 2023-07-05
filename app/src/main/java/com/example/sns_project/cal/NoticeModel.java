package com.example.sns_project.cal;

import java.io.Serializable;

public class NoticeModel implements Serializable {



    String idx="";
    String img = "";
    String memo = "";
    String title = "";

    public NoticeModel(String idx, String img, String memo, String title) {
        super();
        this.idx = idx;
        this.img = img;
        this.memo = memo;
        this.title = title;
    }


    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img)  {
        this.img = img;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}


