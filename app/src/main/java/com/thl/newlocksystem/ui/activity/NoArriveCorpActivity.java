package com.thl.newlocksystem.ui.activity;

import android.widget.ImageView;
import android.widget.ListView;

import com.thl.newlocksystem.R;
import com.thl.newlocksystem.R2;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.presenter.NoArriveCorpAtPresenter;
import com.thl.newlocksystem.ui.presenter.OrderNoConfirmAtPresenter;
import com.thl.newlocksystem.ui.view.INoArriveCorpAtView;
import com.thl.newlocksystem.ui.view.IOrderNoConfirmAtView;

import butterknife.BindView;

public class NoArriveCorpActivity extends BaseActivity<INoArriveCorpAtView, NoArriveCorpAtPresenter> implements INoArriveCorpAtView {

    @BindView(R2.id.lv_order_no_confirm)
    ListView lv_order_no_confirm;

    @BindView(R2.id.img_NoOrder)
    ImageView img_NoOrder;

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getConversations();
    }

    @Override
    public void requestPermissionResult(boolean allowPermission) {

    }

    @Override
    protected NoArriveCorpAtPresenter createPresenter() {
        return new NoArriveCorpAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.at_order_no_confirm;
    }


    @Override
    public ListView getLvNoArriveCorp() {
        return lv_order_no_confirm;
    }

    @Override
    public ImageView getImaNoOrder() {
        return img_NoOrder;
    }
}