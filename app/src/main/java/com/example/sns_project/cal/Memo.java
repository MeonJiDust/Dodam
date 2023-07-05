package com.example.sns_project.cal;

import java.io.Serializable;

public class Memo implements Serializable {

    String idx ="";
    String dates = "";//날짜
    String mission = "";//미션 내용

    public Memo(String idx, String dates, String mission) {
        super();
        this.idx = idx;
        this.dates = dates;
        this.mission = mission;
    }


    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

}
