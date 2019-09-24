package com.thl.newlocksystem.ui.presenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.thl.newlocksystem.ui.adapter.OrderReceiveAdapter;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;
import com.thl.newlocksystem.ui.view.HomePageFgView;

public class HomePageFgPresenter extends BasePresenter<HomePageFgView> implements OrderReceiveAdapter.OnListenerClick {
    private OrderReceiveAdapter orderReceiveAdapter;
    public HomePageFgPresenter(BaseActivity context) {
        super(context);
    }

    public void getConversations() {
        loadData();
        setAdapter();
    }
    private void  loadData(){

    }
    private void setAdapter(){

        if(orderReceiveAdapter == null)
        orderReceiveAdapter = new OrderReceiveAdapter(mContext);
        orderReceiveAdapter.setOnClick(this);
        getView().getLvOrderReceive().setAdapter(orderReceiveAdapter);
        getView().getLvOrderReceive().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,"长按点击事件",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        getView().getLvOrderReceive().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,"listview点击事件",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void OrderReceive() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("这是接单按钮?");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        //添加AlertDialog.Builder对象的setNegativeButton()方法
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }
}
