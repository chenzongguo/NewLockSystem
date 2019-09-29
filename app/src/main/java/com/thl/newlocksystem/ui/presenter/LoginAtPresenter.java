package com.thl.newlocksystem.ui.presenter;

import android.text.TextUtils;


import com.thl.newlocksystem.R;
import com.thl.newlocksystem.api.ApiRetrofit;
import com.thl.newlocksystem.db.DBManager;
import com.thl.newlocksystem.db.model.SysUser;
import com.thl.newlocksystem.model.request.UserLoginRequest;
import com.thl.newlocksystem.ui.activity.LoginActivity;
import com.thl.newlocksystem.ui.activity.MainActivity;
import com.thl.newlocksystem.ui.activity.RegisterActivity;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;
import com.thl.newlocksystem.ui.view.ILoginAtView;
import com.thl.newlocksystem.util.LogUtils;
import com.thl.newlocksystem.util.UIUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LoginAtPresenter extends BasePresenter<ILoginAtView> {

    public LoginAtPresenter(BaseActivity context) {
        super(context);
    }

    public void login() {
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
//                        showUpdateDialog(checkUpdateResponse.getData().getDownload_address());
//                        registerReceiver();
            }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
            }
        });

        SysUser userBean = DBManager.getInstance().getSysUserByPwd(phone,pwd);
        if(userBean!=null) {
//            SharedPreferencesHelper.set(LoginActivity.this,
//                    SPConstant.SYS_USER, SPConstant.User_Number, userBean.getUser_Number());
//            SharedPreferencesHelper.set(LoginActivity.this,
//                    SPConstant.SYS_USER, SPConstant.User_Power, userBean.getUser_Power());
//            SharedPreferencesHelper.set(LoginActivity.this,
//                    SPConstant.SYS_USER, SPConstant.User_Name, userBean.getUser_Name());
//            mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
            mContext.jumpToActivityAndClearTask(MainActivity.class);
            mContext.finish();
        }else{
            mContext.jumpToActivityAndClearTask(MainActivity.class);
            mContext.finish();
        }
    }

    public void register(){
        mContext.jumpToActivityAndClearTop(RegisterActivity.class);
//        mContext.finish();
    }

    private void loginError(Throwable throwable) {
        LogUtils.e(throwable.getLocalizedMessage());
        UIUtils.showToast(throwable.getLocalizedMessage());
        mContext.hideWaitingDialog();
    }
}
