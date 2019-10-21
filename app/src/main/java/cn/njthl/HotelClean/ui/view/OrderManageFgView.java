package cn.njthl.HotelClean.ui.view;

import android.widget.LinearLayout;
import android.widget.TextView;

public interface OrderManageFgView {
    LinearLayout getLlyOrderNoConfirm();
    TextView getTvOrderNoConfirmNum();
    TextView getTvNoArriveCorpNum();
    TextView getTvArriveCropNum();
    TextView getTvCompleteNum();
    TextView getTvCleanCompleteNum();

}
