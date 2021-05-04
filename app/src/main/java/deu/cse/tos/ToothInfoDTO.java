package deu.cse.tos;

import com.google.gson.annotations.SerializedName;

public class ToothInfoDTO {


    @SerializedName("lasttime")
    private String lasttime;

    @SerializedName("difftime")
    private String difftime;
    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함

    @SerializedName("morning_time")
    private String morning_time;

    @SerializedName("afternoon_time")
    private String afternoon_time;

    @SerializedName("dinner_time")
    private String dinner_time;

    @SerializedName("night_time")
    private String night_time;

    @SerializedName("morning_count")
    private int morning_count;

    @SerializedName("afternoon_count")
    private int afternoon_count;

    @SerializedName("dinner_count")
    private int dinner_count;

    @SerializedName("night_count")
    private int night_count;

    public int getMorning_count() {
        return morning_count;
    }

    public void setMorning_count(int morning_count) {
        this.morning_count = morning_count;
    }

    public int getAfternoon_count() {
        return afternoon_count;
    }

    public void setAfetnoon_count(int afetnoon_count) {
        this.afternoon_count = afetnoon_count;
    }

    public int getDinner_count() {
        return dinner_count;
    }

    public void setDinner_count(int dinner_count) {
        this.dinner_count = dinner_count;
    }

    public int getNight_count() {
        return night_count;
    }

    public void setNight_count(int night_count) {
        this.night_count = night_count;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public String getDifftime() {
        return difftime;
    }

    public void setDifftime(String difftime) {
        this.difftime = difftime;
    }

    public String getMorning_time() {
        return morning_time;
    }

    public void setMorning_time(String morning_time) {
        this.morning_time = morning_time;
    }

    public String getAfternoon_time() {
        return afternoon_time;
    }

    public void setAfternoon_time(String afternoon_time) {
        this.afternoon_time = afternoon_time;
    }

    public String getDinner_time() {
        return dinner_time;
    }

    public void setDinner_time(String dinner_time) {
        this.dinner_time = dinner_time;
    }

    public String getNight_time() {
        return night_time;
    }

    public void setNight_time(String night_time) {
        this.night_time = night_time;
    }

    @Override
    public String toString() {
        return "ToothInfoDTO{" +
                "lasttime='" + lasttime + '\'' +
                ", difftime='" + difftime + '\'' +
                ", morning_time='" + morning_time + '\'' +
                ", afternoon_time='" + afternoon_time + '\'' +
                ", dinner_time='" + dinner_time + '\'' +
                ", night_time='" + night_time + '\'' +
                ", morning_count='" + morning_count + '\'' +
                ", afternoon_count='" + afternoon_count + '\'' +
                ", dinner_count='" + dinner_count + '\'' +
                ", night_count='" + night_count + '\'' +
                '}';
    }
}
