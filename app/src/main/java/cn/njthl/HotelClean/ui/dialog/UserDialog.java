package cn.njthl.HotelClean.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.ui.adapter.UserListAdapter;
import cn.njthl.HotelClean.widget.CustomDialog;

public class UserDialog extends CustomDialog {
    private ListView lvUser;
    private UserListAdapter userListAdapter;
    private Context mContext;

    public UserDialog(Context context, View layout, int style) {
        super(context, layout, style);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setAdapter();
    }
    private void initView(){

        lvUser = (ListView)findViewById(R.id.lv_user);

    }
    private void setAdapter(){

//        if(userListAdapter == null)
//            userListAdapter = new UserListAdapter(mContext);
////        userDialogAdapter.setOnClick(this);
//        lvUser.setAdapter(userListAdapter);

    }
}
