package com.thl.newlocksystem.ui.fragment;

import android.widget.ListView;

import com.thl.newlocksystem.R;
import com.thl.newlocksystem.ui.base.BaseFragment;
import com.thl.newlocksystem.ui.presenter.OrderManageFgPresenter;
import com.thl.newlocksystem.ui.view.OrderManageFgView;

public class OrderManageFragment extends BaseFragment<OrderManageFgView, OrderManageFgPresenter> implements OrderManageFgView {

    @Override
    protected OrderManageFgPresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_order_manage;
    }


}