package com.pintu.neostore.model.Rate_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rate_APIMsg {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Rate_Data data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_msg")
    @Expose
    private String userMsg;

    /**
     * No args constructor for use in serialization
     */
    public Rate_APIMsg() {
    }

    /**
     * @param data
     * @param message
     * @param userMsg
     * @param status
     */
    public Rate_APIMsg(Integer status, Rate_Data data, String message, String userMsg) {
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

    public Rate_Data getData() {
        return data;
    }

    public void setData(Rate_Data data) {
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
