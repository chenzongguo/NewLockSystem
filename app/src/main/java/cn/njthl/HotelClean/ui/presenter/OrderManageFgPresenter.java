package cn.njthl.HotelClean.ui.presenter;

import android.content.Intent;
import android.widget.Toast;

import cn.njthl.HotelClean.api.ApiRetrofit;
import cn.njthl.HotelClean.model.request.GetOrderStateNumRequest;
import cn.njthl.HotelClean.ui.activity.NoArriveCorpActivity;
import cn.njthl.HotelClean.ui.activity.OrderNoConfirmActivity;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.base.BasePresenter;
import cn.njthl.HotelClean.ui.view.OrderManageFgView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderManageFgPresenter extends BasePresenter<OrderManageFgView> {
    public OrderManageFgPresenter(BaseActivity context) {
        super(context);
    }

    public void toActivity(){
        mContext.jumpToActivityAndClearTop(OrderNoConfirmActivity.class);
    }

    public void toActivity2(String order_state){
        Intent intent = new Intent(mContext, NoArriveCorpActivity.class);
        intent.putExtra("order_state",order_state);
        mContext.jumpToActivity(intent);
    }

    public void getConversations() {
        loadData();

    }
    private void loadData(){
        GetOrderStateNumRequest getOrderStateNumRequest = new GetOrderStateNumRequest();
        getOrderStateNumRequest.setType("2");
        getOrderStateNumRequest.setPartner_id("1");
        ApiRetrofit.getInstance().getOrderStateNum(getOrderStateNumRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getOrderStateNumResponse -> {
                    String code = getOrderStateNumResponse.getCode();
                    if("000".equals(code)){
                        if(getOrderStateNumResponse.getData()!=null&&getOrderStateNumResponse.getData().size()>0){
                            for (int i=0;i<getOrderStateNumResponse.getData().size();i++){
                                switch (getOrderStateNumResponse.getData().get(i).getOrder_state()){
                                    case "4":
                                        getView().getTvOrderNoConfirmNum().setText(getOrderStateNumResponse.getData().get(i).getNum());
                                        break;
                                    case "5":
                                        getView().getTvNoArriveCorpNum().setText(getOrderStateNumResponse.getData().get(i).getNum());
                                        break;
                                    case "6":
                                        getView().getTvArriveCropNum().setText(getOrderStateNumResponse.getData().get(i).getNum());
                                        break;
                                    case "7":
                                        getView().getTvCompleteNum().setText(getOrderStateNumResponse.getData().get(i).getNum());
                                        break;
                                    case"9":
                                        getView().getTvCleanCompleteNum().setText(getOrderStateNumResponse.getData().get(i).getNum());
                                        break;
                                }
                            }
                        }

                    }else{
                        Toast.makeText(mContext, getOrderStateNumResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}