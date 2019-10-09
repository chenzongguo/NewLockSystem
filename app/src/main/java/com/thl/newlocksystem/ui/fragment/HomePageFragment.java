package com.thl.newlocksystem.ui.fragment;

import android.widget.ImageView;
import android.widget.ListView;

import com.thl.newlocksystem.R;
import com.thl.newlocksystem.R2;
import com.thl.newlocksystem.ui.activity.MainActivity;
import com.thl.newlocksystem.ui.base.BaseFragment;
import com.thl.newlocksystem.ui.presenter.HomePageFgPresenter;
import com.thl.newlocksystem.ui.view.HomePageFgView;

import butterknife.BindView;

public class HomePageFragment extends BaseFragment<HomePageFgView, HomePageFgPresenter> implements HomePageFgView{

    @BindView(R2.id.lv_order_receive)
    ListView lv_OrderReceive;

    @BindView(R2.id.img_NoOrder)
    ImageView img_NoOrder;

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getConversations();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.getConversations();
    }

    @Override
    protected HomePageFgPresenter createPresenter() {
        return new HomePageFgPresenter((MainActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home_page;
    }

    @Override
    public ListView getLvOrderReceive() {
        return lv_OrderReceive;
    }

    @Override
    public ImageView getImaNoOrder() {
        return img_NoOrder;
    }
}
