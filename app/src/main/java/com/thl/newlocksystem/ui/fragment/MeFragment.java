package com.thl.newlocksystem.ui.fragment;

import com.thl.newlocksystem.R;
import com.thl.newlocksystem.ui.base.BaseFragment;
import com.thl.newlocksystem.ui.presenter.MeFgPresenter;
import com.thl.newlocksystem.ui.view.MeFgView;

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