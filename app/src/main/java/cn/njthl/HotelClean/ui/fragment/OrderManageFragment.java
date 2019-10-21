package cn.njthl.HotelClean.ui.fragment;

import android.widget.LinearLayout;
import android.widget.TextView;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.R2;
import cn.njthl.HotelClean.ui.activity.MainActivity;
import cn.njthl.HotelClean.ui.base.BaseFragment;
import cn.njthl.HotelClean.ui.presenter.OrderManageFgPresenter;
import cn.njthl.HotelClean.ui.view.OrderManageFgView;

import butterknife.BindView;

public class OrderManageFragment extends BaseFragment<OrderManageFgView, OrderManageFgPresenter> implements OrderManageFgView {

    @BindView(R2.id.llyOrderNoConfirm)
    LinearLayout llyOrderNoConfirm;

    @BindView(R2.id.llyNoArriveCorp)
    LinearLayout llyNoArriveCorp;

    @BindView(R2.id.llyArriveCrop)
    LinearLayout llyArriveCrop;

    @BindView(R2.id.llyOrderComplete)
    LinearLayout llyOrderComplete;

    @BindView(R2.id.llyCleanComplete)
    LinearLayout llyCleanComplete;

    @BindView(R2.id.tvOrderNoConfirmNum)
    TextView tvOrderNoConfirmNum;

    @BindView(R2.id.tvNoArriveCorpNum)
    TextView tvNoArriveCorpNum;

    @BindView(R2.id.tvArriveCropNum)
    TextView tvArriveCropNum;

    @BindView(R2.id.tvOrderCompleteNum)
    TextView tvOrderCompleteNum;

    @BindView(R2.id.tvCleanCompleteNum)
    TextView tvCleanCompleteNum;

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.getConversations();
    }

    @Override
    protected OrderManageFgPresenter createPresenter() {
        return new OrderManageFgPresenter((MainActivity) getActivity());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_order_manage;
    }


    @Override
    public LinearLayout getLlyOrderNoConfirm() {
        return llyOrderNoConfirm;
    }

    @Override
    public TextView getTvOrderNoConfirmNum() {
        return tvOrderNoConfirmNum;
    }

    @Override
    public TextView getTvNoArriveCorpNum() {
        return tvNoArriveCorpNum;
    }

    @Override
    public TextView getTvArriveCropNum() {
        return tvArriveCropNum;
    }

    @Override
    public TextView getTvCompleteNum() {
        return tvOrderCompleteNum;
    }

    @Override
    public TextView getTvCleanCompleteNum() {
        return tvCleanCompleteNum;
    }

    @Override
    public void initListener() {
        llyOrderNoConfirm.setOnClickListener(v -> mPresenter.toActivity());
        llyNoArriveCorp.setOnClickListener(v -> mPresenter.toActivity2("5"));
        llyArriveCrop.setOnClickListener(v -> mPresenter.toActivity2("6"));
        llyOrderComplete.setOnClickListener(v -> mPresenter.toActivity2("7"));
        llyCleanComplete.setOnClickListener(v -> mPresenter.toActivity2("9"));
    }
}