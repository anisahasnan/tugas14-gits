package com.example.tugas14_anisahasna.model;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {

    @SerializedName("message")
    private String message;

    @SerializedName("idUser")
    private String idUser;

    @SerializedName("error")
    private boolean error;

    @SerializedName("username")
    private String username;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
