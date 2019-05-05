package com.thl.newlocksystem.ui.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.thl.newlocksystem.R;
import com.thl.newlocksystem.R2;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;
import com.thl.newlocksystem.ui.presenter.LockedAtPresenter;
import com.thl.newlocksystem.ui.presenter.LoginAtPresenter;
import com.thl.newlocksystem.ui.view.ILockedAtView;
import com.thl.newlocksystem.ui.view.ILoginAtView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;

public class LockedActivity extends BaseActivity<ILockedAtView, LockedAtPresenter> implements ILockedAtView {

    @BindViews({ R2.id.name, R2.id.sex,  R2.id.nation,R2.id.address,R2.id.cardnum,R2.id.phonenum,R2.id.detailed_addr})
    List<EditText> editTexts;

    @BindViews({R2.id.area,R2.id.lockedReson,R2.id.sourceOfBus,R2.id.lockType})
    List<TextView> textViews;
    @BindView(R2.id.sumbit)
    Button btn_sumbit;

    @Override
    public void initView() {
        super.initView();
        StatusBarUtil.setTranslucent(this);
    }
    @Override
    public void initListener() {
        btn_sumbit.setOnClickListener(v -> mPresenter.save());
    }
    @Override
    public void requestPermissionResult(boolean allowPermission) {

    }

    @Override
    protected LockedAtPresenter createPresenter() {
        return new LockedAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_locked;
    }

    @Override
    public List<EditText> GetEtList() {
        return editTexts;
    }

    @Override
    public List<TextView> GetTvList() {
        return textViews;
    }
}
