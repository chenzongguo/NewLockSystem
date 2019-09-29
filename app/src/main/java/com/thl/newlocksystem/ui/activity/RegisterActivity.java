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

    @BindView(R2.id.etVerifyCode)
    EditText mEtVerifyCode;

    @BindView(R2.id.btnCheckCaptcha)
    Button mBtnCheckCaptcha;

    @BindView(R2.id.etPwd)
    EditText mEtPwd;

    @BindView(R2.id.etPwd2)
    EditText mEtPwd2;

    @BindView(R2.id.btnUserRegister)
    Button mBtnUserRegister;


    @Override
    public void initListener() {
        mBtnGetVerifyCode.setOnClickListener(v -> mPresenter.GetVerifyCode());
        mBtnCheckCaptcha.setOnClickListener(v -> mPresenter.GetVerifyCode());
        mBtnUserRegister.setOnClickListener(v -> mPresenter.register());
    }


    @Override
    public void requestPermissionResult(boolean allowPermission) {

    }

    @Override
    protected RegisterAtPresenter createPresenter() {
        return new RegisterAtPresenter(this);
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
        return mEtVerifyCode;
    }

    @Override
    public EditText getEtPwd() {
        return mEtPwd;
    }

    @Override
    public EditText getEtPwd2() {
        return mEtPwd2;
    }
}
