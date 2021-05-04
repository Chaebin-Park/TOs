package deu.cse.tos;

import com.google.gson.annotations.SerializedName;

import androidx.core.app.NotificationCompat;
public class UserDTO {


    @SerializedName("hash_key")
    private String hash_key;

    @SerializedName("created_at")
    private String created_at;
    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("status")
    private String status;

    @SerializedName("age")
    private String age;

    @SerializedName("gender")
    private String gender;

    @SerializedName("level")
    private String level;

    @SerializedName("exp")
    private String exp;

    @SerializedName("tooth_score")
    private String tooth_score;

    public String getHash_key() {
        return hash_key;
    }

    public void setHash_key(String hash_key) {
        this.hash_key = hash_key;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getTooth_score() {
        return tooth_score;
    }

    public void setTooth_score(String tooth_score) {
        this.tooth_score = tooth_score;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "hash_key='" + hash_key + '\'' +
                ", created_at='" + created_at + '\'' +
                ", nickname='" + nickname + '\'' +
                ", status='" + status + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", level='" + level + '\'' +
                ", exp='" + exp + '\'' +
                ", tooth_score='" + tooth_score + '\'' +
                '}';
    }
}
