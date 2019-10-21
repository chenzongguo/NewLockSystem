package cn.njthl.HotelClean.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.R2;
import cn.njthl.HotelClean.api.ApiRetrofit;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.base.BasePresenter;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @创建者 CSDN_LQR
 * @描述 微信闪屏页
 */
public class SplashActivity extends BaseActivity {

    private  boolean userTokenIsOk = false;

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
            onPermissionSuccess();
        }
    }

    @Override
    public void init() {
//                ApiRetrofit.getInstance().checkUserToken()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(baseResponse -> {
//                    String code = baseResponse.getCode();
//                    if("000".equals(code)){
//                        userTokenIsOk = true;
////                        showUpdateDialog(checkUpdateResponse.getData().getDownload_address());
////                        registerReceiver();
//                    }else{
//                        userTokenIsOk = false;
////                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
//                    }
//                });
        if(mayRequestPermission(downloadApkPermission)){
            onPermissionSuccess();
        }


    }

    @Override
    public void initView() {
        StatusBarUtil.setTranslucent(this,0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1000);
        mRlButton.startAnimation(alphaAnimation);
    }

    public void onPermissionSuccess() {
        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setTitle("loading register data...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

                ApiRetrofit.getInstance().checkUserToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseResponse -> {
                    String code = baseResponse.getCode();
                    if("000".equals(code)){
                        mProgressDialog.cancel();
                        jumpToActivity(MainActivity.class);
                        finish();
                    }else{
                        mProgressDialog.cancel();
                        jumpToActivity(LoginActivity.class);
                        finish();
                    }
                });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                SplashActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mProgressDialog.cancel();
//                        jumpToActivity(LoginActivity.class);
//                        finish();
//                    }
//                });
//            }
//        }).start();
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
