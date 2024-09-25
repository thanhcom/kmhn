/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhtrang.online.thanhtrang.Model;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import thanhtrang.online.thanhtrang.HibnateUtils;

/**
 *
 * @author thanhcom
 */
public class tesst {
    public static void main(String[] args) {
        Session ss = HibnateUtils.getFactory().openSession();
       //paymentmethod
       //c.getReceipt().forEach(i ->System.out.println("Customer Name :["+i.getCustomer().getName()+"]\n Trong Kinh   : ["+i.getTkPrice()+"]"+" Do Kinh ["+i.getNote()+"]"));
        //c.getEyeService().forEach(action->System.out.println(action.getSPHL()));
        //Receipt r = ss.get(Receipt.class,"4");
        //r.getCustomer().id
        //System.out.println(r.getCustomer().getName());
        //Customer c= ss.get(Customer.class, r.getCustomer().getId());
        //System.out.println("Mat Phai : "+c.getEyeService().getLast().getSPHR()+"---- Mat Trai :"+c.getEyeService().getLast().getSPHL());
        EyeService e = ss.get(EyeService.class,"1");
        System.out.println(e.getEyeaxl());
        //Query q = ss.createQuery("FROM Customer ORDER BY Id DESC");
        //List<Customer> list = q.getResultList();
        //list.forEach(action->{
        //    System.out.println("ID:"+action.getEyeService().getLast().getAXR()+"--Name:"+action.getName()+"--Age:"+action.getAge());
        //});
        
        
        
        SimpleDateFormat formatter = new SimpleDateFormat("M-yyyy");
        //Date date = new Date();
        //String str =formatter.format(date);
        //Session ss = HibnateUtils.getFactory().openSession();        
        //Other o = ss.get(Other.class, 1);
        //o.getPaymentmethod();
        //System.out.println(o.getCustomer().getName()+"--------"+o.getServiceName()+"---------"+o.getServicePrice());
        //Query q = ss.createQuery("FROM Customer C INNER JOIN Receipt R ON C.id = R.customer.id WHERE R.date =:date");
        //q.setParameter("date",str);
        //List<Customer> listCustomer = q.getResultList();
        //listCustomer.forEach(action->System.out.println(action.getName()));
    
        Instant now = Instant.now();
        Instant yesterday = now.minus(1, ChronoUnit.DAYS);
        
        System.out.println("1-"+formatter.format(Date.from(now)));
        System.out.println(yesterday);
    }
    
}
