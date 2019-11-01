package cn.njthl.HotelClean.ui.presenter;

import android.content.Intent;

import cn.njthl.HotelClean.ui.activity.OrderDetailActivity;
import cn.njthl.HotelClean.ui.activity.SettingActivity;
import cn.njthl.HotelClean.ui.activity.UserInvitationActivity;
import cn.njthl.HotelClean.ui.activity.UserManageActivity;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.base.BasePresenter;
import cn.njthl.HotelClean.ui.view.MeFgView;

public class MeFgPresenter extends BasePresenter<MeFgView> {

    public void toUserManageActivity(){
        Intent intent = new Intent(mContext, UserManageActivity.class);
        mContext.jumpToActivity(intent);
    }

    public void toUserInvitationActivity(){
        Intent intent = new Intent(mContext, UserInvitationActivity.class);
        mContext.jumpToActivity(intent);
    }

    public void toSettingActivity(){
        Intent intent = new Intent(mContext, SettingActivity.class);
        mContext.jumpToActivity(intent);
    }
    public MeFgPresenter(BaseActivity context) {
        super(context);
    }
}
