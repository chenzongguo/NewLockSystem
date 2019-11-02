package cn.njthl.HotelClean.model.Bean;

import java.util.List;

public class OrderRoomBean {
    public String getOrder_room_id() {
        return order_room_id;
    }

    public void setOrder_room_id(String order_room_id) {
        this.order_room_id = order_room_id;
    }

    public String getCorp_room_id() {
        return corp_room_id;
    }

    public void setCorp_room_id(String corp_room_id) {
        this.corp_room_id = corp_room_id;
    }

    public String getCorp_room_name() {
        return corp_room_name;
    }

    public void setCorp_room_name(String corp_room_name) {
        this.corp_room_name = corp_room_name;
    }

    public String getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(String room_type_id) {
        this.room_type_id = room_type_id;
    }

    public String getBed_num() {
        return bed_num;
    }

    public void setBed_num(String bed_num) {
        this.bed_num = bed_num;
    }

    public String getRoom_area_id() {
        return room_area_id;
    }

    public void setRoom_area_id(String room_area_id) {
        this.room_area_id = room_area_id;
    }

    public List<OrderGoodsBean> getOrder_goods_data() {
        return order_goods_data;
    }

    public void setOrder_goods_data(List<OrderGoodsBean> order_goods_data) {
        this.order_goods_data = order_goods_data;
    }

    public String getOrder_room_state() {
        return order_room_state;
    }

    public void setOrder_room_state(String order_room_state) {
        this.order_room_state = order_room_state;
    }

    public String getRoom_type_name() {
        return room_type_name;
    }

    public void setRoom_type_name(String room_type_name) {
        this.room_type_name = room_type_name;
    }

    public String getOrder_room_remark() {
        return order_room_remark;
    }

    public void setOrder_room_remark(String order_room_remark) {
        this.order_room_remark = order_room_remark;
    }

    public String getIs_overtime() {
        return is_overtime;
    }

    public void setIs_overtime(String is_overtime) {
        this.is_overtime = is_overtime;
    }

    public String getDispatch_time() {
        return dispatch_time;
    }

    public void setDispatch_time(String dispatch_time) {
        this.dispatch_time = dispatch_time;
    }

    public String getReceipt_time() {
        return receipt_time;
    }

    public void setReceipt_time(String receipt_time) {
        this.receipt_time = receipt_time;
    }

    public String getIs_clean() {
        return is_clean;
    }

    public void setIs_clean(String is_clean) {
        this.is_clean = is_clean;
    }

    public String getPic_id() {
        return pic_id;
    }

    public void setPic_id(String pic_id) {
        this.pic_id = pic_id;
    }

    public String getIs_rating() {
        return is_rating;
    }

    public void setIs_rating(String is_rating) {
        this.is_rating = is_rating;
    }

    public String getUser_rating_id() {
        return user_rating_id;
    }

    public void setUser_rating_id(String user_rating_id) {
        this.user_rating_id = user_rating_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String order_room_id ="";
    private String order_room_state = "";
    private String corp_room_id ="";
    private String corp_room_name ="";
    private String room_type_id ="";
    private String room_type_name = "";
    private String order_room_remark = "";
    private String is_overtime = "";
    private String dispatch_time = "";
    private String receipt_time = "";
    private String is_clean = "";
    private String pic_id = "";
    private String is_rating = "";
    private String user_rating_id = "";
    private String bed_num ="";
    private String room_area_id ="";
    private String user_id ="";
    private String name ="";
    private List<OrderGoodsBean> order_goods_data;

//    private List<OrderServicesBean> order_services_data;
//    private List<RatingBean> rating_data ;
}
