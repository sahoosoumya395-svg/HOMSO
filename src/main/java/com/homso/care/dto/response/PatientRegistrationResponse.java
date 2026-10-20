package com.homso.care.dto.response;

public class PatientRegistrationResponse {

    private Long patientId;
    private String mrnNo;
    private Long userId;
    private String fullName;
    private String email;
    private String phone;
    private String role;
    private String message;

    public PatientRegistrationResponse() {
    }

    public PatientRegistrationResponse(
            Long patientId,
            String mrnNo,
            Long userId,
            String fullName,
            String email,
            String phone,
            String role,
            String message) {

        this.patientId = patientId;
        this.mrnNo = mrnNo;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.message = message;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getMrnNo() {
        return mrnNo;
    }

    public void setMrnNo(String mrnNo) {
        this.mrnNo = mrnNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}