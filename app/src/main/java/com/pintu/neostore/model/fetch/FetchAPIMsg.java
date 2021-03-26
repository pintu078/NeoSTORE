package com.pintu.neostore.model.fetch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchAPIMsg {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Data data;

    /**
     * No args constructor for use in serialization
     *
     */
    public FetchAPIMsg() {
    }

    /**
     *
     * @param data
     * @param status
     */
    public FetchAPIMsg(Integer status, Data data) {
        super();
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}