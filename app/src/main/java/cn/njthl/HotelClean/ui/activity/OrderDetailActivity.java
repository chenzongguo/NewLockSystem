package cn.njthl.HotelClean.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.R2;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.presenter.OederDetailAtPresenter;
import cn.njthl.HotelClean.ui.view.IOrderDetailAtView;
import cn.njthl.HotelClean.util.UIUtils;

import androidx.annotation.Nullable;
import butterknife.BindView;

public class OrderDetailActivity extends BaseActivity<IOrderDetailAtView, OederDetailAtPresenter> implements IOrderDetailAtView {
    @BindView(R2.id.tvCorpName)
    TextView tvCorpName;

    @BindView(R2.id.tvAddress)
    TextView tvAddress;



    @BindView(R2.id.tvOrderState)
    TextView tvOrderState;

    @BindView(R2.id.tvOrderId)
    TextView tvOrderId;

    @BindView(R2.id.tvCreateTime)
    TextView tvCreateTime;

    @BindView(R2.id.tvTime)
    TextView tvTime;

    @BindView(R2.id.tvContacts)
    TextView tvContacts;

    @BindView(R2.id.tvContactPhone)
    TextView tvContactPhone;

    @BindView(R2.id.tvPaymentPrice)
    TextView tvPaymentPrice;

//
    @BindView(R2.id.btnParnterReceipt)
    Button btnParnterReceipt;

    @BindView(R2.id.AllChoose)
    CheckBox AllChoose;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this, UIUtils.getColor(R.color.assist_green1), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String user_id = "";
        if (data!=null)
             user_id = data.getStringExtra("user_id");
        if(user_id!=null&&!user_id.equals(""))
            mPresenter.OrderAllocation(user_id);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getConversations();
    }

    @Override
    public void initView() {
        super.initView();
        mToolbarTitle.setText("订单详情");
    }
    @Override
    public void initListener() {
        btnParnterReceipt.setOnClickListener(v -> mPresenter.ParnterReceipt());
//        mBtnCheckCaptcha.setOnClickListener(v -> mPresenter.GetVerifyCode());
//        mBtnUserRegister.setOnClickListener(v -> mPresenter.register());
        AllChoose.setOnClickListener(v -> mPresenter.AllChoose());
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
    public TextView getTvOrderId() {
        return tvOrderId;
    }

    @Override
    public TextView getTvCreateTime() {
        return tvCreateTime;
    }

    @Override
    public TextView getTvTime() {
        return tvTime;
    }

    @Override
    public TextView getTvContacts() {
        return tvContacts;
    }

    @Override
    public TextView getTvContactPhone() {
        return tvContactPhone;
    }

    @Override
    public TextView getTvPaymentPrice() {
        return tvPaymentPrice;
    }

    @Override
    public TextView getTvOrderState() {
        return tvOrderState;
    }

    @Override
    public CheckBox getAllChoose() {
        return AllChoose;
    }

    @Override
    public Button getBtnParnterReceipt() {
        return btnParnterReceipt;
    }
}