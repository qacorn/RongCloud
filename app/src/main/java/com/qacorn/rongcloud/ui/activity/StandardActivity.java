package com.qacorn.rongcloud.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class StandardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        initializeData();
        registerClickListener();
    }

    protected abstract void initializeView();

    protected abstract void initializeData();

    protected abstract void registerClickListener();
}
