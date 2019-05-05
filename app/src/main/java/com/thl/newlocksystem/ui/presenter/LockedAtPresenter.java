package com.thl.newlocksystem.ui.presenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.thl.newlocksystem.app.SPConstant;
import com.thl.newlocksystem.db.model.Customer;
import com.thl.newlocksystem.db.model.Photo;
import com.thl.newlocksystem.db.model.UnlockBusiness;
import com.thl.newlocksystem.ui.base.BaseActivity;
import com.thl.newlocksystem.ui.base.BasePresenter;
import com.thl.newlocksystem.ui.view.ILockedAtView;
import com.thl.newlocksystem.util.SPUtils;
import com.thl.newlocksystem.util.TimeUtils;
import com.thl.newlocksystem.util.UIUtils;

public class LockedAtPresenter extends BasePresenter<ILockedAtView> {
    String PPBS_AreaCode;
    String PPBS_ResaonCode;
    String PPBS_110Code;
    String PPBS_LockTypeCode;
    public LockedAtPresenter(BaseActivity context) {
        super(context);
    }
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    tvArea.setText("  "+items[msg.arg1].toString());
                    if(msg.arg1 < 8){
                        PPBS_AreaCode = "32010"+msg.arg1;
                    }else if(msg.arg1 < 12){
                        PPBS_AreaCode = "3201"+(msg.arg1 +3 );
                    }else if(msg.arg1 < 16){
                        PPBS_AreaCode = "3201"+(msg.arg1 +4 );
                    }else if(msg.arg1 < 21){
                        PPBS_AreaCode = "3201"+(msg.arg1 +5 );
                    }else if(msg.arg1 < 12){
                        PPBS_AreaCode = "3201"+(msg.arg1 +10 );
                    }
                    break;
                case 1:
                    tvLockedReson.setText("  "+items1[msg.arg1].toString());
                    if(msg.arg1 == 5){
                        PPBS_ResaonCode="99";
                    }else{
                        PPBS_ResaonCode = "0"+(msg.arg1+1);
                    }

