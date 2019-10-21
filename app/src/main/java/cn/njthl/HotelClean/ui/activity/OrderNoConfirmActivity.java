package cn.njthl.HotelClean.ui.activity;

import android.widget.ImageView;
import android.widget.ListView;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.R2;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.presenter.OrderNoConfirmAtPresenter;
import cn.njthl.HotelClean.ui.view.IOrderNoConfirmAtView;

import butterknife.BindView;

public class OrderNoConfirmActivity extends BaseActivity<IOrderNoConfirmAtView, OrderNoConfirmAtPresenter> implements IOrderNoConfirmAtView {

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

    @Override
    public ImageView getImaNoOrder() {
        return img_NoOrder;
    }
}
