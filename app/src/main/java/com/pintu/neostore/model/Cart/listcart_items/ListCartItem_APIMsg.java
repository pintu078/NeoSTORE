package com.pintu.neostore.model.Cart.listcart_items;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListCartItem_APIMsg {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<ListCartItem_Data> data = null;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("total")
    @Expose
    private Integer total;

    /**
     * No args constructor for use in serialization
     *
     */
    public ListCartItem_APIMsg() {
    }

    /**
     *
     * @param total
     * @param data
     * @param count
     * @param status
     */
    public ListCartItem_APIMsg(Integer status, List<ListCartItem_Data> data, Integer count, Integer total) {
        super();
        this.status = status;
        this.data = data;
        this.count = count;
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ListCartItem_Data> getData() {
        return data;
    }

    public void setData(List<ListCartItem_Data> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
