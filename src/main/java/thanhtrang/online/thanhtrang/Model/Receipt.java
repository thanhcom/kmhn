/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhtrang.online.thanhtrang.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author thanhcom
 */
@Entity
@Table(name = "Receipt")
public class Receipt implements Serializable{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   
   @ManyToOne//MAc dinh LAZE
   @JoinColumn(name = "cusid")
   private Customer customer;
   
   private String gkName;
   private int gkPrice;
   private String tkName;
   private int tkPrice;
   private String note;
   private int paymentMethod;
   private int discount;
   private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
   
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getGkName() {
        return gkName;
    }

    public void setGkName(String gkName) {
        this.gkName = gkName;
    }

    public int getGkPrice() {
        return gkPrice;
    }

    public void setGkPrice(int gkPrice) {
        this.gkPrice = gkPrice;
    }

    public String getTkName() {
        return tkName;
    }

    public void setTkName(String tkName) {
        this.tkName = tkName;
    }

    public int getTkPrice() {
        return tkPrice;
    }

    public void setTkPrice(int tkPrice) {
        this.tkPrice = tkPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
   
   
    
   
}
