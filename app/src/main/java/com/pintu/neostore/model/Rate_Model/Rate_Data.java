package com.pintu.neostore.model.Rate_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rate_Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_category_id")
    @Expose
    private Integer productCategoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("producer")
    @Expose
    private String producer;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("rating")
    @Expose
    private Float rating;
    @SerializedName("view_count")
    @Expose
    private Integer viewCount;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("modified")
    @Expose
    private String modified;

    /**
     * No args constructor for use in serialization
     */
    public Rate_Data() {
    }

    /**
     * @param productCategoryId
     * @param cost
     * @param created
     * @param name
     * @param rating
     * @param producer
     * @param description
     * @param modified
     * @param id
     * @param viewCount
     */
    public Rate_Data(Integer id, Integer productCategoryId, String name, String producer, String description, Integer cost, Float rating, Integer viewCount, String created, String modified) {
        super();
        this.id = id;
        this.productCategoryId = productCategoryId;
        this.name = name;
        this.producer = producer;
        this.description = description;
        this.cost = cost;
        this.rating = rating;
        this.viewCount = viewCount;
        this.created = created;
        this.modified = modified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

}
