package com.thl.newlocksystem.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thl.newlocksystem.R;
import com.thl.newlocksystem.R2;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.presenter.RegisterAtPresenter;
import com.thl.newlocksystem.ui.view.IRegisterAtView;
import com.thl.newlocksystem.util.UIUtils;

import butterknife.BindView;

public class RegisterActivity extends BaseActivity<IRegisterAtView, RegisterAtPresenter> implements IRegisterAtView {
    @BindView(R2.id.etPhone)
    EditText mEtPhone;

    @BindView(R2.id.btnGetVerifyCode)
    Button mBtnGetVerifyCode;



    @Override
    public void initListener() {
        mBtnGetVerifyCode.setOnClickListener(v -> mPresenter.GetVerifyCode());
    }


    @Override
    public void requestPermissionResult(boolean allowPermission) {

    }

    @Override
    protected RegisterAtPresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    public EditText getEtPhone() {
        return mEtPhone;
    }

    @Override
    public EditText getEtVerifyCode() {
        return null;
    }

    @Override
    public EditText getEtPwd() {
        return null;
    }

    @Override
    public EditText getEtPwd2() {
        return null;
    }
}
