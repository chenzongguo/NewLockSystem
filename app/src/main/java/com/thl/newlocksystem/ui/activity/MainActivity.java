package com.thl.newlocksystem.ui.activity;

import android.content.Intent;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.thl.newlocksystem.R;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;

public class MainActivity extends BaseActivity {


    @Override
    public void initView() {
        super.initView();
        StatusBarUtil.setTranslucent(this);
    }

    public void startActivity(View view){
//        Intent intent = new Intent(MainActivity.this, QRCodeScanActivity.class);
//        startActivityForResult(intent, 100);
    }

    @Override
    public void requestPermissionResult(boolean allowPermission) {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }
}
