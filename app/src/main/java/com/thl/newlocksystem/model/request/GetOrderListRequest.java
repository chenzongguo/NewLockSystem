package com.thl.newlocksystem.model.request;

public class GetOrderListRequest {
    private String type;//查询标识(1全部2商户3微信用户)
    private String partner_id;//商户id
    private String wx_user_id;//微信用户id
    private String order_state;//订单状态
    private String create_start_time;//下单开始时间
    private String create_stop_time;//下单结束时间
//    private String current_page;//
    private String start_number;//开始位置
    private String select_number;//查询条数

}
