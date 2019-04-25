package com.thl.newlocksystem.ui.activity;

import android.Manifest;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;
import com.thl.newlocksystem.R;
import com.thl.newlocksystem.R2;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;

import butterknife.BindView;

/**
 * @创建者 CSDN_LQR
 * @描述 微信闪屏页
 */
public class SplashActivity extends BaseActivity {

    @BindView(R2.id.rlButton)
    RelativeLayout mRlButton;
    @BindView(R2.id.btnLogin)
    Button mBtnLogin;
    @BindView(R2.id.btnRegister)
    Button mBtnRegister;
    protected String[] downloadApkPermission = {
            //电话
            Manifest.permission.READ_PHONE_STATE,
            //相机
            Manifest.permission.CAMERA,
            //存储空间
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    public void requestPermissionResult(boolean allowPermission) {
        if (allowPermission) {
        }
    }

    @Override
    public void init() {
        mayRequestPermission(downloadApkPermission);
    }

    @Override
    public void initView() {
        StatusBarUtil.setTranslucent(this,0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1000);
        mRlButton.startAnimation(alphaAnimation);
    }

    @Override
    public void initListener() {
        mBtnLogin.setOnClickListener(v -> {
            jumpToActivity(LoginActivity.class);
            finish();
        });
        mBtnRegister.setOnClickListener(v -> {
//            jumpToActivity(RegisterActivity.class);
            finish();
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_splash;
    }
}
