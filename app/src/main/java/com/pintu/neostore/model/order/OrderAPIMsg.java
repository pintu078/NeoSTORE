package com.pintu.neostore.model.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderAPIMsg {

    @SerializedName("status")
    @Expose
    private Integer status;
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
    public OrderAPIMsg() {
    }

    /**
     *
     * @param message
     * @param userMsg
     * @param status
     */
    public OrderAPIMsg(Integer status, String message, String userMsg) {
        super();
        this.status = status;
        this.message = message;
        this.userMsg = userMsg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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