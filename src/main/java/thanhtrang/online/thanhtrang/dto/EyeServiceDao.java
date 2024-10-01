/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhtrang.online.thanhtrang.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import thanhtrang.online.thanhtrang.HibnateUtils;
import thanhtrang.online.thanhtrang.Model.Customer;
import thanhtrang.online.thanhtrang.Model.EyeService;

/**
 *
 * @author thanhcom
 */
public class EyeServiceDao {
    
     private static EyeServiceDao instance;
    
     public static EyeServiceDao getInstance()
     {
         if(instance ==null)
         {
             instance = new EyeServiceDao();
         }
         return instance;
     }

    private EyeServiceDao() {
    }
     
     

    public List<EyeService> FindAll() {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Receipt EyeService BY eyeid DESC");
        List<EyeService> list = q.getResultList();
        return list;
    }

    public EyeService FindById(int id) {
        Session ss = HibnateUtils.getFactory().openSession();
        EyeService e = ss.get(EyeService.class, id);
        return e;
    }

    public List<EyeService> FindByCustomerId(int CustomerId) {
        Session ss = HibnateUtils.getFactory().openSession();
        Customer c = ss.get(Customer.class, CustomerId);
        //Query q = ss.createQuery("FROM Receipt R WHERE R.customer.id=:cusid ORDER BY R.id DESC");
        //q.setParameter("cusid", CustomerId);
        //List<Receipt> list = q.getResultList();
        List<EyeService> list1 = c.getEyeService();
        return list1;
    }

    public List<EyeService> FindByCustomerName(String CustomerName) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM EyeService E WHERE E.customer.name LIKE :CustomerName ORDER BY E.eyeid DESC");
        q.setParameter("CustomerName", "%" + CustomerName + "%");
        List<EyeService> list = q.getResultList();
        return list;
    }

    public List<EyeService> FindByCustomerPhone(String PhoneNumber) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM EyeService E WHERE E.customer.phone LIKE :PhoneNumber ORDER BY E.eyeid DESC");
        q.setParameter("PhoneNumber", "%" + PhoneNumber + "%");
        List<EyeService> list = q.getResultList();
        return list;
    }
    
     public List<EyeService> FindByDay(String date) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM EyeService WHERE eyedatetime =:date ORDER BY id DESC");
        q.setParameter("eyedatetime", date);
        List<EyeService> list = q.getResultList();
        return list;
    }

    public List<EyeService> FindByCurrentDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = formatter.format(date);
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM EyeService WHERE eyedatetime =:date ORDER BY id DESC");
        q.setParameter("eyedatetime", str);
        List<EyeService> list = q.getResultList();
        return list;
    }
    
     public void EyeServiceAdd(EyeService R)
    {
       Session ss = HibnateUtils.getFactory().openSession();
       ss.save(R);
    }
    
    public void EyeServiceEdit(EyeService R)
    {
       Session ss = HibnateUtils.getFactory().openSession();
       ss.getTransaction().begin();
       ss.save(R);
       ss.getTransaction().commit();
    }
    
    public void EyeServiceRemove(EyeService R)
    {
        
       Session ss = HibnateUtils.getFactory().openSession();
       ss.getTransaction().begin();
       ss.delete(R);
       ss.getTransaction().commit();
    }
    
}
