package com.pintu.neostore.model.Cart.listcart_items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListCartItem_Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("product")
    @Expose
    private ListCartItem_Image product;

    /**
     * No args constructor for use in serialization
     *
     */
    public ListCartItem_Data() {
    }

    /**
     *
     * @param product
     * @param quantity
     * @param productId
     * @param id
     */
    public ListCartItem_Data(Integer id, Integer productId, Integer quantity, ListCartItem_Image product) {
        super();
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ListCartItem_Image getProduct() {
        return product;
    }

    public void setProduct(ListCartItem_Image product) {
        this.product = product;
    }

}