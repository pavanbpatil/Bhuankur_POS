package com.alphaone.pos.remote;

import com.alphaone.pos.constants.Constant;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by pavan on 08/02/2019.
 */

public class RetrofitInterfaces {

    public interface DoLogin {
        @POST(Constant.ServerEndpoint.DO_LOGIN)
        Call<ResponseBody> post(@Body JsonObject jsonObject);
    }
    public interface FetchDashboardData {
        @POST(Constant.ServerEndpoint.FETCH_DASHBOARD_DATA)
        Call<ResponseBody> post(@Body JsonObject jsonObject);
    }

}



