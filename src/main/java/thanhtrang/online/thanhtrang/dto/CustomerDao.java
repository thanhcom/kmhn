/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhtrang.online.thanhtrang.dto;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import thanhtrang.online.thanhtrang.HibnateUtils;
import thanhtrang.online.thanhtrang.Model.Customer;

/**
 *
 * @author thanhcom
 */
public class CustomerDao {
    
    private static CustomerDao instance;
    
     public static CustomerDao getInstance()
     {
         if(instance ==null)
         {
             instance = new CustomerDao();
         }
         return instance;
     }

    private CustomerDao() {
    }
     
     

    public List<Customer> FinAll() {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Customer ORDER BY id DESC");
        List<Customer> list = q.getResultList();
        return list;
    }

    public Customer FinById(int ID) {
        Session ss = HibnateUtils.getFactory().openSession();
        Customer C = ss.get(Customer.class, ID);
        return C;
    }
    
     public List<Customer> FinByName(String Name) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Customer WHERE name LIKE :Name ORDER BY id DESC");
        q.setParameter("name", "%" + Name + "%");
        List<Customer> list = q.getResultList();
        return list;
    }

    public List<Customer> FinByPhone(String PhoneNumber) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Customer WHERE phone LIKE :PhoneNumber ORDER BY id DESC");
        q.setParameter("PhoneNumber", "%" + PhoneNumber + "%");
        List<Customer> list = q.getResultList();
        return list;
    }
    
    public List<Customer> FinByAge(int age) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Customer WHERE age=:age ORDER BY id DESC");
        q.setParameter("age", age);
        List<Customer> list = q.getResultList();
        return list;
    }

    public static void main(String[] args) {
        CustomerDao cdao = new CustomerDao();
        cdao.FinByAge(18).forEach(action -> System.out.println(action.getOther().size()));
    }

}
