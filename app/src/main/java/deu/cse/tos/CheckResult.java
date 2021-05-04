package deu.cse.tos;

import com.google.gson.annotations.SerializedName;

public class CheckResult {
    @SerializedName("result")
    private String result;

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CheckResult{" +
                "result='" + result + '\'' +
                '}';
    }
}
