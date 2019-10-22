package cn.njthl.HotelClean.ui.fragment;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.ui.base.BaseFragment;
import cn.njthl.HotelClean.ui.presenter.MeFgPresenter;
import cn.njthl.HotelClean.ui.view.MeFgView;

public class MeFragment extends BaseFragment<MeFgView, MeFgPresenter> implements MeFgView {

    @Override
    protected MeFgPresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_me;
    }


}