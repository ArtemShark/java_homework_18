package org.example.model;

public class Staff {
    private int staffId;
    private String fullName;
    private String phone;
    private String email;
    private String position;

    public Staff() {}

    public Staff(int staffId, String fullName, String phone, String email, String position) {
        this.staffId = staffId;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }

    public int getStaffId() { return staffId; }
    public void setStaffId(int staffId) { this.staffId = staffId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}
