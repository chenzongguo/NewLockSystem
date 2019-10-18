package com.thl.newlocksystem.ui.presenter;

import android.graphics.Bitmap;
import android.view.View;

import com.thl.newlocksystem.api.ApiRetrofit;
import com.thl.newlocksystem.model.request.GetCleanPicRequest;
import com.thl.newlocksystem.model.request.GetOrderListRequest;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;
import com.thl.newlocksystem.ui.view.ICompelteAtView;
import com.thl.newlocksystem.util.ImageUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CompelteAtPresenter extends BasePresenter<ICompelteAtView> {
    public CompelteAtPresenter(BaseActivity context) {
        super(context);
    }

    public void getConversations() {
        loadData();

    }
    private void  loadData(){
        String pic_id = mContext.getIntent().getStringExtra("pic_id");
        GetCleanPicRequest getCleanPicRequest = new GetCleanPicRequest();
        getCleanPicRequest.setType("1");
        getCleanPicRequest.setPic_id(pic_id);
        ApiRetrofit.getInstance().getCleanPic(getCleanPicRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getCleanPicResponse -> {
                    String code = getCleanPicResponse.getCode();
                    if("000".equals(code)){
                        Bitmap bitmap = ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_one());
                        getView().getImageView1().setImageBitmap(bitmap);
                        getView().getImageView2().setImageBitmap(ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_two()));
                        getView().getImageView3().setImageBitmap(ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_three()));
                        getView().getImageView4().setImageBitmap(ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_four()));
                        getView().getImageView5().setImageBitmap(ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_five()));
                        getView().getImageView6().setImageBitmap(ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_six()));
                        getView().getImageView7().setImageBitmap(ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_seven()));
                        getView().getImageView8().setImageBitmap(ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_eight()));
//                        orderBeanList =  getOrderListResponse.getData().getPaging_data();
////                        showUpdateDialog(checkUpdateResponse.getData().getDownload_address());
////                        registerReceiver();
//                        if(orderBeanList!=null && orderBeanList.size()>0){
//                            setAdapter();
//                            getView().getImaNoOrder().setVisibility(View.GONE);
//                        }else{
//                            getView().getImaNoOrder().setVisibility(View.VISIBLE);
//                        }


                    }else{
//                        Toast.makeText(getContext(), getTokenResponse.getStatue(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
