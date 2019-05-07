package com.qacorn.rongcloud.network;

/**
 * Created by gsy on 2017/1/22.
 */

public class OkHttpException extends Exception {


    private int ecode;
    private String emsg;

    public OkHttpException(int ecode,String emsg){
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public int getEcode() {
        return ecode;
    }

    public String getEmsg() {
        return emsg;
    }

}
