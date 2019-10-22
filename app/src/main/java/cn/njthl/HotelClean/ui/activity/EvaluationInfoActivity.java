package cn.njthl.HotelClean.ui.activity;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.R2;
import cn.njthl.HotelClean.ui.base.BaseActivity;
import cn.njthl.HotelClean.ui.presenter.EvaluationInfoAtPresenter;
import cn.njthl.HotelClean.ui.view.IEvaluationInfoView;

import butterknife.BindView;

public class EvaluationInfoActivity extends BaseActivity<IEvaluationInfoView, EvaluationInfoAtPresenter> implements IEvaluationInfoView{

    @BindView(R2.id.cleanliness_star)
    RatingBar cleanliness_star;

    @BindView(R2.id.order_speed_star)
    RatingBar order_speed_star;

    @BindView(R2.id.tvRatingExplain)
    TextView tvRatingExplain;

    @BindView(R2.id.imgImg1)
    ImageView img_photo1;

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getConversations();
    }

    @Override
    public void requestPermissionResult(boolean allowPermission) {

    }

    @Override
    protected EvaluationInfoAtPresenter createPresenter() {
        return new EvaluationInfoAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.at_evaluation_info;
    }

    @Override
    public RatingBar getcleanliness_star() {
        return cleanliness_star;
    }

    @Override
    public RatingBar getorder_speed_star() {
        return order_speed_star;
    }

    @Override
    public TextView getTvRatingExplain() {
        return tvRatingExplain;
    }

    @Override
    public ImageView getImageView1() {
        return img_photo1;
    }
}
