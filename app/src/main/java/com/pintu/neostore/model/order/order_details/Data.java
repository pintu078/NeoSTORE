package com.pintu.neostore.model.order.order_details;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("order_details")
    @Expose
    private List<OrderDetail> orderDetails = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Data() {
    }

    /**
     *
     * @param orderDetails
     * @param cost
     * @param address
     * @param id
     */
    public Data(Integer id, Integer cost, String address, List<OrderDetail> orderDetails) {
        super();
        this.id = id;
        this.cost = cost;
        this.address = address;
        this.orderDetails = orderDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
