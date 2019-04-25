package com.thl.newlocksystem.ui.presenter;

import android.text.TextUtils;


import com.thl.newlocksystem.R;
import com.thl.newlocksystem.db.DBManager;
import com.thl.newlocksystem.db.model.SysUser;
import com.thl.newlocksystem.ui.activity.LoginActivity;
import com.thl.newlocksystem.ui.activity.MainActivity;
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
            UIUtils.showToast(UIUtils.getString(R.string.IDcard_not_empty));
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            UIUtils.showToast(UIUtils.getString(R.string.password_not_empty));
            return;
        }


        SysUser userBean = DBManager.getInstance().getSysUserByPwd(phone,pwd);
        if(userBean!=null) {
//            SharedPreferencesHelper.set(LoginActivity.this,
//                    SPConstant.SYS_USER, SPConstant.User_Number, userBean.getUser_Number());
//            SharedPreferencesHelper.set(LoginActivity.this,
//                    SPConstant.SYS_USER, SPConstant.User_Power, userBean.getUser_Power());
//            SharedPreferencesHelper.set(LoginActivity.this,
//                    SPConstant.SYS_USER, SPConstant.User_Name, userBean.getUser_Name());
            mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
            mContext.jumpToActivityAndClearTask(MainActivity.class);
            mContext.finish();
        }else{

        }
    }

    private void loginError(Throwable throwable) {
        LogUtils.e(throwable.getLocalizedMessage());
        UIUtils.showToast(throwable.getLocalizedMessage());
        mContext.hideWaitingDialog();
    }
}
