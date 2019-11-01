package cn.njthl.HotelClean.ui.view;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public interface IOrderDetailAtView {
    ListView getLvRoomInfo();
    TextView getTvAddress();
    TextView getTvCorpName();
    TextView getTvOrderId();
    TextView getTvCreateTime();
    TextView getTvTime();
    TextView getTvContacts();
    TextView getTvContactPhone();
    TextView getTvPaymentPrice();
    TextView getTvOrderState();
    CheckBox getAllChoose();
    Button getBtnParnterReceipt();
}
