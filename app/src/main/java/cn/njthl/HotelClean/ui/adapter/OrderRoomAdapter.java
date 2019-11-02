package cn.njthl.HotelClean.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.model.Bean.ChooseOrderRoomBean;
import cn.njthl.HotelClean.model.Bean.OrderRoomBean;
import cn.njthl.HotelClean.ui.activity.CompelteActivity;
import cn.njthl.HotelClean.ui.activity.EvaluationInfoActivity;

import java.util.List;

public class OrderRoomAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private OrderReceiveAdapter.OnListenerClick onClick;
    private List<OrderRoomBean> OrderRoomBeanlist;
    List<ChooseOrderRoomBean> chooseOrderRoomBeanList;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    private Context mContext;
    private String order_state;

    public void setOnClick(OrderReceiveAdapter.OnListenerClick onClick) {
        this.onClick = onClick;
    }

    public OrderRoomAdapter(Context context,String order_state, List<OrderRoomBean> orderRoomBeanlist, List<ChooseOrderRoomBean> chooseOrderRoomBeanList
    , CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        // TODO Auto-generated constructor stub
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.OrderRoomBeanlist = orderRoomBeanlist;
        this.chooseOrderRoomBeanList = chooseOrderRoomBeanList;
        this.onCheckedChangeListener = onCheckedChangeListener;
        mContext = context;
        this.order_state = order_state;
    }
    @Override
    public int getCount() {
        if(OrderRoomBeanlist!=null)
            return OrderRoomBeanlist.size();
        else
            return 0;
    }

    public void setOrder_state(){
        order_state = "3";
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
            holder.RlyCleanerName = (RelativeLayout) convertView.findViewById(R.id.RlyCleanerName);
            holder.tvCleanerName = (TextView)convertView.findViewById(R.id.tvCleanerName);
            holder.checkbox = (CheckBox)convertView.findViewById(R.id.choose);
            convertView.setTag(holder);
        }else{
            holder = (OrderRoomAdapter.ViewHolder) convertView.getTag();
        }
        if(OrderRoomBeanlist.get(position).getIs_clean().equals("1")){
            holder.btnCompelte.setVisibility(View.VISIBLE);
        }
        holder.btnCompelte.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                onClick.OrderReceive();
                if(OrderRoomBeanlist.get(position).getIs_clean().equals("1")){
                    Intent intent = new Intent(mContext, CompelteActivity.class);
                    intent.putExtra("pic_id",OrderRoomBeanlist.get(position).getPic_id());
                    mContext.startActivity(intent);
                }else{
                    Toast.makeText(mContext,"房间正在打扫中 ",Toast.LENGTH_SHORT).show();
                }

            }
        });

        if(OrderRoomBeanlist.get(position).getIs_rating().equals("1")){
            holder.btnEvaluation.setVisibility(View.VISIBLE);
        }

        if(OrderRoomBeanlist.get(position).getUser_id()!=null&&!OrderRoomBeanlist.get(position).getUser_id().equals("")){
            holder.RlyCleanerName.setVisibility(View.VISIBLE);
            holder.tvCleanerName.setText(OrderRoomBeanlist.get(position).getName());
        }
        holder.btnEvaluation.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                onClick.OrderReceive();
                if(OrderRoomBeanlist.get(position).getIs_rating().equals("1")){
                    Intent intent = new Intent(mContext, EvaluationInfoActivity.class);
                    intent.putExtra("user_rating_id",OrderRoomBeanlist.get(position).getUser_rating_id());
                    mContext.startActivity(intent);
                }else{
                    Toast.makeText(mContext,"订单未完成无评价信息",Toast.LENGTH_SHORT).show();
                }

            }
        });

        holder.tv_room_name.setText(OrderRoomBeanlist.get(position).getCorp_room_name());
        holder.tv_room_type.setText(OrderRoomBeanlist.get(position).getRoom_type_name());
//        holder.tv_bed_num.setText(OrderRoomBeanlist.get(position).getBed_num());
//        holder.tv_room_area.setText(OrderRoomBeanlist.get(position).getRoom_area_id());
        if(order_state.equals("3")||order_state.equals("4")){
            holder.checkbox.setVisibility(View.VISIBLE);
        }
        if(OrderRoomBeanlist.get(position).getOrder_room_state().equals("1")){
            holder.tvRoomState.setText("待分配");
        }else if(OrderRoomBeanlist.get(position).getOrder_room_state().equals("2")){
            holder.tvRoomState.setText("待确认");
        }else if(OrderRoomBeanlist.get(position).getOrder_room_state().equals("3")){
            holder.tvRoomState.setText("待上门");
        }else if(OrderRoomBeanlist.get(position).getOrder_room_state().equals("4")){
            holder.tvRoomState.setText("已到店");
        }else if(OrderRoomBeanlist.get(position).getOrder_room_state().equals("7")){
            holder.tvRoomState.setText("已打扫");
        }
        else if(OrderRoomBeanlist.get(position).getOrder_room_state().equals("5")){
            holder.tvRoomState.setText("已完成");
        }
        holder.checkbox.setOnCheckedChangeListener(onCheckedChangeListener);
        holder.checkbox.setTag(position);
        holder.checkbox.setChecked(chooseOrderRoomBeanList.get(position).isIs_choose());
        return convertView;
    }
    class ViewHolder{
        TextView tv_room_name,tv_room_type,tvCleanerName,tv_room_area,tvRoomState;
        Button btnCompelte,btnEvaluation;
        RelativeLayout RlyCleanerName;
        CheckBox checkbox;
    }

    public interface OnListenerClick{
        void OrderReceive();
    }
}