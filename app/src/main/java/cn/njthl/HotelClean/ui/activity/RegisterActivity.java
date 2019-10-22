package cn.njthl.HotelClean.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.jaeger.library.StatusBarUtil;

import androidx.annotation.Nullable;
import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.R2;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.presenter.RegisterAtPresenter;
import cn.njthl.HotelClean.ui.view.IRegisterAtView;

import butterknife.BindView;
import cn.njthl.HotelClean.util.UIUtils;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this, UIUtils.getColor(R.color.colorPrimary), 10);
    }

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
