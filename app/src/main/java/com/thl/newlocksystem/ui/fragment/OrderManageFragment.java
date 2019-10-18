package com.thl.newlocksystem.ui.fragment;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.thl.newlocksystem.R;
import com.thl.newlocksystem.R2;
import com.thl.newlocksystem.ui.activity.MainActivity;
import com.thl.newlocksystem.ui.base.BaseFragment;
import com.thl.newlocksystem.ui.presenter.OrderManageFgPresenter;
import com.thl.newlocksystem.ui.view.OrderManageFgView;
import com.thl.newlocksystem.util.UIUtils;

import butterknife.BindView;

public class OrderManageFragment extends BaseFragment<OrderManageFgView, OrderManageFgPresenter> implements OrderManageFgView {

    @BindView(R2.id.llyOrderNoConfirm)
    LinearLayout llyOrderNoConfirm;

    @BindView(R2.id.llyNoArriveCorp)
    LinearLayout llyNoArriveCorp;

    @BindView(R2.id.llyArriveCrop)
    LinearLayout llyArriveCrop;

    @BindView(R2.id.llyOrderComplete)
    LinearLayout llyOrderComplete;

    @BindView(R2.id.llyCleanComplete)
    LinearLayout llyCleanComplete;

    @BindView(R2.id.tvOrderNoConfirmNum)
    TextView tvOrderNoConfirmNum;

    @BindView(R2.id.tvNoArriveCorpNum)
    TextView tvNoArriveCorpNum;

    @BindView(R2.id.tvArriveCropNum)
    TextView tvArriveCropNum;

    @BindView(R2.id.tvOrderCompleteNum)
    TextView tvOrderCompleteNum;

    @BindView(R2.id.tvCleanCompleteNum)
    TextView tvCleanCompleteNum;

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.getConversations();
    }

    @Override
    protected OrderManageFgPresenter createPresenter() {
        return new OrderManageFgPresenter((MainActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_order_manage;
    }


    @Override
    public LinearLayout getLlyOrderNoConfirm() {
        return llyOrderNoConfirm;
    }

    @Override
    public TextView getTvOrderNoConfirmNum() {
        return tvOrderNoConfirmNum;
    }

    @Override
    public TextView getTvNoArriveCorpNum() {
        return tvNoArriveCorpNum;
    }

    @Override
    public TextView getTvArriveCropNum() {
        return tvArriveCropNum;
    }

    @Override
    public TextView getTvCompleteNum() {
        return tvOrderCompleteNum;
    }

    @Override
    public TextView getTvCleanCompleteNum() {
        return tvCleanCompleteNum;
    }

    @Override
    public void initListener() {
        llyOrderNoConfirm.setOnClickListener(v -> mPresenter.toActivity());
        llyNoArriveCorp.setOnClickListener(v -> mPresenter.toActivity2("5"));
        llyArriveCrop.setOnClickListener(v -> mPresenter.toActivity2("6"));
        llyOrderComplete.setOnClickListener(v -> mPresenter.toActivity2("7"));
        llyCleanComplete.setOnClickListener(v -> mPresenter.toActivity2("9"));
    }
}