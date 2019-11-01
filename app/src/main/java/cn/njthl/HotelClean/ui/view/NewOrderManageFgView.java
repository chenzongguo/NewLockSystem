package cn.njthl.HotelClean.ui.view;

import android.widget.TextView;

import cn.njthl.HotelClean.widget.MyListView;

public interface NewOrderManageFgView {
    MyListView getLvOrder();
    TextView getTvNoArriveCorp();
    TextView getTvArriveCorp();
    TextView getTvCleanComplete();
    TextView getTvOrderComplete();
}
