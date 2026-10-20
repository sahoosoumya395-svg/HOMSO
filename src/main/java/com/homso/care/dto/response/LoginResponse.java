package com.homso.care.dto.response;

public class LoginResponse {

    private String token;
    private String tokenType;
    private Long userId;
    private String fullName;
    private String email;
    private String role;
    private String mrnNo;
    private String message;

    public LoginResponse(
            String token,
            Long userId,
            String fullName,
            String email,
            String role,
            String mrnNo,
            String message) {

        this.token = token;
        this.tokenType = "Bearer";
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.mrnNo = mrnNo;
        this.message = message;
    }

    public String getToken() { return token; }
    public String getTokenType() { return tokenType; }
    public Long getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getMrnNo() { return mrnNo; }
    public String getMessage() { return message; }
}