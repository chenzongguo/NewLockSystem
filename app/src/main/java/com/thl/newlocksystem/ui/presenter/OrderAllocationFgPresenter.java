package com.thl.newlocksystem.ui.presenter;

import com.thl.newlocksystem.ui.adapter.OrderAllocationAdapter;
import com.thl.newlocksystem.ui.adapter.OrderReceiveAdapter;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;
import com.thl.newlocksystem.ui.view.OrderAllocationFgView;

public class OrderAllocationFgPresenter extends BasePresenter<OrderAllocationFgView> {
    private OrderAllocationAdapter orderAllocationAdapter;
    public OrderAllocationFgPresenter(BaseActivity context) {
        super(context);
    }

    public void getConversations() {
        loadData();
        setAdapter();
    }
    private void  loadData(){

    }
    private void setAdapter(){

        if(orderAllocationAdapter == null)
            orderAllocationAdapter = new OrderAllocationAdapter(mContext);
        getView().getLvOrderAllocation().setAdapter(orderAllocationAdapter);
    }
}