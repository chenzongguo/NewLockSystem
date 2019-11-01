package cn.njthl.HotelClean.model.Bean;

public class ChooseOrderRoomBean {
    private String order_room_id ="";
    private String order_room_state = "";
    private boolean is_choose = false;

    public String getOrder_room_id() {
        return order_room_id;
    }

    public void setOrder_room_id(String order_room_id) {
        this.order_room_id = order_room_id;
    }

    public String getOrder_room_state() {
        return order_room_state;
    }

    public void setOrder_room_state(String order_room_state) {
        this.order_room_state = order_room_state;
    }

    public boolean isIs_choose() {
        return is_choose;
    }

    public void setIs_choose(boolean is_choose) {
        this.is_choose = is_choose;
    }
}
