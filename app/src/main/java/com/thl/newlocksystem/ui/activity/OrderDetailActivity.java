package com.thl.newlocksystem.ui.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.thl.newlocksystem.R;
import com.thl.newlocksystem.R2;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.presenter.OederDetailAtPresenter;
import com.thl.newlocksystem.ui.presenter.RegisterAtPresenter;
import com.thl.newlocksystem.ui.view.IOrderDetailAtView;

import butterknife.BindView;

public class OrderDetailActivity extends BaseActivity<IOrderDetailAtView, OederDetailAtPresenter> implements IOrderDetailAtView {
    @BindView(R2.id.tvCorpName)
    TextView tvCorpName;

    @BindView(R2.id.tvAddress)
    TextView tvAddress;
//
    @BindView(R2.id.btnParnterReceipt)
    Button btnParnterReceipt;
//
//    @BindView(R2.id.etVerifyCode)
//    EditText mEtVerifyCode;
//
//    @BindView(R2.id.btnCheckCaptcha)
//    Button mBtnCheckCaptcha;
//
//    @BindView(R2.id.etPwd)
//    EditText mEtPwd;
//
//    @BindView(R2.id.etPwd2)
//    EditText mEtPwd2;
//
//    @BindView(R2.id.btnUserRegister)
//    Button mBtnUserRegister;

    @BindView(R2.id.lvOrderRoom)
    ListView lvOrderRoom;


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getConversations();
    }

    @Override
    public void initListener() {
        btnParnterReceipt.setOnClickListener(v -> mPresenter.ParnterReceipt());
//        mBtnCheckCaptcha.setOnClickListener(v -> mPresenter.GetVerifyCode());
//        mBtnUserRegister.setOnClickListener(v -> mPresenter.register());
    }


    @Override
    public void requestPermissionResult(boolean allowPermission) {

    }

    @Override
    protected OederDetailAtPresenter createPresenter() {
        return new OederDetailAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.at_order_detail;
    }


    @Override
    public ListView getLvRoomInfo() {
        return lvOrderRoom;
    }

    @Override
    public TextView getTvAddress() {
        return tvAddress;
    }

    @Override
    public TextView getTvCorpName() {
        return tvCorpName;
    }

    @Override
    public Button getBtnParnterReceipt() {
        return btnParnterReceipt;
    }
}