package cn.njthl.HotelClean.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import cn.njthl.HotelClean.R;
import cn.njthl.HotelClean.model.Bean.UserBean;

import java.util.List;

public class UserListAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {
    private LayoutInflater inflater;
    private List<UserBean> userBeanList;
    private int checkPosition = -1;
    private OnCheckedChangeClickListener dbListener;
    private boolean isShowCheckbox = false ;

    public UserListAdapter(Context context, List<UserBean> userBeanList) {
        // TODO Auto-generated constructor stub
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.userBeanList = userBeanList;
    }

    public void setIsShowCheckbox(boolean isShowCheckbox){
        this.isShowCheckbox = isShowCheckbox;
    }

    @Override
    public int getCount() {
        return userBeanList.size();
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
        UserListAdapter.ViewHolder holder;
        if(convertView == null){
            holder = new UserListAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.item_user, null);
            holder.tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
            holder.checkbox = (CheckBox)convertView.findViewById(R.id.choose);
            convertView.setTag(holder);
        }else{
            holder = (UserListAdapter.ViewHolder) convertView.getTag();
        }
//        holder.checkbox.setOnCheckedChangeListener(this);
        holder.checkbox.setTag(position);
        if(isShowCheckbox){
            holder.checkbox.setVisibility(View.VISIBLE);
        }
        if(position==checkPosition){
            holder.checkbox.setChecked(true);
        }else{
            holder.checkbox.setChecked(false);
        }
        holder.tvUserName.setText(userBeanList.get(position).getName());
        return convertView;
    }

    public void setCheckPosition(int position){
        checkPosition = position;
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        checkPosition = (Integer)buttonView.getTag();
        dbListener.onCheckBoxClick(checkPosition, isChecked);
    }

    public void setCheckBoxOnClickListener(OnCheckedChangeClickListener listener) {
        dbListener = listener;
    }

    public interface OnCheckedChangeClickListener {
        void onCheckBoxClick(int index, boolean isChecked);
    }

    class ViewHolder{
        TextView tvUserName;
        CheckBox checkbox;
    }
}
