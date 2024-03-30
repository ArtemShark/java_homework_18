package org.example.model;

import java.time.LocalDate;

public class Client {
    private int clientId;
    private String fullName;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private int discount;

    public Client() {}

    public Client(int clientId, String fullName, LocalDate birthDate, String phone, String email, int discount) {
        this.clientId = clientId;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.discount = discount;
    }

    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }
}
