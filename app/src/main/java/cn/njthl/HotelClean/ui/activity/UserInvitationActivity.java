package cn.njthl.HotelClean.ui.activity;

import android.widget.ImageView;
import android.widget.ListView;

import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.R2;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.base.BasePresenter;
import cn.njthl.HotelClean.util.LogUtils;
import cn.njthl.HotelClean.util.UIUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserInvitationActivity extends BaseActivity {

    @BindView(R2.id.imgQRcode)
    ImageView imgQRcode;


    @Override
    public void initView() {
        super.initView();
        setQRCode("这是一串二维码里的信息");
        StatusBarUtil.setColor(this, UIUtils.getColor(R.color.assist_green1), 10);
        mToolbarTitle.setText("邀请码");
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
        return R.layout.at_user_invitation;
    }

    private void setQRCode(String content) {
        Observable.just(QRCodeEncoder.syncEncodeQRCode(content, UIUtils.dip2Px(300)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmap -> imgQRcode.setImageBitmap(bitmap), this::loadQRCardError);
    }

    private void loadQRCardError(Throwable throwable) {
        LogUtils.sf(throwable.getLocalizedMessage());
    }
}
