package com.thl.newlocksystem.ui.presenter;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.thl.newlocksystem.api.ApiRetrofit;
import com.thl.newlocksystem.model.request.GetOrderListRequest;
import com.thl.newlocksystem.model.request.GetUserListRequest;
import com.thl.newlocksystem.model.response.GetUserListResponse;
import com.thl.newlocksystem.ui.activity.OrderDetailActivity;
import com.thl.newlocksystem.ui.adapter.OrderReceiveAdapter;
import com.thl.newlocksystem.ui.adapter.UserListAdapter;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;
import com.thl.newlocksystem.ui.view.IUserListAtView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserListAtPresenter  extends BasePresenter<IUserListAtView> implements UserListAdapter.OnCheckedChangeClickListener {
    private GetUserListResponse getUserListResponse;
    private UserListAdapter userListAdapter;
    private int checkposition = -1;

    public UserListAtPresenter(BaseActivity context) {
        super(context);
    }

    public void getConversations() {
        getUserList();
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
//                            items = new String[getUserListResponse.getData().size()];
//                            for (int i = 0; i < items.length; i++){
//                                items[i] = getUserListResponse.getData().get(i).getName();
//                            }
                            setAdapter();
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
    private void setAdapter(){

        if(userListAdapter == null)
            userListAdapter = new UserListAdapter(mContext,getUserListResponse.getData());
        userListAdapter.setCheckBoxOnClickListener(this);
//        orderReceiveAdapter.setOnClick(this);
        getView().getLvOrderNoConfirm().setAdapter(userListAdapter);
        getView().getLvOrderNoConfirm().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,"长按点击事件",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        getView().getLvOrderNoConfirm().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(mContext,"listview点击事件",Toast.LENGTH_SHORT).show();
                userListAdapter.setCheckPosition(position);
                checkposition = position;
                getView().getTvCleanerName().setText("当前指派人员为"+getUserListResponse.getData().get(position).getName());
                userListAdapter.notifyDataSetChanged();
//                Intent intent = new Intent(mContext, OrderDetailActivity.class);
//                intent.putExtra("order_id",orderBeanList.get(position).getOrder_id());
//                intent.putExtra("order_state",orderBeanList.get(position).getOrder_state());
//                mContext.jumpToActivity(intent);
//                mContext.jumpToActivityAndClearTop(OrderDetailActivity.class);
            }
        });
    }

    @Override
    public void onCheckBoxClick(int index, boolean isChecked) {
        userListAdapter.notifyDataSetChanged();
        getView().getTvCleanerName().setText("当前指派人员为"+getUserListResponse.getData().get(index).getName());
    }

    public void OrderAllocation(){
        Intent intent = new Intent();
        intent.putExtra("user_id",getUserListResponse.getData().get(checkposition).getUser_id());
        mContext.setResult(1,intent);
        mContext.finish();
    }
}
