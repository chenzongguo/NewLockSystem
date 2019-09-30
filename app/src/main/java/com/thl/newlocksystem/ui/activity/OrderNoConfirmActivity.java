package com.thl.newlocksystem.ui.activity;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thl.newlocksystem.R;
import com.thl.newlocksystem.R2;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.presenter.OrderNoConfirmAtPresenter;
import com.thl.newlocksystem.ui.view.IOrderNoConfirmAtView;

import butterknife.BindView;

public class OrderNoConfirmActivity extends BaseActivity<IOrderNoConfirmAtView, OrderNoConfirmAtPresenter> implements IOrderNoConfirmAtView {

    @BindView(R2.id.lv_order_no_confirm)
    ListView lv_order_no_confirm;
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getConversations();
    }

    @Override
    public void requestPermissionResult(boolean allowPermission) {

    }

    @Override
    protected OrderNoConfirmAtPresenter createPresenter() {
        return new OrderNoConfirmAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.at_order_no_confirm;
    }

    @Override
    public ListView getLvOrderNoConfirm() {
        return lv_order_no_confirm;
    }
}
