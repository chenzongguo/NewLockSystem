package cn.njthl.HotelClean.model.request;

public class GetOrderStateNumRequest {
    private String type;//查询标识(1全部2商户3微信用户)
    private String partner_id;//商户必传
    private String wx_user_id;//

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String getWx_user_id() {
        return wx_user_id;
    }

    public void setWx_user_id(String wx_user_id) {
        this.wx_user_id = wx_user_id;
    }
}
