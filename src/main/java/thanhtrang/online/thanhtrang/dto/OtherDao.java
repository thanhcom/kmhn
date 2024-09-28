/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhtrang.online.thanhtrang.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import thanhtrang.online.thanhtrang.HibnateUtils;
import thanhtrang.online.thanhtrang.Model.Customer;
import thanhtrang.online.thanhtrang.Model.Other;
import thanhtrang.online.thanhtrang.Model.Receipt;

/**
 *
 * @author thanhcom
 */
public class OtherDao {
    
    public List<OtherDao> FindAll() {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Other ORDER BY id DESC");
        List<OtherDao> list = q.getResultList();
        return list;
    }

    public OtherDao FindById(int id) {
        Session ss = HibnateUtils.getFactory().openSession();
        OtherDao e = ss.get(OtherDao.class, id);
        return e;
    }

    public List<Other> FindByCustomerId(int CustomerId) {
        Session ss = HibnateUtils.getFactory().openSession();
        Customer c = ss.get(Customer.class, CustomerId);
        //Query q = ss.createQuery("FROM Receipt R WHERE R.customer.id=:cusid ORDER BY R.id DESC");
        //q.setParameter("cusid", CustomerId);
        //List<Receipt> list = q.getResultList();
        List<Other> list = c.getOther();
        return list;
    }

    public List<Other> FindByCustomerName(String CustomerName) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Other E WHERE E.customer.name LIKE :CustomerName ORDER BY E.eyeid DESC");
        q.setParameter("CustomerName", "%" + CustomerName + "%");
        List<Other> list = q.getResultList();
        return list;
    }

    public List<Other> FindByCustomerPhone(String PhoneNumber) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Other E WHERE E.customer.phone LIKE :PhoneNumber ORDER BY E.eyeid DESC");
        q.setParameter("PhoneNumber", "%" + PhoneNumber + "%");
        List<Other> list = q.getResultList();
        return list;
    }
    
     public List<Other> FindByDay(String date) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Other WHERE date=:date ORDER BY id DESC");
        q.setParameter("date", date);
        List<Other> list = q.getResultList();
        return list;
    }

    public List<Other> FindByCurrentDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = formatter.format(date);
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Other WHERE date =:date ORDER BY id DESC");
        q.setParameter("date", str);
        List<Other> list = q.getResultList();
        return list;
    }
    
    public List<Other> FindByDaytoDay(String StartDate, String EndDate) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Other R WHERE R.date >=:date and R.date<=:date1 ORDER BY id DESC");
        q.setParameter("date", StartDate);
        q.setParameter("date1", EndDate);
        List<Other> list = q.getResultList();
        return list;
    }

    public List<Other> FindByCurrentMonth() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        Instant now = Instant.now();
        String str = formatter.format(Date.from(now)) + "-01";
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Other WHERE date >=:date ORDER BY id DESC");
        q.setParameter("date", str);
        List<Other> list = q.getResultList();
        return list;
    }

    public List<Other> FindByYesterMonth() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date lastDayOfMonth = calendar.getTime();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM");
        DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String str1 = sdf.format(lastDayOfMonth) + "-01";
        String str2 = sdf1.format(lastDayOfMonth);
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Other R WHERE R.date >=:date and R.date<=:date1 ORDER BY R.id DESC");
        q.setParameter("date", str1);
        q.setParameter("date1", str2);
        List<Other> list = q.getResultList();
        return list;
    }
    
    public List<Other> FindByServiceName(String ServiceName) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Other WHERE serviceName LIKE :serviceName ORDER BY id DESC");
        q.setParameter("serviceName", "%" + ServiceName + "%");
        List<Other> list = q.getResultList();
        return list;
    }
    
    public void OtherAdd(Other R)
    {
       Session ss = HibnateUtils.getFactory().openSession();
       ss.save(R);
    }
    
    public void OtherEdit(Other R)
    {
       Session ss = HibnateUtils.getFactory().openSession();
       ss.getTransaction().begin();
       ss.save(R);
       ss.getTransaction().commit();
    }
    
    public void OtherRemove(Other R)
    {
        
       Session ss = HibnateUtils.getFactory().openSession();
       ss.getTransaction().begin();
       ss.delete(R);
       ss.getTransaction().commit();
    }
    
}
