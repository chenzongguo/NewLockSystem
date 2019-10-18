package com.thl.newlocksystem.ui.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.thl.newlocksystem.R;
import com.thl.newlocksystem.R2;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.presenter.CompelteAtPresenter;
import com.thl.newlocksystem.ui.view.ICompelteAtView;

import butterknife.BindView;

public class CompelteActivity extends BaseActivity<ICompelteAtView, CompelteAtPresenter> implements ICompelteAtView {

    @BindView(R2.id.tvCorpName)
    TextView tvCorpName;


    @BindView(R2.id.imgImg1)
    ImageView img_photo1;

    @BindView(R2.id.imgImg2)
    ImageView img_photo2;

    @BindView(R2.id.imgImg3)
    ImageView img_photo3;

    @BindView(R2.id.imgImg4)
    ImageView img_photo4;

    @BindView(R2.id.imgImg5)
    ImageView img_photo5;

    @BindView(R2.id.imgImg6)
    ImageView img_photo6;

    @BindView(R2.id.imgImg7)
    ImageView img_photo7;

    @BindView(R2.id.imgImg8)
    ImageView img_photo8;


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getConversations();
    }

    @Override
    public void requestPermissionResult(boolean allowPermission) {

    }

    @Override
    protected CompelteAtPresenter createPresenter() {
        return new CompelteAtPresenter(this);

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.at_compelte;
    }

    @Override
    public ImageView getImageView1() {
        return img_photo1;
    }

    @Override
    public ImageView getImageView2() {
        return img_photo2;
    }

    @Override
    public ImageView getImageView3() {
        return img_photo3;
    }

    @Override
    public ImageView getImageView4() {
        return img_photo4;
    }

    @Override
    public ImageView getImageView5() {
        return img_photo5;
    }

    @Override
    public ImageView getImageView6() {
        return img_photo6;
    }

    @Override
    public ImageView getImageView7() {
        return img_photo7;
    }

    @Override
    public ImageView getImageView8() {
        return img_photo8;
    }
}
