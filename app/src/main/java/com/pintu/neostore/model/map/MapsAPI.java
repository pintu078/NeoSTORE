package com.pintu.neostore.model.map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapsAPI {

    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("store_address")
    @Expose
    private String storeAddress;
    @SerializedName("store_desc")
    @Expose
    private String storeDesc;
    @SerializedName("store_rating")
    @Expose
    private Float storeRating;
    @SerializedName("store_contact")
    @Expose
    private String storeContact;
    @SerializedName("store_website")
    @Expose
    private String storeWebsite;
    @SerializedName("store_image")
    @Expose
    private String storeImage;
    @SerializedName("store_latitiue")
    @Expose
    private String storeLatitiue;
    @SerializedName("store_longitude")
    @Expose
    private String storeLongitude;

    /**
     * No args constructor for use in serialization
     *
     */
    public MapsAPI() {
    }

    /**
     *
     * @param storeDesc
     * @param storeImage
     * @param storeWebsite
     * @param storeLongitude
     * @param storeAddress
     * @param storeContact
     * @param storeName
     * @param storeRating
     * @param storeLatitiue
     */
    public MapsAPI(String storeName, String storeAddress, String storeDesc, Float storeRating, String storeContact, String storeWebsite, String storeImage, String storeLatitiue, String storeLongitude) {
        super();
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeDesc = storeDesc;
        this.storeRating = storeRating;
        this.storeContact = storeContact;
        this.storeWebsite = storeWebsite;
        this.storeImage = storeImage;
        this.storeLatitiue = storeLatitiue;
        this.storeLongitude = storeLongitude;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreDesc() {
        return storeDesc;
    }

    public void setStoreDesc(String storeDesc) {
        this.storeDesc = storeDesc;
    }

    public Float getStoreRating() {
        return storeRating;
    }

    public void setStoreRating(Float storeRating) {
        this.storeRating = storeRating;
    }

    public String getStoreContact() {
        return storeContact;
    }

    public void setStoreContact(String storeContact) {
        this.storeContact = storeContact;
    }

    public String getStoreWebsite() {
        return storeWebsite;
    }

    public void setStoreWebsite(String storeWebsite) {
        this.storeWebsite = storeWebsite;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public String getStoreLatitiue() {
        return storeLatitiue;
    }

    public void setStoreLatitiue(String storeLatitiue) {
        this.storeLatitiue = storeLatitiue;
    }

    public String getStoreLongitude() {
        return storeLongitude;
    }

    public void setStoreLongitude(String storeLongitude) {
        this.storeLongitude = storeLongitude;
    }

}
