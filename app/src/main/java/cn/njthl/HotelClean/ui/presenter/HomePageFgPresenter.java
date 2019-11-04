package cn.njthl.HotelClean.ui.presenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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
import cn.njthl.HotelClean.ui.adapter.OrderReceiveAdapter;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.base.BasePresenter;
import cn.njthl.HotelClean.ui.view.HomePageFgView;

import java.util.List;

import cn.njthl.HotelClean.util.ButtonUtils;
import cn.njthl.HotelClean.util.LogUtils;
import cn.njthl.HotelClean.util.UIUtils;
import cn.njthl.HotelClean.widget.MyListView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePageFgPresenter extends BasePresenter<HomePageFgView> implements OrderReceiveAdapter.OnListenerClick {
    private OrderReceiveAdapter orderReceiveAdapter;
    private List<OrderBean> orderBeanList;
    private LayoutInflater inflater;

    // ListView头部下拉刷新的布局
    private LinearLayout LlyListNull;
    public HomePageFgPresenter(BaseActivity context) {
        super(context);
    }

    public void getConversations() {
        loadData();

    }
    private void  loadData(){
        if(LlyListNull==null){
            inflater = LayoutInflater.from(mContext);
            LlyListNull = (LinearLayout) inflater.inflate(R.layout.include_list_null, null);
            getView().getLvOrderReceive().addFooterView(LlyListNull);
        }
        GetOrderListRequest getOrderListRequest = new GetOrderListRequest();
        getOrderListRequest.setType("2");
        getOrderListRequest.setSelect_number("10");
        getOrderListRequest.setStart_number("0");
        getOrderListRequest.setOrder_state("2");
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
//                            getView().getImaNoOrder().setVisibility(View.GONE);
                            LlyListNull.setVisibility(View.GONE);
                        }else{
                            LlyListNull.setVisibility(View.VISIBLE);
                            setAdapter();
//                            getView().getImaNoOrder().setVisibility(View.VISIBLE);
                        }


                    }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
                    }
                },HomePageFgPresenter.this::loginError);
    }
    private void setAdapter(){

        if(orderReceiveAdapter == null)
        orderReceiveAdapter = new OrderReceiveAdapter(mContext,orderBeanList);
        orderReceiveAdapter.setOrderList(orderBeanList);
        orderReceiveAdapter.setOnClick(this);

        getView().getLvOrderReceive().setonRefreshListener(new MyListView.OnRefreshListener() {

            @Override
            public void onRefresh() {

                GetOrderListRequest getOrderListRequest = new GetOrderListRequest();
                getOrderListRequest.setType("2");
                getOrderListRequest.setSelect_number("10");
                getOrderListRequest.setStart_number("0");
                getOrderListRequest.setOrder_state("2");
                ApiRetrofit.getInstance().getOrderList(getOrderListRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(getOrderListResponse -> {
                            String code = getOrderListResponse.getCode();
                            if("000".equals(code)){
                                orderBeanList =  getOrderListResponse.getData().getPaging_data();
                                orderReceiveAdapter.setOrderList(orderBeanList);
                                if(orderBeanList!=null && orderBeanList.size()>0){
                                    LlyListNull.setVisibility(View.GONE);
                                }else{
                                    LlyListNull.setVisibility(View.VISIBLE);
                                }
                                orderReceiveAdapter.notifyDataSetChanged();
                                getView().getLvOrderReceive().onRefreshComplete();

                            }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();


//                                getView().getLvOrderReceive().addFooterView(LlyListNull);
//                                orderReceiveAdapter.notifyDataSetChanged();
                                getView().getLvOrderReceive().onRefreshComplete();
                                Toast.makeText(mContext, getOrderListResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                            }
                        },HomePageFgPresenter.this::loginError);

            }
        });
        getView().getLvOrderReceive().setAdapter(orderReceiveAdapter);
        getView().getLvOrderReceive().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!ButtonUtils.isFastDoubleClick()) {
                    //写你相关操作即可
                    Intent intent = new Intent(mContext, OrderDetailActivity.class);
                    if(orderBeanList.size() == 0)
                        return;
                    if (position>0)
                        position = position - 1;
                    intent.putExtra("order_id",orderBeanList.get(position).getOrder_id());
                    intent.putExtra("order_state",orderBeanList.get(position).getOrder_state());
                    mContext.jumpToActivity(intent);
                }

//                mContext.jumpToActivityAndClearTop(OrderDetailActivity.class);
            }
        });
        if (orderReceiveAdapter != null)
            orderReceiveAdapter.notifyDataSetChanged();
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
