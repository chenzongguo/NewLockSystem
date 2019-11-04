package cn.njthl.HotelClean.ui.presenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.api.ApiRetrofit;
import cn.njthl.HotelClean.model.Bean.OrderBean;
import cn.njthl.HotelClean.model.request.GetOrderListRequest;
import cn.njthl.HotelClean.ui.activity.OrderDetailActivity;
import cn.njthl.HotelClean.ui.adapter.OrderAllocationAdapter;
import cn.njthl.HotelClean.ui.adapter.OrderReceiveAdapter;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.base.BasePresenter;
import cn.njthl.HotelClean.ui.view.OrderAllocationFgView;

import java.util.List;

import cn.njthl.HotelClean.util.ButtonUtils;
import cn.njthl.HotelClean.util.LogUtils;
import cn.njthl.HotelClean.util.UIUtils;
import cn.njthl.HotelClean.widget.MyListView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderAllocationFgPresenter extends BasePresenter<OrderAllocationFgView> implements OrderReceiveAdapter.OnListenerClick {
    private OrderAllocationAdapter orderAllocationAdapter;
    private List<OrderBean> orderBeanList;
    private LayoutInflater inflater;

    private LinearLayout LlyListNull;
    public OrderAllocationFgPresenter(BaseActivity context) {
        super(context);
    }


    public void getConversations() {
        loadData();

    }
    private void  loadData(){
        if(LlyListNull==null){
            inflater = LayoutInflater.from(mContext);
            LlyListNull = (LinearLayout) inflater.inflate(R.layout.include_list_null, null);
            getView().getLvOrderAllocation().addFooterView(LlyListNull);
        }
        GetOrderListRequest getOrderListRequest = new GetOrderListRequest();
        getOrderListRequest.setType("2");
        getOrderListRequest.setSelect_number("10");
        getOrderListRequest.setStart_number("0");
        getOrderListRequest.setIs_dispatch("1");
//        getOrderListRequest.setOrder_state("3");
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
                            LlyListNull.setVisibility(View.GONE);
                            setAdapter();
                        }else{
                            LlyListNull.setVisibility(View.VISIBLE);
                            setAdapter();
                        }
                    }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
                    }
                },OrderAllocationFgPresenter.this::loginError);
    }
    private void setAdapter(){

        if(orderAllocationAdapter == null)
            orderAllocationAdapter = new OrderAllocationAdapter(mContext,orderBeanList);
        orderAllocationAdapter.setOnClick(this);
        orderAllocationAdapter.setOrderList(orderBeanList);
        getView().getLvOrderAllocation().setAdapter(orderAllocationAdapter);
        getView().getLvOrderAllocation().setonRefreshListener(new MyListView.OnRefreshListener() {

            @Override
            public void onRefresh() {

                GetOrderListRequest getOrderListRequest = new GetOrderListRequest();
                getOrderListRequest.setType("2");
                getOrderListRequest.setSelect_number("10");
                getOrderListRequest.setStart_number("0");
                getOrderListRequest.setIs_dispatch("1");
//                getOrderListRequest.setOrder_state("3");
                ApiRetrofit.getInstance().getOrderList(getOrderListRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(getOrderListResponse -> {
                            String code = getOrderListResponse.getCode();
                            if("000".equals(code)){
                                orderBeanList =  getOrderListResponse.getData().getPaging_data();
                                orderAllocationAdapter.setOrderList(orderBeanList);
                                if(orderBeanList!=null && orderBeanList.size()>0){
                                    LlyListNull.setVisibility(View.GONE);
                                }else{
                                    LlyListNull.setVisibility(View.VISIBLE);
                                }
                                orderAllocationAdapter.notifyDataSetChanged();
                                getView().getLvOrderAllocation().onRefreshComplete();

                            }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
                                getView().getLvOrderAllocation().onRefreshComplete();
                                Toast.makeText(mContext, getOrderListResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                            }
                        },OrderAllocationFgPresenter.this::loginError);

            }
        });
        getView().getLvOrderAllocation().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!ButtonUtils.isFastDoubleClick()) {
                    if (orderBeanList.size() == 0)
                        return;
                    if (position > 0)
                        position = position - 1;
                    Intent intent = new Intent(mContext, OrderDetailActivity.class);
                    intent.putExtra("order_id", orderBeanList.get(position).getOrder_id());
                    intent.putExtra("order_state", orderBeanList.get(position).getOrder_state());
                    mContext.jumpToActivity(intent);
                }
            }
        });
        if (orderAllocationAdapter != null)
            orderAllocationAdapter.notifyDataSetChanged();
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
    private void loginError(Throwable throwable) {
        LogUtils.e(throwable.getLocalizedMessage());
        UIUtils.showToast(throwable.getLocalizedMessage());
        if (mContext == null || mContext.isDestroyed() || mContext.isFinishing()) {
            return;
        }
        mContext.hideWaitingDialog();
    }
}