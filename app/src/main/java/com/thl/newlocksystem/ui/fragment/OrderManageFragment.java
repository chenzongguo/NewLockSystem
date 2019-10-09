package com.thl.newlocksystem.ui.fragment;

import android.widget.LinearLayout;
import android.widget.ListView;

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
    public void initListener() {
        llyOrderNoConfirm.setOnClickListener(v -> mPresenter.toActivity());
        llyNoArriveCorp.setOnClickListener(v -> mPresenter.toActivity2());
    }
}