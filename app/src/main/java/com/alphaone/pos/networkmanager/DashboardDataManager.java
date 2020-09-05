package com.alphaone.pos.networkmanager;


import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.alphaone.pos.BuildConfig;
import com.alphaone.pos.R;
import com.alphaone.pos.commonutilities.CommonUtils;
import com.alphaone.pos.modelclass.ServiceResponse;
import com.alphaone.pos.remote.RetrofitClient;
import com.alphaone.pos.remote.RetrofitInterfaces;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class DashboardDataManager implements retrofit2.Callback<ResponseBody> {
    private Context mContext;
    private StatusListener listener;
    private Dialog progressDialog;

    public DashboardDataManager(Context mContext, StatusListener listener) {
        this.mContext = mContext;
        this.listener = listener;
        this.progressDialog = CommonUtils.Companion.showLoadingDialog(mContext, mContext.getResources().getString(R.string.loading));

    }

    public interface StatusListener {
        // void onLoginSuccess(ServiceResponse serviceResponse);
        void onDataFetchSuccess(ServiceResponse serviceResponse);

        void onDataFetchFailure(String errorMessage);
    }

    public void fetchDashboardData(String loginWithOtp, String username, String password) {
        showLoader();
        //String authorization= App.getCurrentUser(mContext).getLogApiKey().trim();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("loginWithOtp", loginWithOtp);
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);
        String rawJson = CommonUtils.Companion.encodeString(jsonObject.toString());

        RetrofitClient
                .getClient(BuildConfig.SERVER_URL)
                .create(RetrofitInterfaces.FetchDashboardData.class)
                .post(CommonUtils.Companion.sendFromSecret(rawJson))
                .enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
            hideLoader();
            JSONObject jsonObject = new JSONObject(response.body().string());
            ServiceResponse serviceResponse;
            String status = jsonObject.getString("status");
            String message = jsonObject.getString("message");
            if (status.contentEquals("false")) {
                serviceResponse = new Gson().fromJson(jsonObject.toString(), ServiceResponse.class);

                listener.onDataFetchSuccess(serviceResponse);

            } else {
                listener.onDataFetchFailure(message);
            }
        } catch (Exception e) {
            listener.onDataFetchFailure(mContext.getResources().getString(R.string.something_went_wrong));
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        listener.onDataFetchFailure(mContext.getResources().getString(R.string.some_technical_issue));
    }

    private void showLoader() {
        if (progressDialog == null || progressDialog.isShowing()) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                progressDialog.show();
            }
        });
    }

    private void hideLoader() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        });
    }
}
