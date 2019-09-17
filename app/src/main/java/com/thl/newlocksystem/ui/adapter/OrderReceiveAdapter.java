package com.thl.newlocksystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thl.newlocksystem.R;

import androidx.recyclerview.widget.RecyclerView;

public class OrderReceiveAdapter extends BaseAdapter {
    private LayoutInflater inflater;

    public OrderReceiveAdapter(Context context) {
        // TODO Auto-generated constructor stub
//        if(items.size()>0){
//            items.clear();
//        }
//        items.addAll(list);
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
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
//        if(items.size()>0){
//            map = items.get(position);
//            if(map != null){
//            }
//        }
        return convertView;
    }
    class ViewHolder{
        TextView tv_time;
    }
}
