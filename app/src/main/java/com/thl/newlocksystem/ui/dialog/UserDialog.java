package com.thl.newlocksystem.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.thl.newlocksystem.R;
import com.thl.newlocksystem.ui.activity.OrderDetailActivity;
import com.thl.newlocksystem.ui.adapter.OrderReceiveAdapter;
import com.thl.newlocksystem.ui.adapter.UserListAdapter;
import com.thl.newlocksystem.widget.CustomDialog;

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
