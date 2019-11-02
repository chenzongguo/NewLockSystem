package cn.njthl.HotelClean.ui.activity;

import android.widget.Button;

import butterknife.BindView;
import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.R2;
import cn.njthl.HotelClean.app.AppConst;
import cn.njthl.HotelClean.app.MyApp;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.base.BasePresenter;
import cn.njthl.HotelClean.util.ActivityCollectorUtils;
import cn.njthl.HotelClean.util.SPUtils;

public class SettingActivity extends BaseActivity {

    @BindView(R2.id.btnLogout)
    Button mBtnLogout;

    @Override
    public void initListener() {
        super.initListener();
        mBtnLogout.setOnClickListener(v -> logout());
    }

    private void logout(){
        SPUtils.getInstance(MyApp.getContext()).putString("USER_TOKEN","");
        ActivityCollectorUtils.finishAllActivity();
        jumpToActivityAndClearTop(LoginActivity.class);
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
        return R.layout.at_setting;
    }

}
