package com.thl.newlocksystem.ui.activity;

import android.Manifest;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;
import com.thl.newlocksystem.R;
import com.thl.newlocksystem.R2;
import com.thl.newlocksystem.api.ApiRetrofit;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
//        89CA7BFA98871245FF2B80F3167FB912
//        ApiRetrofit.getInstance().getToken("E4C885607F307C27D77E891AB2EB6F4C")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(baseResponse -> {
//                    String code = baseResponse.getCode();
//                    if("000".equals(code)){
////                        showUpdateDialog(checkUpdateResponse.getData().getDownload_address());
////                        registerReceiver();
//                    }else{
////                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
//                    }
//                });

//        ApiRetrofit.getInstance().checkCaptcha("15094307279","733631")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(baseResponse -> {
//                    String code = baseResponse.getCode();
//                    if("000".equals(code)){
////                        showUpdateDialog(checkUpdateResponse.getData().getDownload_address());
////                        registerReceiver();
//                    }else{
////                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
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
