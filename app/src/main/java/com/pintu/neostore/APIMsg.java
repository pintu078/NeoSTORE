package com.pintu.neostore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIMsg  {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Data data;
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
    public APIMsg() {
    }

    /**
     *
     * @param data
     * @param message
     * @param userMsg
     * @param status
     */
    public APIMsg(String status, Data data, String message, String userMsg) {
        super();
        this.status = status;
        this.data = data;
        this.message = message;
        this.userMsg = userMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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