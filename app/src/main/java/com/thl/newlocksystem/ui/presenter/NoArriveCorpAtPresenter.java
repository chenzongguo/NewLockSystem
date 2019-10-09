package com.thl.newlocksystem.ui.presenter;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.thl.newlocksystem.api.ApiRetrofit;
import com.thl.newlocksystem.model.Bean.OrderBean;
import com.thl.newlocksystem.model.request.GetOrderListRequest;
import com.thl.newlocksystem.ui.activity.OrderDetailActivity;
import com.thl.newlocksystem.ui.adapter.OrderReceiveAdapter;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;
import com.thl.newlocksystem.ui.view.INoArriveCorpAtView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NoArriveCorpAtPresenter extends BasePresenter<INoArriveCorpAtView> {

    private OrderReceiveAdapter orderReceiveAdapter;
    private List<OrderBean> orderBeanList;

    public NoArriveCorpAtPresenter(BaseActivity context) {
        super(context);
    }
    public void getConversations() {
        loadData();
    }
    private void  loadData(){
        GetOrderListRequest getOrderListRequest = new GetOrderListRequest();
        getOrderListRequest.setType("1");
        getOrderListRequest.setSelect_number("10");
        getOrderListRequest.setStart_number("0");
        getOrderListRequest.setOrder_state("5");
        ApiRetrofit.getInstance().getOrderList(getOrderListRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getOrderListResponse -> {
                    String code = getOrderListResponse.getCode();
                    if("000".equals(code)){
                        orderBeanList =  getOrderListResponse.getData().getPaging_data();
//                        showUpdateDialog(checkUpdateResponse.getData().getDownload_address());
//                        registerReceiver();
                        if(orderBeanList!=null && orderBeanList.size()>0){
                            setAdapter();
                            getView().getImaNoOrder().setVisibility(View.GONE);
                        }else{
                            getView().getImaNoOrder().setVisibility(View.VISIBLE);
                        }
                    }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void setAdapter(){

        if(orderReceiveAdapter == null)
            orderReceiveAdapter = new OrderReceiveAdapter(mContext,orderBeanList);
//        orderReceiveAdapter.setOnClick(this);
        getView().getLvNoArriveCorp().setAdapter(orderReceiveAdapter);
        getView().getLvNoArriveCorp().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,"长按点击事件",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        getView().getLvNoArriveCorp().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,"listview点击事件",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra("order_id",orderBeanList.get(position).getOrder_id());
                intent.putExtra("order_state",orderBeanList.get(position).getOrder_state());
                mContext.jumpToActivity(intent);
//                mContext.jumpToActivityAndClearTop(OrderDetailActivity.class);
            }
        });
    }

//    @Override
//    public void OrderReceive() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//        builder.setMessage("这是接单按钮?");
//        builder.setTitle("提示");
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        });
//
//        //添加AlertDialog.Builder对象的setNegativeButton()方法
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//
//        builder.create().show();
//    }
}
