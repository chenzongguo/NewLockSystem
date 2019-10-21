package cn.njthl.HotelClean.ui.activity;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.R2;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.presenter.UserListAtPresenter;
import cn.njthl.HotelClean.ui.view.IUserListAtView;

import butterknife.BindView;

public class UserListActivity extends BaseActivity<IUserListAtView, UserListAtPresenter> implements IUserListAtView{

    @BindView(R2.id.lv_user)
    ListView lv_user;

    @BindView(R2.id.tvCleanerName)
    TextView tvCleanerName;

    @BindView(R2.id.btnParnterReceipt)
    Button btnParnterReceipt;
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getConversations();
    }



    @Override
    public void initListener() {
        btnParnterReceipt.setOnClickListener(v -> mPresenter.OrderAllocation());
//        mBtnCheckCaptcha.setOnClickListener(v -> mPresenter.GetVerifyCode());
//        mBtnUserRegister.setOnClickListener(v -> mPresenter.register());
    }
    @Override
    public void requestPermissionResult(boolean allowPermission) {

    }

    @Override
    protected UserListAtPresenter createPresenter() {
        return new UserListAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.user_list;
    }

    @Override
    public ListView getLvOrderNoConfirm() {
        return lv_user;
    }

    @Override
    public TextView getTvCleanerName() {
        return tvCleanerName;
    }

    @Override
    public Button getBtnParnterReceipt() {
        return btnParnterReceipt;
    }
}
