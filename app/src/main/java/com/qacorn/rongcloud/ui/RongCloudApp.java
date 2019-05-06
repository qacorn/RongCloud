package com.qacorn.rongcloud.ui;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.TextMessage;


public class RongCloudApp extends Application {

//    String token_12345678 = "BbDi8Pvgq1NAwRve+EJnKuTW3pt76Nznh8Fw1T8PO5SQ4Ktf5+FR7wIWzlJEcBTH1t4HoyZxyQ85nNcCoQNB26HgYqKlVwbR";
    String token_12345679 = "B0w5F0wdq0jhCY2g3s9X3eTW3pt76Nznh8Fw1T8PO5SQ4Ktf5+FR78JewtKROZdU96yVNJPap/6bcCCb2q0wdKHgYqKlVwbR";

    @Override
    public void onCreate() {
        super.onCreate();
//        OkGoManager.init(this);
        RongIM.init(this);

//        connect(token_12345678);
        connect(token_12345679);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    private void connect(String token) {
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(String s) {
                    //连接融云成功
                    Log.e("onSuccess", s);


                    Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
                    String targetId = "12345679";
                    MessageContent textMessage = TextMessage.obtain("中午吃什么");
                    RongIMClient.getInstance().sendMessage(conversationType, targetId, textMessage, null, null, new IRongCallback.ISendMessageCallback() {
                        @Override
                        public void onAttached(Message message) {

                        }

                        @Override
                        public void onSuccess(Message message) {
                            Log.e("Message", message.getContent().toString());
                        }

                        @Override
                        public void onError(Message message, RongIMClient.ErrorCode errorCode) {

                        }
                    });
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }


    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
