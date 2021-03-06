package cn.njthl.HotelClean.ui.presenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;

import cn.njthl.HotelClean.api.ApiRetrofit;
import cn.njthl.HotelClean.model.Bean.ChooseOrderRoomBean;
import cn.njthl.HotelClean.model.Bean.OrderRoomBean;
import cn.njthl.HotelClean.model.request.GetOrderRequest;
import cn.njthl.HotelClean.model.request.GetUserListRequest;
import cn.njthl.HotelClean.model.request.ParnterReceiptRequest;
import cn.njthl.HotelClean.model.request.UpdateOrderRoomStateRequest;
import cn.njthl.HotelClean.model.response.GetOrderResponse;
import cn.njthl.HotelClean.model.response.GetUserListResponse;
import cn.njthl.HotelClean.ui.activity.UserListActivity;
import cn.njthl.HotelClean.ui.adapter.OrderRoomAdapter;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.base.BasePresenter;
import cn.njthl.HotelClean.ui.view.IOrderDetailAtView;
import cn.njthl.HotelClean.util.LogUtils;
import cn.njthl.HotelClean.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import cn.njthl.HotelClean.util.UIUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OederDetailAtPresenter extends BasePresenter<IOrderDetailAtView> implements CompoundButton.OnCheckedChangeListener {

    private OrderRoomAdapter orderRoomAdapter;
    private List<OrderRoomBean> orderRoomBeanList;
    private List<ChooseOrderRoomBean> chooseOrderRoomBeanList;
    private GetOrderResponse getOrderResponse;
    private GetUserListResponse getUserListResponse;
    private Boolean OrderAllocation = false;
    private boolean allChoose = false;
    private String order_id;
    private String order_state;
    private String items[];
    private int i = 0;
    private int ChooseNum;
    public OederDetailAtPresenter(BaseActivity context) {
        super(context);
    }

    public void getConversations() {
        loadData();

    }
    private void  loadData(){
        mContext.showWaitingDialog("正在加载");
        order_id = mContext.getIntent().getStringExtra("order_id");
        order_state = mContext.getIntent().getStringExtra("order_state");
        if(order_state.equals("2")){
            getView().getTvOrderState().setText("待接单");
            getView().getBtnParnterReceipt().setText("确认接单");
        }
        if(order_state.equals("3")){
            getView().getAllChoose().setVisibility(View.VISIBLE);
            getView().getTvOrderState().setText("待派单");
            getView().getBtnParnterReceipt().setText("去派单");
            OrderAllocation = true;
        }
        if(order_state.equals("4")){
            getView().getAllChoose().setVisibility(View.VISIBLE);
            getView().getTvOrderState().setText("已派单");
            getView().getBtnParnterReceipt().setText("重新派单");
            OrderAllocation = true;
        }else if(order_state.equals("5")){
            getView().getTvOrderState().setText("保洁员");
            getView().getBtnParnterReceipt().setText("订单待完成");
            getView().getBtnParnterReceipt().setEnabled(false);
        }
        else if(order_state.equals("6")){
            getView().getTvOrderState().setText("保洁员已上门");
            getView().getBtnParnterReceipt().setText("订单待完成");
            getView().getBtnParnterReceipt().setEnabled(false);
        }
        else if(order_state.equals("9")){
            getView().getTvOrderState().setText("房间已打扫");
            getView().getBtnParnterReceipt().setText("订单待完成");
            getView().getBtnParnterReceipt().setEnabled(false);
        }
        else if(order_state.equals("7")){
            getView().getTvOrderState().setText("订单完成");
            getView().getBtnParnterReceipt().setText("订单已完成");
            getView().getBtnParnterReceipt().setEnabled(false);
        }

        GetOrderRequest getOrderRequest = new GetOrderRequest();
        getOrderRequest.setType("2");
        getOrderRequest.setOrder_id(order_id);
        ApiRetrofit.getInstance().getOrder(getOrderRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getOrderResponse -> {
                    String code = getOrderResponse.getCode();
                    mContext.hideWaitingDialog();
                    if("000".equals(code)){
                        this.getOrderResponse = getOrderResponse;
                        orderRoomBeanList =  getOrderResponse.getData().getCorp_room_data();
                        chooseOrderRoomBeanList = new ArrayList<ChooseOrderRoomBean>();
                        for (int i =0;i<orderRoomBeanList.size();i++){
                            ChooseOrderRoomBean chooseOrderRoomBean= new ChooseOrderRoomBean();
                            chooseOrderRoomBeanList.add(chooseOrderRoomBean);
                        }
//                        showUpdateDialog(checkUpdateResponse.getData().getDownload_address());
//                        registerReceiver();
                        setAdapter();
                        initView(getOrderResponse);
                    }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
                    }
                },this::loginError);
    }
    private void setAdapter(){

        if(orderRoomAdapter == null)
            orderRoomAdapter = new OrderRoomAdapter(mContext,order_state,this);
//        orderRoomAdapter.setOnClick(this);
        orderRoomAdapter.setlist(orderRoomBeanList,chooseOrderRoomBeanList);
        getView().getLvRoomInfo().setAdapter(orderRoomAdapter);
        SystemUtil.setListViewHeightBasedOnChildren(getView().getLvRoomInfo());
        orderRoomAdapter.notifyDataSetChanged();
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
//                mContext.jumpToActivityAndClearTask(OrderDetailActivity.class);
            }
        });
    }

    private void initView(GetOrderResponse getOrderResponse){
        getView().getTvCorpName().setText(getOrderResponse.getData().getCorp_name());
        getView().getTvAddress().setText(getOrderResponse.getData().getCorp_addr());
        getView().getTvOrderId().setText(getOrderResponse.getData().getOrder_id());
        getView().getTvCreateTime().setText(getOrderResponse.getData().getCreate_time());
        getView().getTvTime().setText(getOrderResponse.getData().getDoor_time());
        getView().getTvContacts().setText(getOrderResponse.getData().getContacts());
        getView().getTvContactPhone().setText(getOrderResponse.getData().getContact_phone());
        getView().getTvPaymentPrice().setText(getOrderResponse.getData().getPayment_price());
    }

    public void ParnterReceipt(){

        if (!OrderAllocation){
            if(orderRoomBeanList==null){
                UIUtils.showToast("没有获取到订单信息，无法接单");
                return ;
            }
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
                            getView().getAllChoose().setVisibility(View.VISIBLE);
                            getView().getBtnParnterReceipt().setText("去派单");
                            orderRoomAdapter.setOrder_state();
                            orderRoomAdapter.notifyDataSetChanged();
                            OrderAllocation = true;
                            Toast.makeText(mContext, "接单成功", Toast.LENGTH_SHORT).show();
                        }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(mContext, getBaseResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                        }
                    },this::loginError);
        }else{
            if(!IsChoose()){
                Toast.makeText(mContext, "请选择需要分配的房间", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(mContext, UserListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mContext.startActivityForResult(intent, 100);
//            mContext.jumpToActivityAndClearTop(UserListActivity.class);

//            getUserList();
//            dialogChoice();
//            UserDialog userDialog = new UserDialog(mContext, View.inflate(mContext, R.layout.dialog_user, null),R.style.MyDialog);
//            userDialog.setTitle("请选择需要指派的保洁员");
//            userDialog.show();
        }

    }

    public void OrderAllocation(String userId){
        for (int i =0;i<orderRoomBeanList.size();i++){
            if(!chooseOrderRoomBeanList.get(i).isIs_choose())
                continue;
            UpdateOrderRoomStateRequest updateOrderRoomStateRequest = new UpdateOrderRoomStateRequest();
            updateOrderRoomStateRequest.setType("1");
            updateOrderRoomStateRequest.setUser_id(userId);
            updateOrderRoomStateRequest.setOrder_id(getOrderResponse.getData().getOrder_id());
            updateOrderRoomStateRequest.setOrder_room_id(orderRoomBeanList.get(i).getOrder_room_id());
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
//                            getView().getBtnParnterReceipt().setText("订单待确认");
//                            getView().getBtnParnterReceipt().setEnabled(false);
//                            getView().getTvOrderState().setText("已派单");
//                            getView().getBtnParnterReceipt().setText("重新派单");
                            getConversations();
                        }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(mContext, getBaseResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                        }
                    },this::loginError);
        }

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
                    },this::loginError);
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
//                OrderAllocation();
                dialog.dismiss();
