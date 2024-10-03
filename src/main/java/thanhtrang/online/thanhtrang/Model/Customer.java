/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhtrang.online.thanhtrang.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author thanhcom
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String phone;
    private int gender;
    private  String address;
    
    @OneToMany(mappedBy = "customer")
    private List<Other> Other;
    
    @OneToMany(mappedBy = "customer")
    private List<Receipt> Receipt;

    @OneToMany(mappedBy = "customer")
    private List<EyeService> eyeService;

    public List<Other> getOther() {
        return Other;
    }

    public void setOther(List<Other> Other) {
        this.Other = Other;
    }

    
    
    public List<EyeService> getEyeService() {
        return eyeService;
    }

    public void setEyeService(List<EyeService> eyeService) {
        this.eyeService = eyeService;
    }
    
    
    
    public List<Receipt> getReceipt() {
        return Receipt;
    }

    public void setReceipt(List<Receipt> receipt) {
        this.Receipt = receipt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
