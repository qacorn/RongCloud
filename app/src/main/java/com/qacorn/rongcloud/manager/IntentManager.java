package com.qacorn.rongcloud.manager;

import android.content.Context;
import android.content.Intent;

import com.qacorn.rongcloud.ui.activity.RegisterActivity;

public class IntentManager {

    public static void goRegister(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
}
