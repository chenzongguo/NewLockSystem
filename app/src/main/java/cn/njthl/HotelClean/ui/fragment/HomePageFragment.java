package cn.njthl.HotelClean.ui.fragment;

import android.widget.ImageView;
import android.widget.ListView;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.R2;
import cn.njthl.HotelClean.ui.activity.MainActivity;
import cn.njthl.HotelClean.ui.base.BaseFragment;
import cn.njthl.HotelClean.ui.presenter.HomePageFgPresenter;
import cn.njthl.HotelClean.ui.view.HomePageFgView;

import butterknife.BindView;
import cn.njthl.HotelClean.widget.MyListView;

public class HomePageFragment extends BaseFragment<HomePageFgView, HomePageFgPresenter> implements HomePageFgView{

    @BindView(R2.id.lv_order_receive)
    MyListView lv_OrderReceive;

    @BindView(R2.id.img_NoOrder)
    ImageView img_NoOrder;

    @Override
    public void onResume() {
        super.onResume();
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
    public MyListView getLvOrderReceive() {
        return lv_OrderReceive;
    }

    @Override
    public ImageView getImaNoOrder() {
        return img_NoOrder;
    }
}
