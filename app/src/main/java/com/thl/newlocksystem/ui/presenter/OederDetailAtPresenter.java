package com.thl.newlocksystem.ui.presenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.thl.newlocksystem.R;
import com.thl.newlocksystem.api.ApiRetrofit;
import com.thl.newlocksystem.model.Bean.OrderRoomBean;
import com.thl.newlocksystem.model.request.GetOrderListRequest;
import com.thl.newlocksystem.model.request.GetOrderRequest;
import com.thl.newlocksystem.model.request.GetUserListRequest;
import com.thl.newlocksystem.model.request.ParnterReceiptRequest;
import com.thl.newlocksystem.model.request.UpdateOrderRoomStateRequest;
import com.thl.newlocksystem.model.response.GetOrderResponse;
import com.thl.newlocksystem.model.response.GetUserListResponse;
import com.thl.newlocksystem.ui.activity.OrderDetailActivity;
import com.thl.newlocksystem.ui.adapter.OrderRoomAdapter;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;
import com.thl.newlocksystem.ui.dialog.UserDialog;
import com.thl.newlocksystem.ui.view.IOrderDetailAtView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OederDetailAtPresenter extends BasePresenter<IOrderDetailAtView> {

    private OrderRoomAdapter orderRoomAdapter;
    private List<OrderRoomBean> orderRoomBeanList;
    private GetOrderResponse getOrderResponse;
    private GetUserListResponse getUserListResponse;
    private Boolean OrderAllocation = false;
    private String order_id;
    private String order_state;
    private String items[];
    private int i = 0;
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


            getUserList();
//            dialogChoice();
//            UserDialog userDialog = new UserDialog(mContext, View.inflate(mContext, R.layout.dialog_user, null),R.style.MyDialog);
//            userDialog.setTitle("请选择需要指派的保洁员");
//            userDialog.show();
        }

    }

    private void OrderAllocation(){
            UpdateOrderRoomStateRequest updateOrderRoomStateRequest = new UpdateOrderRoomStateRequest();
            updateOrderRoomStateRequest.setType("1");
            updateOrderRoomStateRequest.setUser_id(getUserListResponse.getData().get(i).getUser_id());
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
                            getView().getBtnParnterReceipt().setText("订单待确认");
                            getView().getBtnParnterReceipt().setEnabled(false);
                            Toast.makeText(mContext, "派单成功", Toast.LENGTH_SHORT).show();
                        }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(mContext, getBaseResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
    }

    private void getUserList(){
        GetUserListRequest getUserListRequest = new GetUserListRequest();
        getUserListRequest.setType("2");
//        getUserListRequest.setUser_id("5");
        getUserListRequest.setPartner_id("1");//商户id
            ApiRetrofit.getInstance().getUserList(getUserListRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getUserListResponse -> {
                        String code = getUserListResponse.getCode();
                        if("000".equals(code)){
                            this.getUserListResponse = getUserListResponse;
                            if(getUserListResponse.getData()!=null&&getUserListResponse.getData().size()>0){
                                items = new String[getUserListResponse.getData().size()];
                                for (int i = 0; i < items.length; i++){
                                    items[i] = getUserListResponse.getData().get(i).getName();
                                }
                                dialogChoice();
                            }else{
                                Toast.makeText(mContext, "没有可指派的保洁员", Toast.LENGTH_SHORT).show();
                            }
//                        orderRoomBeanList =  getOrderResponse.getData().getCorp_room_data();
////                        showUpdateDialog(checkUpdateResponse.getData().getDownload_address());
////                        registerReceiver();
//                        setAdapter();
//                        initView(getOrderResponse);
                        }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(mContext, getUserListResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
    }
    private void dialogChoice() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext,3);
        builder.setTitle("保洁员列表");
//        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(false);
        builder.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        i = which;
                        Toast.makeText(mContext, items[which],
                                Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OrderAllocation();
                dialog.dismiss();
//                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT)
//                        .show();
            }
        });
        builder.create().show();
        i = 0;
    }
}
