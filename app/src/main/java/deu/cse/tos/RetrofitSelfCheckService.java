package deu.cse.tos;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitSelfCheckService {

    @FormUrlEncoded
    @POST("tooth/insert_calendar")
    Call<CheckResult> postSelfUserResult(@FieldMap HashMap<String, Object> param);

}
