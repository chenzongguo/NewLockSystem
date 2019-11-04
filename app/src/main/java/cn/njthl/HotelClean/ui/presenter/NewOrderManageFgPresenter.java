package cn.njthl.HotelClean.ui.presenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.api.ApiRetrofit;
import cn.njthl.HotelClean.model.Bean.OrderBean;
import cn.njthl.HotelClean.model.request.GetOrderListRequest;
import cn.njthl.HotelClean.ui.activity.OrderDetailActivity;
import cn.njthl.HotelClean.ui.adapter.OrderAllocationAdapter;
import cn.njthl.HotelClean.ui.adapter.OrderReceiveAdapter;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.base.BasePresenter;
import cn.njthl.HotelClean.ui.view.NewOrderManageFgView;
import cn.njthl.HotelClean.ui.view.OrderAllocationFgView;
import cn.njthl.HotelClean.util.ButtonUtils;
import cn.njthl.HotelClean.util.LogUtils;
import cn.njthl.HotelClean.util.UIUtils;
import cn.njthl.HotelClean.widget.MyListView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewOrderManageFgPresenter extends BasePresenter<NewOrderManageFgView> {
    private OrderAllocationAdapter orderAllocationAdapter;
    private List<OrderBean> orderBeanList;
    private LayoutInflater inflater;

    private LinearLayout LlyListNull;
    private String Order_state = "5";
    public NewOrderManageFgPresenter(BaseActivity context) {
        super(context);
    }


    public void getConversations() {
        loadData();

    }
    public void tableClick(String Order_state){
        this.Order_state = Order_state;
        getView().getTvNoArriveCorp().setTextColor(mContext.getResources().getColor(R.color.black));
        getView().getTvArriveCorp().setTextColor(mContext.getResources().getColor(R.color.black));
        getView().getTvCleanComplete().setTextColor(mContext.getResources().getColor(R.color.black));
        getView().getTvOrderComplete().setTextColor(mContext.getResources().getColor(R.color.black));
        switch (Order_state){
            case "5":
                getView().getTvNoArriveCorp().setTextColor(mContext.getResources().getColor(R.color.assist_green));
                break;
            case "6":
                getView().getTvArriveCorp().setTextColor(mContext.getResources().getColor(R.color.assist_green));
                break;
            case "9":
                getView().getTvCleanComplete().setTextColor(mContext.getResources().getColor(R.color.assist_green));
                break;
            case "7":
                getView().getTvOrderComplete().setTextColor(mContext.getResources().getColor(R.color.assist_green));
                break;
        }
        loadData();
    }
    private void  loadData(){
        if(LlyListNull==null){
            inflater = LayoutInflater.from(mContext);
            LlyListNull = (LinearLayout) inflater.inflate(R.layout.include_list_null, null);
            getView().getLvOrder().addFooterView(LlyListNull);
        }
        GetOrderListRequest getOrderListRequest = new GetOrderListRequest();
        getOrderListRequest.setType("2");
        getOrderListRequest.setSelect_number("10");
        getOrderListRequest.setStart_number("0");
        getOrderListRequest.setOrder_state(Order_state);
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
                },NewOrderManageFgPresenter.this::loginError);
    }
    private void setAdapter(){

        if(orderAllocationAdapter == null)
            orderAllocationAdapter = new OrderAllocationAdapter(mContext,orderBeanList);
        orderAllocationAdapter.setOrderList(orderBeanList);
        getView().getLvOrder().setAdapter(orderAllocationAdapter);
        getView().getLvOrder().setonRefreshListener(new MyListView.OnRefreshListener() {

            @Override
            public void onRefresh() {

                GetOrderListRequest getOrderListRequest = new GetOrderListRequest();
                getOrderListRequest.setType("2");
                getOrderListRequest.setSelect_number("10");
                getOrderListRequest.setStart_number("0");
                getOrderListRequest.setOrder_state(Order_state);
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
                                getView().getLvOrder().onRefreshComplete();

                            }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
                                getView().getLvOrder().onRefreshComplete();
                                Toast.makeText(mContext, getOrderListResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                            }
                        },NewOrderManageFgPresenter.this::loginError);

            }
        });
        getView().getLvOrder().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!ButtonUtils.isFastDoubleClick()) {
                    if(orderBeanList.size() == 0)
                        return;
                    if (position>0)
                        position = position - 1;
                    Intent intent = new Intent(mContext, OrderDetailActivity.class);
                    intent.putExtra("order_id",orderBeanList.get(position).getOrder_id());
                    intent.putExtra("order_state",orderBeanList.get(position).getOrder_state());
                    mContext.jumpToActivity(intent);
                }
            }
        });
        if (orderAllocationAdapter != null)
            orderAllocationAdapter.notifyDataSetChanged();
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
