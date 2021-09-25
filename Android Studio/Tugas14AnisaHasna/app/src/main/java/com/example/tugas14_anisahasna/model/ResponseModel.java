package com.example.tugas14_anisahasna.model;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {

    @SerializedName("message")
    private String message;

    @SerializedName("id")
    private String id;

    @SerializedName("error")
    private boolean error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
