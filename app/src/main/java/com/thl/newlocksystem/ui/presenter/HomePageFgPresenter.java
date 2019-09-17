package com.thl.newlocksystem.ui.presenter;

import com.thl.newlocksystem.ui.adapter.OrderReceiveAdapter;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;
import com.thl.newlocksystem.ui.view.HomePageFgView;

public class HomePageFgPresenter extends BasePresenter<HomePageFgView> {
    private OrderReceiveAdapter orderReceiveAdapter;
    public HomePageFgPresenter(BaseActivity context) {
        super(context);
    }

    public void getConversations() {
        loadData();
        setAdapter();
    }
    private void  loadData(){

    }
    private void setAdapter(){

        if(orderReceiveAdapter == null)
        orderReceiveAdapter = new OrderReceiveAdapter(mContext);
        getView().getLvOrderReceive().setAdapter(orderReceiveAdapter);
    }
}
