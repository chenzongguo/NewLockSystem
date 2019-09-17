package com.thl.newlocksystem.ui.fragment;

import android.widget.ListView;

import com.thl.newlocksystem.R;
import com.thl.newlocksystem.ui.base.BaseFragment;
import com.thl.newlocksystem.ui.presenter.OrderAllocationFgPresenter;
import com.thl.newlocksystem.ui.view.OrderAllocationFgView;

public class OrderAllocationFragment extends BaseFragment<OrderAllocationFgView, OrderAllocationFgPresenter> implements OrderAllocationFgView{
    @Override
    protected OrderAllocationFgPresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_order_allocation;
    }

    @Override
    public ListView getLvOrderAllocation() {
        return null;
    }
}
