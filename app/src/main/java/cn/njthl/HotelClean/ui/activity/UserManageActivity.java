package cn.njthl.HotelClean.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.R2;
import cn.njthl.HotelClean.api.ApiRetrofit;
import cn.njthl.HotelClean.model.request.GetUserListRequest;
import cn.njthl.HotelClean.model.response.GetUserListResponse;
import cn.njthl.HotelClean.ui.adapter.UserListAdapter;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.base.BasePresenter;
import cn.njthl.HotelClean.util.ButtonUtils;
import cn.njthl.HotelClean.util.LogUtils;
import cn.njthl.HotelClean.util.UIUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserManageActivity extends BaseActivity {
    @BindView(R2.id.lv_user)
    ListView lv_user;
    private GetUserListResponse getUserListResponse;
    private UserListAdapter userListAdapter;


    @Override
    public void initView() {
        super.initView();
        StatusBarUtil.setColor(this, UIUtils.getColor(R.color.assist_green1), 10);
        mToolbarTitle.setText("员工列表");
        getUserList();
    }

    @Override
    public void requestPermissionResult(boolean allowPermission) {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.at_user_manager;
    }

    private void getUserList(){
        GetUserListRequest getUserListRequest = new GetUserListRequest();
        getUserListRequest.setType("1");
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
                            Toast.makeText(this, "没有可指派的保洁员", Toast.LENGTH_SHORT).show();
                        }
//                        orderRoomBeanList =  getOrderResponse.getData().getCorp_room_data();
////                        showUpdateDialog(checkUpdateResponse.getData().getDownload_address());
////                        registerReceiver();
//                        setAdapter();
//                        initView(getOrderResponse);
                    }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(this, getUserListResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void setAdapter(){

        if(userListAdapter == null)
            userListAdapter = new UserListAdapter(this,getUserListResponse.getData());
//        orderReceiveAdapter.setOnClick(this);
        lv_user.setAdapter(userListAdapter);
        lv_user.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(this,"listview点击事件",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(this, OrderDetailActivity.class);
//                intent.putExtra("order_id",orderBeanList.get(position).getOrder_id());
//                intent.putExtra("order_state",orderBeanList.get(position).getOrder_state());
//                this.jumpToActivity(intent);
//                this.jumpToActivityAndClearTop(OrderDetailActivity.class);
                if (!ButtonUtils.isFastDoubleClick()) {
                    //写你相关操作即可
                    Intent intent = new Intent(UserManageActivity.this, UserInfoActivity.class);
                    intent.putExtra("user_id",getUserListResponse.getData().get(position).getUser_id());
                    jumpToActivity(intent);
                }
            }
        });
    }


}
