package com.pintu.neostore.model.order.Order_List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("created")
    @Expose
    private String created;

    /**
     * No args constructor for use in serialization
     *
     */
    public Datum() {
    }

    /**
     *
     * @param cost
     * @param created
     * @param id
     */
    public Datum(Integer id, Integer cost, String created) {
        super();
        this.id = id;
        this.cost = cost;
        this.created = created;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}