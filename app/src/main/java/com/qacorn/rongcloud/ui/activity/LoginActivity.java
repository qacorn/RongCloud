package com.qacorn.rongcloud.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.qacorn.rongcloud.R;
import com.qacorn.rongcloud.manager.IntentManager;
import com.qacorn.rongcloud.ui.widget.ClearWriteEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends StandardActivity implements View.OnClickListener {

    private ClearWriteEditText de_login_phone,de_login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initializeView() {
        setContentView(R.layout.activity_login);
        de_login_phone = findViewById(R.id.de_login_phone);
        de_login_password = findViewById(R.id.de_login_password);
    }

    @Override
    protected void initializeData() {

    }

    @Override
    protected void registerClickListener() {
        findViewById(R.id.de_login_sign).setOnClickListener(this);
        findViewById(R.id.de_login_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.de_login_sign:
                String  userPhone = de_login_phone.getText().toString();
                String password = de_login_password.getText().toString();

                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("userPhone",userPhone)
                        .add("password",password)
                        .build();
                Request request = new Request.Builder()
                        .url("http://192.168.253.1:8080/login.json")
                        .post(requestBody)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
//                        {"status":0,"message":"success","timestamp":"2019-05-06 14:31:31","result":"TUinPjxE87AEg2ksTrSA8UuzI8scO5Er2JrVGJ0f/peMFATwMKgVMKtHEXEbhE6+VZoKAzr5AvLj0VaBuDU5gcLEjizs3oEVhlhU7VmnmXQ="}
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            String status = jsonObject.optString("status");
                            String message = jsonObject.optString("message");
                            String timestamp = jsonObject.optString("timestamp");
                            String result = jsonObject.optString("result");



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                break;
            case R.id.de_login_register:
                IntentManager.goRegister(this);
                break;
        }
    }
}
