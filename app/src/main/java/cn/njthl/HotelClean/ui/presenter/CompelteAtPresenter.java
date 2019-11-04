package cn.njthl.HotelClean.ui.presenter;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.IThumbViewInfo;

import java.util.ArrayList;

import cn.njthl.HotelClean.api.ApiRetrofit;
import cn.njthl.HotelClean.model.Bean.UserViewInfo;
import cn.njthl.HotelClean.model.request.GetCleanPicRequest;
import cn.njthl.HotelClean.ui.activity.ImageLookActivity;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.base.BasePresenter;
import cn.njthl.HotelClean.ui.view.ICompelteAtView;
import cn.njthl.HotelClean.util.ButtonUtils;
import cn.njthl.HotelClean.util.ImageUtils;

import cn.njthl.HotelClean.util.LogUtils;
import cn.njthl.HotelClean.util.UIUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static cn.njthl.HotelClean.util.ImageUtils.HOME_PATH;

public class CompelteAtPresenter extends BasePresenter<ICompelteAtView> {
    public CompelteAtPresenter(BaseActivity context) {
        super(context);
    }

    public void getConversations() {
        loadData();

    }
    public void showImage(int position){
        if (!ButtonUtils.isFastDoubleClick()) {
            //打开预览界面
            ArrayList<UserViewInfo> mThumbViewInfoList = new ArrayList<>();
            UserViewInfo item;
            mThumbViewInfoList.clear();
            for (int i = 0;i < 8; i++) {
                Rect bounds = new Rect();
                //new ThumbViewInfo(图片地址);
                item=new UserViewInfo(HOME_PATH+mContext.getIntent().getStringExtra("pic_id")+(i+1)+".jpg");
                mThumbViewInfoList.add(item);
            }
            GPreviewBuilder.from(mContext)
                    //是否使用自定义预览界面，当然8.0之后因为配置问题，必须要使用
                    .to(ImageLookActivity.class)
                    .setData(mThumbViewInfoList)
                    .setCurrentIndex(position)
                    .setSingleFling(true)
                    .setDuration(300)
                    .setDrag(false)
                    .setType(GPreviewBuilder.IndicatorType.Number)
                    // 小圆点
//  .setType(GPreviewBuilder.IndicatorType.Dot)
                    .start();//启动
        }

    }
    private void  loadData(){
        mContext.showWaitingDialog("正在加载图片");
        String pic_id = mContext.getIntent().getStringExtra("pic_id");
        GetCleanPicRequest getCleanPicRequest = new GetCleanPicRequest();
        getCleanPicRequest.setType("1");
        getCleanPicRequest.setPic_id(pic_id);
        ApiRetrofit.getInstance().getCleanPic(getCleanPicRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getCleanPicResponse -> {
                    mContext.hideWaitingDialog();
                    String code = getCleanPicResponse.getCode();
                    if("000".equals(code)){
                        Bitmap bitmap = ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_one());
                        getView().getImageView1().setImageBitmap(bitmap);
                        ImageUtils.saveBmp(bitmap,pic_id+1+".jpg");
                        getView().getImageView2().setImageBitmap(ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_two()));
                        ImageUtils.saveBmp(bitmap,pic_id+2+".jpg");
                        getView().getImageView3().setImageBitmap(ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_three()));
                        ImageUtils.saveBmp(bitmap,pic_id+3+".jpg");
                        getView().getImageView4().setImageBitmap(ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_four()));
                        ImageUtils.saveBmp(bitmap,pic_id+4+".jpg");
                        getView().getImageView5().setImageBitmap(ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_five()));
                        ImageUtils.saveBmp(bitmap,pic_id+5+".jpg");
                        getView().getImageView6().setImageBitmap(ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_six()));
                        ImageUtils.saveBmp(bitmap,pic_id+6+".jpg");
                        getView().getImageView7().setImageBitmap(ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_seven()));
                        ImageUtils.saveBmp(bitmap,pic_id+7+".jpg");
                        getView().getImageView8().setImageBitmap(ImageUtils.base64ToBitmap(getCleanPicResponse.getData().getPic_eight()));
                        ImageUtils.saveBmp(bitmap,pic_id+8+".jpg");
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
                },this::loginError);
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
