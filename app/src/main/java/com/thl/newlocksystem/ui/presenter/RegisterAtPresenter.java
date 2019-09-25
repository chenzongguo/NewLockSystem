package com.thl.newlocksystem.ui.presenter;

import android.text.TextUtils;

import com.thl.newlocksystem.R;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;
import com.thl.newlocksystem.ui.view.IRegisterAtView;
import com.thl.newlocksystem.util.UIUtils;

public class RegisterAtPresenter extends BasePresenter<IRegisterAtView> {

    public RegisterAtPresenter(BaseActivity context) {
        super(context);
    }

    public void login() {
        String phone = getView().getEtPhone().getText().toString().trim();
        String pwd = getView().getEtPwd().getText().toString().trim();


    }

    public void GetVerifyCode(){
        String phone = getView().getEtPhone().getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            UIUtils.showToast(UIUtils.getString(R.string.IDcard_not_empty));
            return;
        }
    }


}