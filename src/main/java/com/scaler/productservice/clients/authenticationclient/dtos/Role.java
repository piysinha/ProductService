package com.scaler.productservice.clients.authenticationclient.dtos;

import com.scaler.productservice.models.BaseModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role extends BaseModel {
    private String role;
}
