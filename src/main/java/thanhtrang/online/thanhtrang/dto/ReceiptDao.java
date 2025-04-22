/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhtrang.online.thanhtrang.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import thanhtrang.online.thanhtrang.HibnateUtils;
import thanhtrang.online.thanhtrang.Model.Customer;
import thanhtrang.online.thanhtrang.Model.Receipt;

/**
 *
 * @author thanhcom
 */
public class ReceiptDao {
    
    private static ReceiptDao instance;
    
     public static ReceiptDao getInstance()
     {
         if(instance ==null)
         {
             instance = new ReceiptDao();
         }
         return instance;
     }

    private ReceiptDao() {
    }
    
     
    public List<Receipt> FindAll() {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Receipt ORDER BY id DESC");
        List<Receipt> list = q.getResultList();
        return list;
    }

    public Receipt FindById(int id) {
        Session ss = HibnateUtils.getFactory().openSession();
        Receipt r = ss.get(Receipt.class, id);
        return r;
    }

    public List<Receipt> FindByCustomerId(int CustomerId) {
        Session ss = HibnateUtils.getFactory().openSession();
        Customer c = ss.get(Customer.class, CustomerId);
        //Query q = ss.createQuery("FROM Receipt R WHERE R.customer.id=:cusid ORDER BY R.id DESC");
        //q.setParameter("cusid", CustomerId);
       //List<Receipt> list = q.getResultList();
        List<Receipt> list1 = c.getReceipt();
        return list1;
    }
    
    public List<Receipt> FindByGkName(String GkName) {
        Session ss = HibnateUtils.getFactory().openSession();
         Query q = ss.createQuery("FROM Receipt R WHERE R.gkName LIKE :gkName ORDER BY R.id DESC");
         q.setParameter("gkName", "%" + GkName + "%");
         List<Receipt> list = q.getResultList();
         return list;
    }
    
    public List<Receipt> FindByTkName(String TkName) {
        Session ss = HibnateUtils.getFactory().openSession();
         Query q = ss.createQuery("FROM Receipt R WHERE R.tkName LIKE :tkName ORDER BY R.id DESC");
         q.setParameter("tkName", "%" + TkName + "%");
         List<Receipt> list = q.getResultList();
         return list;
    }

    public List<Receipt> FindByCustomerName(String CustomerName) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Receipt R WHERE R.customer.name LIKE :CustomerName ORDER BY R.id DESC");
        q.setParameter("CustomerName", "%" + CustomerName + "%");
        List<Receipt> list = q.getResultList();
        return list;
    }

    public List<Receipt> FindByCustomerPhone(String PhoneNumber) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Receipt R WHERE R.customer.phone LIKE :PhoneNumber ORDER BY R.id DESC");
        q.setParameter("PhoneNumber", "%" + PhoneNumber + "%");
        List<Receipt> list = q.getResultList();
        return list;
    }

    public List<Receipt> FindByDay(String date) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Receipt WHERE date =:date ORDER BY id DESC");
        q.setParameter("date", date);
        List<Receipt> list = q.getResultList();
        return list;
    }

    public List<Receipt> FindByCurrentDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = formatter.format(date);
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Receipt WHERE date =:date ORDER BY id DESC");
        q.setParameter("date", str);
        List<Receipt> list = q.getResultList();
        return list;
    }
    
     public List<Receipt> FindByYesterday() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Instant now = Instant.now();
        Instant yesterday = now.minus(1, ChronoUnit.DAYS);
        String str = formatter.format(Date.from(yesterday));
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Receipt WHERE date =:date ORDER BY id DESC");
        q.setParameter("date", str);
        List<Receipt> list = q.getResultList();
        return list;
    }

    public List<Receipt> FindByDaytoDay(String StartDate, String EndDate) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Receipt WHERE date >=:date and date<=:date1 ORDER BY id DESC");
        q.setParameter("date", StartDate);
        q.setParameter("date1", EndDate);
        List<Receipt> list = q.getResultList();
        return list;
    }

    public List<Receipt> FindByCurrentMonth() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        Instant now = Instant.now();
        String str = formatter.format(Date.from(now)) + "-01";
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Receipt WHERE date >=:date ORDER BY id DESC");
        q.setParameter("date", str);
        List<Receipt> list = q.getResultList();
        return list;
    }

    public List<Receipt> FindByYesterMonth() {
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
        Query q = ss.createQuery("FROM Receipt R WHERE R.date >=:date and R.date<=:date1 ORDER BY R.id DESC");
        q.setParameter("date", str1);
        q.setParameter("date1", str2);
        List<Receipt> list = q.getResultList();
        return list;
    }
    
    public void ReceiptAdd(Receipt R)
    {
       Session ss = HibnateUtils.getFactory().openSession();
       ss.save(R);
    }
    
    public void ReceiptEdit(Receipt R)
    {
       Session ss = HibnateUtils.getFactory().openSession();
       ss.getTransaction().begin();
       ss.save(R);
       ss.getTransaction().commit();
    }
    
    public void ReceiptRemove(Receipt R)
    {
        
       Session ss = HibnateUtils.getFactory().openSession();
       ss.getTransaction().begin();
       ss.delete(R);
       ss.getTransaction().commit();
    }

    public static void main(String[] args) {
        ReceiptDao dao = new ReceiptDao();
       // dao.FindByGkName("TK").forEach(action -> System.out.println("ID: " + action.getId() + "---Name :" + action.getCustomer().getEyeService().getLast().getEyeadd()+ "----------  Ten trong  :" + action.getTkName()));
    }

}
