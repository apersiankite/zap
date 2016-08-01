package com.zoomcar.zap.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.zoomcar.zap.R;

/**
 * Created by Prateek on 01/08/16.
 */
public class DashboardActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }
}
