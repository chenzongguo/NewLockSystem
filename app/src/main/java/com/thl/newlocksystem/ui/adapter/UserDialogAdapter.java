package com.thl.newlocksystem.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.thl.newlocksystem.R;

public class UserDialogAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {
    private LayoutInflater inflater;

    public UserDialogAdapter(Context context) {
        // TODO Auto-generated constructor stub
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        this.OrderBeanlist = orderBeanlist;
    }

    @Override
    public int getCount() {
        return 7;
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
        UserDialogAdapter.ViewHolder holder;
        if(convertView == null){
            holder = new UserDialogAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.item_user, null);
            holder.tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
            holder.checkbox = (CheckBox)convertView.findViewById(R.id.choose);
            convertView.setTag(holder);
        }else{
            holder = (UserDialogAdapter.ViewHolder) convertView.getTag();
        }
        holder.checkbox.setOnCheckedChangeListener(this);
        return convertView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int p = (Integer)buttonView.getTag();
//        dbListener.onCheckBoxClick(p, isChecked);
    }

    class ViewHolder{
        TextView tvUserName;
        CheckBox checkbox;
    }
}
