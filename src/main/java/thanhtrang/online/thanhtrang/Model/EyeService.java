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
@Table(name = "eyeService")
public class EyeService implements Serializable{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int eyeid;
   private double eyeadd;
   private String eyeapproved;
   private int eyeaxl;
   private int eyeaxr;
   @ManyToOne//MAc dinh LAZE
   @JoinColumn(name = "cusid")
   private Customer customer;
   private double eyecyll;
   private double eyecylr;
   private String eyedatetime;
   private int eyepd;
   private double eyesphl;
   private double eyesphr;

    public int getEyeid() {
        return eyeid;
    }

    public void setEyeid(int eyeid) {
        this.eyeid = eyeid;
    }

    public double getEyeadd() {
        return eyeadd;
    }

    public void setEyeadd(double eyeadd) {
        this.eyeadd = eyeadd;
    }

    public String getEyeapproved() {
        return eyeapproved;
    }

    public void setEyeapproved(String eyeapproved) {
        this.eyeapproved = eyeapproved;
    }

    public int getEyeaxl() {
        return eyeaxl;
    }

    public void setEyeaxl(int eyeaxl) {
        this.eyeaxl = eyeaxl;
    }

    public int getEyeaxr() {
        return eyeaxr;
    }

    public void setEyeaxr(int eyeaxr) {
        this.eyeaxr = eyeaxr;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getEyecyll() {
        return eyecyll;
    }

    public void setEyecyll(double eyecyll) {
        this.eyecyll = eyecyll;
    }

    public double getEyecylr() {
        return eyecylr;
    }

    public void setEyecylr(double eyecylr) {
        this.eyecylr = eyecylr;
    }

    public String getEyedatetime() {
        return eyedatetime;
    }

    public void setEyedatetime(String eyedatetime) {
        this.eyedatetime = eyedatetime;
    }

    public int getEyepd() {
        return eyepd;
    }

    public void setEyepd(int eyepd) {
        this.eyepd = eyepd;
    }

    public double getEyesphl() {
        return eyesphl;
    }

    public void setEyesphl(double eyesphl) {
        this.eyesphl = eyesphl;
    }

    public double getEyesphr() {
        return eyesphr;
    }

    public void setEyesphr(double eyesphr) {
        this.eyesphr = eyesphr;
    }

 
}
