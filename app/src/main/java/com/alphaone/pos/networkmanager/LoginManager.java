package com.alphaone.pos.networkmanager;


import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.alphaone.pos.BuildConfig;
import com.alphaone.pos.R;
import com.alphaone.pos.commonutilities.CommonUtils;
import com.alphaone.pos.modelclass.Data;
import com.alphaone.pos.remote.RetrofitClient;
import com.alphaone.pos.remote.RetrofitInterfaces;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class LoginManager implements retrofit2.Callback<ResponseBody> {
    private Context mContext;
    private StatusListener listener;
    private Dialog progressDialog;

    public LoginManager(Context mContext, StatusListener listener) {
        this.mContext = mContext;
        this.listener = listener;
        this.progressDialog = CommonUtils.Companion.showLoadingDialog(mContext, mContext.getResources().getString(R.string.loading));

    }

    public interface StatusListener {
        // void onLoginSuccess(ServiceResponse serviceResponse);
        void onLoginSuccess(String status, String message, Data data);

        void onLoginFailure(String status, String errorMessage);
    }

    public void doLogin(String loginWithOtp, String mobile_number, String password) {
        showLoader();
        //String authorization= App.getCurrentUser(mContext).getLogApiKey().trim();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("loginWithOtp", loginWithOtp);
        jsonObject.addProperty("mobile_number", mobile_number);
        jsonObject.addProperty("password", password);
        String rawJson = CommonUtils.Companion.encodeString(jsonObject.toString());

        //Temp object, delete it once API gets corrected
        JsonObject jsonObjectTemp = new JsonObject();
        jsonObjectTemp.addProperty("loginWithOtp", loginWithOtp);
        jsonObjectTemp.addProperty("mobile_number", mobile_number);
        jsonObjectTemp.addProperty("password", password);
        jsonObjectTemp.addProperty("secret", rawJson);
        RetrofitClient
                .getClient(BuildConfig.SERVER_URL)
                .create(RetrofitInterfaces.DoLogin.class)
                // .post(CommonUtils.Companion.sendFromSecret(rawJson))
                .post(jsonObjectTemp)
                .enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        String status = null;
        String message = null;
        try {
            hideLoader();
            JSONObject jsonObject = new JSONObject(response.body().string());
            Data data;
             status = jsonObject.getString("status");
             message = jsonObject.getString("message");
            if (status.contentEquals("true")) {
                data = new Gson().fromJson(CommonUtils.Companion.decodeString(jsonObject.getString("data")) , Data.class);

                listener.onLoginSuccess(status, message, data);

            } else {
                listener.onLoginFailure(status, message);
            }
        } catch (Exception e) {
            listener.onLoginFailure(status, mContext.getResources().getString(R.string.something_went_wrong));
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        listener.onLoginFailure("false", mContext.getResources().getString(R.string.some_technical_issue));
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
