package com.thl.newlocksystem.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.thl.newlocksystem.R;
import com.thl.newlocksystem.model.Bean.OrderBean;
import com.thl.newlocksystem.model.Bean.OrderRoomBean;
import com.thl.newlocksystem.ui.activity.CompelteActivity;
import com.thl.newlocksystem.ui.activity.OrderDetailActivity;

import java.util.List;

public class OrderRoomAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private OrderReceiveAdapter.OnListenerClick onClick;
    private List<OrderRoomBean> OrderRoomBeanlist;
    private Context mContext;

    public void setOnClick(OrderReceiveAdapter.OnListenerClick onClick) {
        this.onClick = onClick;
    }

    public OrderRoomAdapter(Context context, List<OrderRoomBean> orderRoomBeanlist) {
        // TODO Auto-generated constructor stub
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.OrderRoomBeanlist = orderRoomBeanlist;
        mContext = context;
    }
    @Override
    public int getCount() {
        if(OrderRoomBeanlist!=null)
            return OrderRoomBeanlist.size();
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
        OrderRoomAdapter.ViewHolder holder;
        if(convertView == null){
            holder = new OrderRoomAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.item_order_room, null);
            holder.tv_room_name = (TextView) convertView.findViewById(R.id.tv_room_name);
            holder.tv_room_type = (TextView) convertView.findViewById(R.id.tv_room_type);
//            holder.tv_bed_num = (TextView) convertView.findViewById(R.id.tv_bed_num);
            holder.btnCompelte = (Button) convertView.findViewById(R.id.btnCompelte);
            holder.btnEvaluation = (Button) convertView.findViewById(R.id.btnEvaluation);
            holder.tvRoomState = (TextView)convertView.findViewById(R.id.tvRoomState);
            convertView.setTag(holder);
        }else{
            holder = (OrderRoomAdapter.ViewHolder) convertView.getTag();
        }
        holder.btnCompelte.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                onClick.OrderReceive();
                Intent intent = new Intent(mContext, CompelteActivity.class);
                intent.putExtra("pic_id",OrderRoomBeanlist.get(position).getPic_id());
                mContext.startActivity(intent);
            }
        });

        holder.btnEvaluation.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                onClick.OrderReceive();
                Intent intent = new Intent(mContext, CompelteActivity.class);
                intent.putExtra("user_rating_id",OrderRoomBeanlist.get(position).getUser_rating_id());
                mContext.startActivity(intent);
            }
        });
        holder.tv_room_name.setText(OrderRoomBeanlist.get(position).getCorp_room_name());
        holder.tv_room_type.setText(OrderRoomBeanlist.get(position).getRoom_type_name());
//        holder.tv_bed_num.setText(OrderRoomBeanlist.get(position).getBed_num());
//        holder.tv_room_area.setText(OrderRoomBeanlist.get(position).getRoom_area_id());

        if(OrderRoomBeanlist.get(position).getOrder_room_state().equals("2")){
            holder.tvRoomState.setText("待确认");
        }else if(OrderRoomBeanlist.get(position).getOrder_room_state().equals("3")){
            holder.tvRoomState.setText("待上门");
        }else if(OrderRoomBeanlist.get(position).getOrder_room_state().equals("4")){
            holder.tvRoomState.setText("已到店");
        }else if(OrderRoomBeanlist.get(position).getOrder_room_state().equals("7")){
            holder.tvRoomState.setText("已完成");
        }else{
            holder.tvRoomState.setText("未分配");
        }
        return convertView;
    }
    class ViewHolder{
        TextView tv_room_name,tv_room_type,tv_bed_num,tv_room_area,tvRoomState;
        Button btnCompelte,btnEvaluation;
    }

    public interface OnListenerClick{
        void OrderReceive();
    }
}