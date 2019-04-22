package com.thl.newlocksystem.db.model;

public class Unlock_Business {
    String PPBS_Code = "PPBS_Code";  //记录编码
    String Cus_Code = "Cus_Code";	//客户编码
    String Company_Code = "Company_Code";//无
    String PPBS_Object = "PPBS_Object";//无
    String PPBS_Area = "PPBS_Area";//开锁区域
    String PPBS_AreaCode = "PPBS_AreaCode";//开锁区域编码
    String PPBS_Addr = "PPBS_Addr";//开锁地址
    String PPBS_Resaon = "PPBS_Resaon";//开锁原因
    String PPBS_ResaonCode = "PPBS_ResaonCode";//开锁原因编码
    String PPBS_LockType = "PPBS_LockType";//锁类型(字典)
    String PPBS_LockTypeCode = "PPBS_LockTypeCode";//锁类型编码
    String PPBS_Photo_Code = "PPBS_Photo_Code";//锁具标的物特征[外键,表h_Photo]
    String PPBS_Unlock_Person = "PPBS_Unlock_Person";//开锁人（身份证号）
    String PPBS_110 = "PPBS_110";//业务来源
    String PPBS_110Code = "PPBS_110Code";//业务来源
    String PPBS_Bill_Code = "PPBS_Bill_Code";//开锁客户联编号(无)
    String PPBS_Unlock_DateTime = "PPBS_Unlock_DateTime";//开锁时间
    String PPBS_Unlock_Charge = "PPBS_Unlock_Charge";//开锁服务费
    String PPBS_Material_Cost = "PPBS_Material_Cost";//开锁材料费
    String PPBS_Total_Cost = "PPBS_Total_Cost";//费用合计
    String PPBS_Reg_Person = "PPBS_Reg_Person";//登记人
    String PPBS_Reg_DateTime = "PPBS_Reg_DateTime";//登记时间
    String PPBS_RegCompany_Code = "PPBS_RegCompany_Code";//机构编码
    String PPBS_Finish = "PPBS_Finish";//业务完成标记
    String PPBS_UpLoaded = "PPBS_UpLoaded";//上传标记
    String PPBS_UpLoaded_DateTime = "PPBS_UpLoaded_DateTime";//上传时间
}
