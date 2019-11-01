package cn.njthl.HotelClean.ui.activity;

import android.widget.ImageView;
import android.widget.ListView;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.R2;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.presenter.NoArriveCorpAtPresenter;
import cn.njthl.HotelClean.ui.view.INoArriveCorpAtView;

import butterknife.BindView;
import cn.njthl.HotelClean.util.UIUtils;

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
    public void initView() {
        super.initView();
        String order_state = getIntent().getStringExtra("order_state");
        switch (order_state){
            case "4":
                setToolbarTitle("待确认订单");
                break;
            case "5":
                setToolbarTitle("已确认订单");
                break;
            case "6":
                setToolbarTitle("已上门订单");
                break;
            case "9":
                setToolbarTitle("已打扫订单");
                break;
            case "7":
                setToolbarTitle("已完成订单");
                break;
        }
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