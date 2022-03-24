package com.ibsoftware.forum.dto;

import lombok.Getter;

@Getter
public class TokenDto {

    private String token;
    private String tipo;

    public TokenDto(String token, String bearer) {
        this.token = token;
        this.tipo = bearer;
    }
}
