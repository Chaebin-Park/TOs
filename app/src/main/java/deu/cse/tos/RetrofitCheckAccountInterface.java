package deu.cse.tos;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitCheckAccountInterface {
    @FormUrlEncoded
    @POST("auth/check")
    Call<CheckResult> postCheckUserResult(@FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("register/user")
    Call<CheckResult> postInsertUserResult(@FieldMap HashMap<String,Object> param);

}
