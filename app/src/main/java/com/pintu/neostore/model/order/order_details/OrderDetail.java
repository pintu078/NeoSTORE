package com.pintu.neostore.model.order.order_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("prod_name")
    @Expose
    private String prodName;
    @SerializedName("prod_cat_name")
    @Expose
    private String prodCatName;
    @SerializedName("prod_image")
    @Expose
    private String prodImage;

    /**
     * No args constructor for use in serialization
     *
     */
    public OrderDetail() {
    }

    /**
     *
     * @param total
     * @param prodImage
     * @param quantity
     * @param productId
     * @param orderId
     * @param prodName
     * @param prodCatName
     * @param id
     */
    public OrderDetail(Integer id, Integer orderId, Integer productId, Integer quantity, Integer total, String prodName, String prodCatName, String prodImage) {
        super();
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.total = total;
        this.prodName = prodName;
        this.prodCatName = prodCatName;
        this.prodImage = prodImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdCatName() {
        return prodCatName;
    }

    public void setProdCatName(String prodCatName) {
        this.prodCatName = prodCatName;
    }

    public String getProdImage() {
        return prodImage;
    }

    public void setProdImage(String prodImage) {
        this.prodImage = prodImage;
    }

}