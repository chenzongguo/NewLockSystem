package cn.njthl.HotelClean.ui.presenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import cn.njthl.HotelClean.app.MyApp;
import cn.njthl.HotelClean.ui.activity.LoginActivity;
import cn.njthl.HotelClean.ui.activity.OrderDetailActivity;
import cn.njthl.HotelClean.ui.activity.SettingActivity;
import cn.njthl.HotelClean.ui.activity.UserInvitationActivity;
import cn.njthl.HotelClean.ui.activity.UserManageActivity;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.base.BasePresenter;
import cn.njthl.HotelClean.ui.view.MeFgView;
import cn.njthl.HotelClean.util.ActivityCollectorUtils;
import cn.njthl.HotelClean.util.SPUtils;

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

    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("确认退出应用吗");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SPUtils.getInstance(MyApp.getContext()).putString("USER_TOKEN","");
                ActivityCollectorUtils.finishAllActivity();
                mContext.jumpToActivityAndClearTop(LoginActivity.class);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }
    public MeFgPresenter(BaseActivity context) {
        super(context);
    }
}
