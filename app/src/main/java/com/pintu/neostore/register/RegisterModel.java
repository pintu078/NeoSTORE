package com.pintu.neostore.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterModel {

    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("confirm_password")
    private String confirm_password;
    @SerializedName("gender")
    private String gender;
    @SerializedName("phone_no")
    private String phone_no;

    public RegisterModel(String first_name, String last_name, String email, String password, String confirm_password, String gender, String phone_no) {
       super();
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
        this.gender = gender;
        this.phone_no = phone_no;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone_no() {
        return phone_no;
    }
}
