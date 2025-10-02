package com.acad.sys.Security.dto;

public record TokenResponse(String token, long expiresInSeconds) {
    
}