                    break;
                case 2:
                    tvSourceOfBus.setText("  "+items2[msg.arg1].toString());
                    if(msg.arg1 == 3){
                        PPBS_110Code = "99";
                    }else{
                        PPBS_110Code = "0"+(msg.arg1+1);
                    }
                    break;
                case 3:
                    tvLockType.setText("  "+items3[msg.arg1].toString());
                    if(msg.arg1 == 6){
                        PPBS_LockTypeCode = "99";
                    }else{
                        PPBS_LockTypeCode = "0"+(msg.arg1+1);
                    }
                    break;
                case 4:
                    edtNation.setText("  "+items4[msg.arg1].toString());
                    if(msg.arg1 > 8){
                        custrbean.setCus_NationCode(""+(msg.arg1+1));
                    }else{
                        custrbean.setCus_NationCode("0"+(msg.arg1+1));
                    }
                    break;
                case 5:
                    edtSex.setText("  "+items5[msg.arg1].toString());
                    break;
            }
        }
    };

    private void showGoalDialog(final int i,final String[] items, String str) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(str);
        builder.setCancelable(false);
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Message msg = new Message();
                msg.what = i;
                msg.arg1 = which;
                handler.sendMessage(msg);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void save(){

    };
    private boolean validate(){
        if(TextUtils.isEmpty(getView().GetEtList().get(0).getText().toString())){
            UIUtils.showToast("姓名不能为空");
            return false;
        }else if(TextUtils.isEmpty(getView().GetEtList().get(1).getText().toString())){
            UIUtils.showToast("性别不能为空");
            return false;
        }else if(TextUtils.isEmpty(getView().GetEtList().get(2).getText().toString())){
            UIUtils.showToast("民族不能为空");
            return false;
        }else if(TextUtils.isEmpty(getView().GetEtList().get(4).getText().toString())){
            UIUtils.showToast("身份证号不能为空");
            return false;
        }else if(TextUtils.isEmpty(getView().GetEtList().get(3).getText().toString())){
            UIUtils.showToast("户籍地址不能为空");
            return false;
        }else if(TextUtils.isEmpty(getView().GetEtList().get(5).getText().toString())){
            UIUtils.showToast("联系电话不能为空");
            return false;
        }else if(TextUtils.isEmpty(getView().GetEtList().get(6).getText().toString())){
            UIUtils.showToast("详细地址不能为空");
            return false;
        }else if(TextUtils.isEmpty(getView().GetTvList().get(0).getText().toString())){
            UIUtils.showToast("开锁区域不能为空");
            return false;
        }else if(TextUtils.isEmpty(getView().GetTvList().get(1).getText().toString())){
            UIUtils.showToast("开锁原因不能为空");
            return false;
        }else if(TextUtils.isEmpty(getView().GetTvList().get(2).getText().toString())){
            UIUtils.showToast("业务来源不能为空");
            return false;
        }else if(TextUtils.isEmpty(getView().GetTvList().get(3).getText().toString())){
            UIUtils.showToast("锁具类型不能为空");
            return false;
        }else if(bitmap_locktype == null){
            UIUtils.showToast("请拍摄锁具照片");
            return false;
        }else{
            return true;
        }
    }
    private void setBean(){
        Customer custrbean= new Customer();
        UnlockBusiness busbean = new UnlockBusiness();
        Photo  custrphotobean = new Photo();
        Photo  busphotobean = new Photo();


        String Cus_Code = SPUtils.getInstance(mContext).getString(SPConstant.Enterprise_Code,"") +
                TimeUtils.getDateTime2()+"49"+SPUtils.getInstance(mContext).getString(SPConstant.IMEI,"");
        String PPBS_Code = SPUtils.getInstance(mContext).getString(SPConstant.Enterprise_Code,"") +
                TimeUtils.getDateTime2()+"50"+SPUtils.getInstance(mContext).getString(SPConstant.IMEI,"");

        custrbean.setCus_Code(Cus_Code);
        custrbean.setCus_Name(getView().GetEtList().get(0).getText().toString());
        custrbean.setCus_Sex(getView().GetEtList().get(1).getText().toString());
        if("男".equals(getView().GetEtList().get(1).getText().toString())){
            custrbean.setCus_SexCode("1");
        }else{
            custrbean.setCus_SexCode("2");
        }
        custrbean.setCus_Nation(getView().GetEtList().get(2).getText().toString());
        custrbean.setCus_Birthday(edtBirth.getText().toString());
        custrbean.setCus_Number(getView().GetEtList().get(4).getText().toString());
        custrbean.setCus_Addr(getView().GetEtList().get(3).getText().toString());
        custrbean.setCus_Tel(getView().GetEtList().get(5).getText().toString());

        custrbean.setCus_Photo_id(Cus_Code);
        custrbean.setCus_Finish("1");
        custrbean.setCus_UpLoaded("0");
        custrbean.setCus_Uploaded_DateTime("");
        if(is_exist){
            UnLockInfo.updateCustomerInfo(Cus_Code, custrbean);
        }else{
            while(!UnLockInfo.saveCustomerInfo(custrbean));
        }
        busbean.setPPBS_Code(PPBS_Code);
        busbean.setCus_Code(Cus_Code);
        busbean.setPPBS_Area(tvArea.getText().toString().trim());
        busbean.setPPBS_Addr(getView().GetEtList().get(6).getText().toString());
        busbean.setPPBS_Resaon(tvLockedReson.getText().toString().trim());
        busbean.setPPBS_110(tvSourceOfBus.getText().toString().trim());
        busbean.setPPBS_LockType(tvLockType.getText().toString().trim());
        busbean.setPPBS_Photo_Code(PPBS_Code);
        busbean.setPPBS_Unlock_Charge("" + int_serve);
        busbean.setPPBS_Material_Cost("" + int_material);
        busbean.setPPBS_Finish("1");
        busbean.setPPBS_Unlock_Person(SharedPreferencesHelper.get(LockedActivity.this,
                SPConstant.SYS_USER, SPConstant.User_Number));
        busbean.setPPBS_Total_Cost(edtTotal.getText().toString());
        while(!UnLockInfo.saveBusinessInfo(busbean));

        custrphotobean.setPhoto_Code(Cus_Code);
        custrphotobean.setPhoto_Content(bitmap_idcard);
        custrphotobean.setPhoto_InputTime(CommonUtils.getDateTime5());
        custrphotobean.setPhoto_Type("1");
        custrphotobean.setPhoto_Desc(Cus_Code);
        if(is_exist){
            UnLockInfo.updatePhotoInfo(Cus_Code,custrphotobean);
        }else{
            while(!UnLockInfo.savePhotoInfo(custrphotobean));
        }
        busphotobean.setPhoto_Code(PPBS_Code);
        busphotobean.setPhoto_Content(bitmap_locktype);
        busphotobean.setPhoto_InputTime(CommonUtils.getDateTime5());
        busphotobean.setPhoto_Type("2");
        busphotobean.setPhoto_Desc(PPBS_Code);
        while(!UnLockInfo.savePhotoInfo(busphotobean));
        proDialog.dismiss();
        UIUtils.showToast("登记成功");
    }
    private void clear(){
        getView().GetEtList().get(0).setText("");
        getView().GetEtList().get(1).setText("");
        getView().GetEtList().get(2).setText("");
        getView().GetEtList().get(3).setText("");
        getView().GetEtList().get(4).setText("");
        getView().GetEtList().get(5).setText("");
        getView().GetEtList().get(6).setText("");
        getView().GetTvList().get(0).setText("");
        getView().GetTvList().get(1).setText("");
        getView().GetTvList().get(2).setText("");
        getView().GetTvList().get(3).setText("");
    }
}
