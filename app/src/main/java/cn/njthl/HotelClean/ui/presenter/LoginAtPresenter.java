package cn.njthl.HotelClean.ui.presenter;

import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;


import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.api.ApiRetrofit;
import cn.njthl.HotelClean.app.AppConst;
import cn.njthl.HotelClean.app.MyApp;
import cn.njthl.HotelClean.model.request.UserLoginRequest;
import cn.njthl.HotelClean.ui.activity.MainActivity;
import cn.njthl.HotelClean.ui.activity.RegisterActivity;
import cn.njthl.HotelClean.ui.activity.UserPerfectActivity;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.base.BasePresenter;
import cn.njthl.HotelClean.ui.view.ILoginAtView;
import cn.njthl.HotelClean.util.ButtonUtils;
import cn.njthl.HotelClean.util.LogUtils;
import cn.njthl.HotelClean.util.NetUtils;
import cn.njthl.HotelClean.util.SPUtils;
import cn.njthl.HotelClean.util.UIUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LoginAtPresenter extends BasePresenter<ILoginAtView> {

    public LoginAtPresenter(BaseActivity context) {
        super(context);
    }

    public void login() {
        if (!ButtonUtils.isFastDoubleClick()) {
            String phone = getView().getEtPhone().getText().toString().trim();
            String pwd = getView().getEtPwd().getText().toString().trim();

            if (TextUtils.isEmpty(phone)) {
                UIUtils.showToast(UIUtils.getString(R.string.IDcard_not_empty));
                return;
            }
            if (TextUtils.isEmpty(pwd)) {
                UIUtils.showToast(UIUtils.getString(R.string.password_not_empty));
                return;
            }

            if (phone.length() < 11) {
                UIUtils.showToast("手机号长度不正确");
                return ;
            }

            if (pwd.length() < 6) {
                UIUtils.showToast("密码长度过短");
                return ;
            }
            if(!NetUtils.isConnectedAndToast(mContext))
                return;
            mContext.showWaitingDialog("登录中，请稍后");
            UserLoginRequest userLoginRequest= new UserLoginRequest();
            userLoginRequest.setType("1");
            userLoginRequest.setUser_phone(phone);
            userLoginRequest.setUser_pwd(pwd);
            ApiRetrofit.getInstance().userLogin(userLoginRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(userLoginResponse -> {
                        String code = userLoginResponse.getCode();
                        if("000".equals(code)){
                            mContext.hideWaitingDialog();
                            SPUtils.getInstance(MyApp.getContext()).putString("USER_TOKEN",userLoginResponse.getData().getUser_token());
//                            AppConst.USER_ID = userLoginResponse.getData().getUser_id();
                            SPUtils.getInstance(MyApp.getContext()).putString("USER_ID",userLoginResponse.getData().getUser_id());
                            AppConst.ROLE_ID = userLoginResponse.getData().getRole_id();
                            AppConst.Is_complete = userLoginResponse.getData().getIs_complete();
                            if(userLoginResponse.getData().getIs_complete().equals("0"))
                                mContext.jumpToActivity(UserPerfectActivity.class);
                            else
                                mContext.jumpToActivityAndClearTask(MainActivity.class);
//                        showUpdateDialog(checkUpdateResponse.getData().getDownload_address());
//                        registerReceiver();
                        }else{
                            mContext.hideWaitingDialog();
                            Toast.makeText(mContext, userLoginResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                        }
                    },this::loginError);
        }


    }

    public void register(){
        if (!ButtonUtils.isFastDoubleClick()) {
            mContext.jumpToActivityAndClearTop(RegisterActivity.class);
        }

//        mContext.finish();
    }

    private void loginError(Throwable throwable) {
        LogUtils.e(throwable.getLocalizedMessage());
        UIUtils.showToast(throwable.getLocalizedMessage());
        if (mContext == null || mContext.isDestroyed() || mContext.isFinishing()) {
            return;
        }
        mContext.hideWaitingDialog();
    }
}
