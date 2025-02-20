
package model;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private String id;
    private String name;
    private String phoneNumber;
    private String partTime;

    public NhanVien() {
    }

    public NhanVien(String id, String name, String phoneNumber, String partTime) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.partTime = partTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPartTime() {
        return partTime;
    }

    public void setPartTime(String partTime) {
        this.partTime = partTime;
    }

    @Override
    public String toString() {
        return String.format("| %-7s| %-20s| %-13s| %-19s|", id, name, phoneNumber, partTime);
    }
    
    
}
