package com.pintu.neostore.model.order.Order_List;

import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class order_list_APIMsg {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_msg")
    @Expose
    private String userMsg;

    /**
     * No args constructor for use in serialization
     *
     */
    public order_list_APIMsg() {
    }

    /**
     *
     * @param data
     * @param message
     * @param userMsg
     * @param status
     */
    public order_list_APIMsg(Integer status, List<Datum> data, String message, String userMsg) {
        super();
        this.status = status;
        this.data = data;
        this.message = message;
        this.userMsg = userMsg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg;
    }

}