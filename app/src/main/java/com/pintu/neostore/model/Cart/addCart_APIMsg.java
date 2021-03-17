package com.pintu.neostore.model.Cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class addCart_APIMsg {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Boolean data;
    @SerializedName("total_carts")
    @Expose
    private Integer totalCarts;
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
    public addCart_APIMsg() {
    }

    /**
     *
     * @param data
     * @param message
     * @param userMsg
     * @param totalCarts
     * @param status
     */
    public addCart_APIMsg(Integer status, Boolean data, Integer totalCarts, String message, String userMsg) {
        super();
        this.status = status;
        this.data = data;
        this.totalCarts = totalCarts;
        this.message = message;
        this.userMsg = userMsg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getData() {
        return data;
    }

    public void setData(Boolean data) {
        this.data = data;
    }

    public Integer getTotalCarts() {
        return totalCarts;
    }

    public void setTotalCarts(Integer totalCarts) {
        this.totalCarts = totalCarts;
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