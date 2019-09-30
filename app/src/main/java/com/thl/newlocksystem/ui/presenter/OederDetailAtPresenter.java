package com.thl.newlocksystem.ui.presenter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.thl.newlocksystem.api.ApiRetrofit;
import com.thl.newlocksystem.model.Bean.OrderRoomBean;
import com.thl.newlocksystem.model.request.GetOrderListRequest;
import com.thl.newlocksystem.model.request.GetOrderRequest;
import com.thl.newlocksystem.model.request.ParnterReceiptRequest;
import com.thl.newlocksystem.model.request.UpdateOrderRoomStateRequest;
import com.thl.newlocksystem.model.response.GetOrderResponse;
import com.thl.newlocksystem.ui.activity.OrderDetailActivity;
import com.thl.newlocksystem.ui.adapter.OrderRoomAdapter;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;
import com.thl.newlocksystem.ui.view.IOrderDetailAtView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OederDetailAtPresenter extends BasePresenter<IOrderDetailAtView> {

    private OrderRoomAdapter orderRoomAdapter;
    private List<OrderRoomBean> orderRoomBeanList;
    private GetOrderResponse getOrderResponse;
    private Boolean OrderAllocation = false;
    private String order_id;
    private String order_state;
    public OederDetailAtPresenter(BaseActivity context) {
        super(context);
    }

    public void getConversations() {
        loadData();

    }
    private void  loadData(){
        order_id = mContext.getIntent().getStringExtra("order_id");
        order_state = mContext.getIntent().getStringExtra("order_state");
        if(order_state.equals("3")){
            getView().getBtnParnterReceipt().setText("去派单");
            OrderAllocation = true;
        }
        GetOrderRequest getOrderRequest = new GetOrderRequest();
        getOrderRequest.setType("1");
        getOrderRequest.setOrder_id(order_id);
        ApiRetrofit.getInstance().getOrder(getOrderRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getOrderResponse -> {
                    String code = getOrderResponse.getCode();
                    if("000".equals(code)){
                        this.getOrderResponse = getOrderResponse;
                        orderRoomBeanList =  getOrderResponse.getData().getCorp_room_data();
//                        showUpdateDialog(checkUpdateResponse.getData().getDownload_address());
//                        registerReceiver();
                        setAdapter();
                        initView(getOrderResponse);
                    }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void setAdapter(){

        if(orderRoomAdapter == null)
            orderRoomAdapter = new OrderRoomAdapter(mContext,orderRoomBeanList);
//        orderRoomAdapter.setOnClick(this);
        getView().getLvRoomInfo().setAdapter(orderRoomAdapter);
        getView().getLvRoomInfo().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,"单个房间长按派单",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        getView().getLvRoomInfo().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,"listview点击事件",Toast.LENGTH_SHORT).show();
                mContext.jumpToActivityAndClearTask(OrderDetailActivity.class);
            }
        });
    }

    private void initView(GetOrderResponse getOrderResponse){
        getView().getTvCorpName().setText(getOrderResponse.getData().getCorp_name());
        getView().getTvAddress().setText(getOrderResponse.getData().getCorp_addr());
    }

    public void ParnterReceipt(){
        if (!OrderAllocation){
            ParnterReceiptRequest parnterReceiptRequest = new ParnterReceiptRequest();
            parnterReceiptRequest.setPartner_id("1");
            parnterReceiptRequest.setPartner_name("测试");
            parnterReceiptRequest.setOrder_id(getOrderResponse.getData().getOrder_id());
            ApiRetrofit.getInstance().parnterReceipt(parnterReceiptRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getBaseResponse -> {
                        String code = getBaseResponse.getCode();
                        if("000".equals(code)){
//                        orderRoomBeanList =  getOrderResponse.getData().getCorp_room_data();
////                        showUpdateDialog(checkUpdateResponse.getData().getDownload_address());
////                        registerReceiver();
//                        setAdapter();
//                        initView(getOrderResponse);
                            getView().getBtnParnterReceipt().setText("去派单");
                            OrderAllocation = true;
                            Toast.makeText(mContext, "接单成功", Toast.LENGTH_SHORT).show();
                        }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(mContext, getBaseResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }else{

            UpdateOrderRoomStateRequest updateOrderRoomStateRequest = new UpdateOrderRoomStateRequest();
            updateOrderRoomStateRequest.setType("1");
            updateOrderRoomStateRequest.setUser_id("5");
            updateOrderRoomStateRequest.setOrder_id(getOrderResponse.getData().getOrder_id());
            updateOrderRoomStateRequest.setOrder_room_id(orderRoomBeanList.get(0).getOrder_room_id());
            ApiRetrofit.getInstance().updateOrderRoomState(updateOrderRoomStateRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getBaseResponse -> {
                        String code = getBaseResponse.getCode();
                        if("000".equals(code)){
//                        orderRoomBeanList =  getOrderResponse.getData().getCorp_room_data();
////                        showUpdateDialog(checkUpdateResponse.getData().getDownload_address());
////                        registerReceiver();
//                        setAdapter();
//                        initView(getOrderResponse);

                            Toast.makeText(mContext, "派单成功", Toast.LENGTH_SHORT).show();
                        }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(mContext, getBaseResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }
}
