package com.pintu.neostore.model.ProductDetailed_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailed_APIMsg {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private ProductDetailed_Data data;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProductDetailed_APIMsg() {
    }

    /**
     *
     * @param data
     * @param status
     */
    public ProductDetailed_APIMsg(Integer status,ProductDetailed_Data data) {
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

    public ProductDetailed_Data getData() {
        return data;
    }

    public void setData(ProductDetailed_Data data) {
        this.data = data;
    }

}

