package com.thl.newlocksystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.thl.newlocksystem.R;


public class OrderReceiveAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private OnListenerClick onClick;

    public void setOnClick(OnListenerClick onClick) {
        this.onClick = onClick;
    }

    public OrderReceiveAdapter(Context context) {
        // TODO Auto-generated constructor stub
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return 4;
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
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_order, null);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.btn_receiveOrder = convertView.findViewById(R.id.btn_receive_order);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.btn_receiveOrder.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                onClick.OrderReceive();
            }
        });
        return convertView;
    }
    class ViewHolder{
        TextView tv_time;
        Button btn_receiveOrder;
    }

    public interface OnListenerClick{
        void OrderReceive();
    }
}
