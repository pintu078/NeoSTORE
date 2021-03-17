package com.pintu.neostore.model.ProductDetailed_Model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailed_Data {

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
    private Integer rating;
    @SerializedName("view_count")
    @Expose
    private Integer viewCount;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("product_images")
    @Expose
    private List<ProductImage> productImages = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProductDetailed_Data() {
    }

    /**
     *
     * @param productImages
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
    public ProductDetailed_Data(Integer id, Integer productCategoryId, String name, String producer, String description, Integer cost, Integer rating, Integer viewCount, String created, String modified, List<ProductImage> productImages) {
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
        this.productImages = productImages;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
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

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

}