//                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT)
//                        .show();
            }
        });
        builder.create().show();
        i = 0;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int p = (Integer)buttonView.getTag();
        chooseOrderRoomBeanList.get(p).setIs_choose(isChecked);
        ChooseNum = 0;
        for (i=0;i<chooseOrderRoomBeanList.size();i++){
            if(chooseOrderRoomBeanList.get(i).isIs_choose()){
                ChooseNum = ChooseNum+1;
            }
        }
        if(ChooseNum ==chooseOrderRoomBeanList.size()){
            allChoose = true;
            getView().getAllChoose().setChecked(true);
        }else{
            allChoose = false;
            getView().getAllChoose().setChecked(false);
        }
    }

    public void AllChoose(){
        if(orderRoomBeanList==null){
            UIUtils.showToast("没有获取到订单信息，无法选择");
            return ;
        }

        if(getView().getAllChoose().isChecked()){
//            getView().getAllChoose().setChecked(true);
            for (int i =0;i<orderRoomBeanList.size();i++){
                chooseOrderRoomBeanList.get(i).setIs_choose(true);
            }
        }else{
            for (int i =0;i<orderRoomBeanList.size();i++){
                chooseOrderRoomBeanList.get(i).setIs_choose(false);
            }
        }
        orderRoomAdapter.notifyDataSetChanged();
    }

    private boolean IsChoose(){
        if(orderRoomBeanList==null){
            UIUtils.showToast("没有获取到订单信息，无法派单");
            return false;
        }
        for (int i =0;i<orderRoomBeanList.size();i++) {
            if (chooseOrderRoomBeanList.get(i).isIs_choose())
                return true;
        }
        return false;
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
