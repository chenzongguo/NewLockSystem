package cn.njthl.HotelClean.ui.fragment;

import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.R2;
import cn.njthl.HotelClean.ui.activity.MainActivity;
import cn.njthl.HotelClean.ui.base.BaseFragment;
import cn.njthl.HotelClean.ui.presenter.MeFgPresenter;
import cn.njthl.HotelClean.ui.view.MeFgView;

public class MeFragment extends BaseFragment<MeFgView, MeFgPresenter> implements MeFgView {

    @BindView(R2.id.tvUser)
    TextView tvUser;

    @BindView(R2.id.tvUserInvitation)
    TextView tvUserInvitation;

//    @BindView(R2.id.tv_setting)
//    TextView tv_setting;

    @BindView(R2.id.btnLogout)
    Button mBtnLogout;


    @Override
    public void initListener() {
        super.initListener();
        tvUser.setOnClickListener(v -> mPresenter.toUserManageActivity());
        tvUserInvitation.setOnClickListener(v -> mPresenter.toUserInvitationActivity());
        mBtnLogout.setOnClickListener(v -> mPresenter.logout());
    }

    @Override
    protected MeFgPresenter createPresenter() {
        return new MeFgPresenter((MainActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_me;
    }


    @Override
    public TextView getTvUser() {
        return null;
    }

    @Override
    public TextView getTvUserInvitation() {
        return null;
    }
}