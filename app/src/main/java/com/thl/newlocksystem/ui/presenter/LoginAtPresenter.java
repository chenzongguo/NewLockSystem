package com.thl.newlocksystem.ui.presenter;

import android.text.TextUtils;


import com.thl.newlocksystem.R;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;
import com.thl.newlocksystem.ui.view.ILoginAtView;
import com.thl.newlocksystem.util.LogUtils;
import com.thl.newlocksystem.util.UIUtils;


public class LoginAtPresenter extends BasePresenter<ILoginAtView> {

    public LoginAtPresenter(BaseActivity context) {
        super(context);
    }

    public void login() {
        String phone = getView().getEtPhone().getText().toString().trim();
        String pwd = getView().getEtPwd().getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            UIUtils.showToast(UIUtils.getString(R.string.phone_not_empty));
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            UIUtils.showToast(UIUtils.getString(R.string.password_not_empty));
            return;
        }

        mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
//        ApiRetrofit.getInstance().login(AppConst.REGION, phone, pwd)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(loginResponse -> {
//                    int code = loginResponse.getCode();
//                    mContext.hideWaitingDialog();
//                    if (code == 200) {
//                        UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
//                        mContext.jumpToActivityAndClearTask(MainActivity.class);
//                        mContext.finish();
//                    } else {
//                        loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
//                    }
//                }, this::loginError);
    }

    private void loginError(Throwable throwable) {
        LogUtils.e(throwable.getLocalizedMessage());
        UIUtils.showToast(throwable.getLocalizedMessage());
        mContext.hideWaitingDialog();
    }
}
