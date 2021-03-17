package com.pintu.neostore.model.Cart.listcart_items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListCartItem_Image {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("product_category")
    @Expose
    private String productCategory;
    @SerializedName("product_images")
    @Expose
    private String productImages;
    @SerializedName("sub_total")
    @Expose
    private Integer subTotal;

    /**
     * No args constructor for use in serialization
     *
     */
    public ListCartItem_Image() {
    }

    /**
     *
     * @param productImages
     * @param cost
     * @param name
     * @param id
     * @param subTotal
     * @param productCategory
     */
    public ListCartItem_Image(Integer id, String name, Integer cost, String productCategory, String productImages, Integer subTotal) {
        super();
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.productCategory = productCategory;
        this.productImages = productImages;
        this.subTotal = subTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages;
    }

    public Integer getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }

}