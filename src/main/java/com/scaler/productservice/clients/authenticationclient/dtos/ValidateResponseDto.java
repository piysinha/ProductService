package com.scaler.productservice.clients.authenticationclient.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateResponseDto {
    private UserDto userDto;
    private SessionStatus sessionStatus;
}
