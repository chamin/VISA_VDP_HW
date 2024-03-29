package com.visa.vdp.model;

/**
 ** @Author kkcmpathi , Created on 28/03/24
 **/
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private String message;
    public Response() {}
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "Message=" + message;
    }
}