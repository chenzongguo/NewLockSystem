package com.thl.newlocksystem.ui.presenter;

import com.thl.newlocksystem.ui.activity.OrderNoConfirmActivity;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;
import com.thl.newlocksystem.ui.view.OrderManageFgView;

public class OrderManageFgPresenter extends BasePresenter<OrderManageFgView> {
    public OrderManageFgPresenter(BaseActivity context) {
        super(context);
    }

    public void toActivity(){
        mContext.jumpToActivityAndClearTop(OrderNoConfirmActivity.class);
    }


}