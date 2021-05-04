package deu.cse.tos;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitCalendarService {

    @FormUrlEncoded
    @POST("calendar/info")
    Call<ToothInfoDTO> postCalendarResult(@FieldMap HashMap<String, Object> param);
}