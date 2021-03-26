package com.pintu.neostore.model.fetch;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("user_data")
    @Expose
    private UserData userData;
    @SerializedName("product_categories")
    @Expose
    private List<ProductCategory> productCategories = null;
    @SerializedName("total_carts")
    @Expose
    private Integer totalCarts;
    @SerializedName("total_orders")
    @Expose
    private Integer totalOrders;

    /**
     * No args constructor for use in serialization
     *
     */
    public Data() {
    }

    /**
     *
     * @param productCategories
     * @param userData
     * @param totalOrders
     * @param totalCarts
     */
    public Data(UserData userData, List<ProductCategory> productCategories, Integer totalCarts, Integer totalOrders) {
        super();
        this.userData = userData;
        this.productCategories = productCategories;
        this.totalCarts = totalCarts;
        this.totalOrders = totalOrders;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    public Integer getTotalCarts() {
        return totalCarts;
    }

    public void setTotalCarts(Integer totalCarts) {
        this.totalCarts = totalCarts;
    }

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

}
