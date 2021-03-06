package cn.njthl.HotelClean.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.model.Bean.OrderBean;

import java.util.List;

public class OrderAllocationAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private OrderReceiveAdapter.OnListenerClick onClick;
    private List<OrderBean> OrderBeanlist;

    public void setOnClick(OrderReceiveAdapter.OnListenerClick onClick) {
        this.onClick = onClick;
    }

    public OrderAllocationAdapter(Context context, List<OrderBean> orderBeanlist) {
        // TODO Auto-generated constructor stub
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.OrderBeanlist = orderBeanlist;
    }
    public void setOrderList(List<OrderBean> orderBeanlist){
        this.OrderBeanlist = orderBeanlist;
    }
    @Override
    public int getCount() {
        if(OrderBeanlist!=null)
            return OrderBeanlist.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderAllocationAdapter.ViewHolder holder;
        if(convertView == null){
            holder = new OrderAllocationAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.item_order, null);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            holder.tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
            holder.tvPaymentPrice = (TextView) convertView.findViewById(R.id.tvPaymentPrice);
            holder.tvCorpName = (TextView) convertView.findViewById(R.id.tvCorpName);
            holder.tvContacts = (TextView) convertView.findViewById(R.id.tvContacts);
            holder.tvContactPhone = (TextView) convertView.findViewById(R.id.tvContactPhone);
//            holder.btn_receiveOrder = convertView.findViewById(R.id.btn_receive_order);
            convertView.setTag(holder);
        }else{
            holder = (OrderAllocationAdapter.ViewHolder) convertView.getTag();
        }
//        holder.btn_receiveOrder.setOnClickListener(new android.view.View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                onClick.OrderReceive();
//            }
//        });
        holder.tvTime.setText(OrderBeanlist.get(position).getDoor_time());
        holder.tvAddress.setText(OrderBeanlist.get(position).getCorp_addr());
        if(OrderBeanlist.get(position).getOrder_state().equals("3")){
            holder.tvPaymentPrice.setText("待派单");
        }else if(OrderBeanlist.get(position).getOrder_state().equals("4")){
            holder.tvPaymentPrice.setText("已派单");
        }else{
            holder.tvPaymentPrice.setText("¥"+OrderBeanlist.get(position).getPayment_price());
        }

        holder.tvCorpName.setText(OrderBeanlist.get(position).getCorp_name());
        holder.tvContacts.setText(OrderBeanlist.get(position).getContacts());
        holder.tvContactPhone.setText(OrderBeanlist.get(position).getContact_phone());
        return convertView;
    }
    class ViewHolder{
        TextView tvTime,tvAddress,tvPaymentPrice,tvCorpName,tvContacts,tvContactPhone;
//        Button btn_receiveOrder;
    }

    public interface OnListenerClick{
        void OrderReceive();
    }
    View tv_time;
    }

