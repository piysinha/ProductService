package com.scaler.productservice.clients.authenticationclient.dtos;

public enum SessionStatus {
    //Session is active
    ACTIVE, //0
    //Session is ended
    ENDED, //1
    //Session is logged out due to timeout
    LOGGED_OUT, //2
    //Session is Invalid
    INVALID

}
