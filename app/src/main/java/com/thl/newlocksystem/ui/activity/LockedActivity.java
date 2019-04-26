package com.thl.newlocksystem.ui.activity;

import com.jaeger.library.StatusBarUtil;
import com.thl.newlocksystem.R;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;

public class LockedActivity extends BaseActivity {

    @Override
    public void initView() {
        super.initView();
        StatusBarUtil.setTranslucent(this);
    }
    @Override
    public void requestPermissionResult(boolean allowPermission) {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_locked;
    }
}
