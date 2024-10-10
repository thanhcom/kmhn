/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhtrang.online.thanhtrang.dto;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import thanhtrang.online.thanhtrang.HibnateUtils;
import thanhtrang.online.thanhtrang.Model.Admin;

/**
 *
 * @author thanhcom
 */
public class AdminDao {
    
    private static AdminDao instance;
    
     public static AdminDao getInstance()
     {
         if(instance ==null)
         {
             instance = new AdminDao();
         }
         return instance;
     }

    private AdminDao() {
    }
    
    public List<Admin> FinAll() {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Admin ORDER BY id DESC");
        List<Admin> list = q.getResultList();
        return list;
    }

    public Admin FinById(int ID) {
        Session ss = HibnateUtils.getFactory().openSession();
        Admin C = ss.get(Admin.class, ID);
        //Admin A = ss.get(Admin.class, );
        return C;
    }
    
    public List<Admin> FinByUserName(String username) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Admin WHERE user LIKE :CustomerName ORDER id DESC");
        q.setParameter("CustomerName", "%" + username + "%");
        List<Admin> list = q.getResultList();
        return list;
    }
    
    public Admin CheckLogin(String user, String pass) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Admin WHERE user =:CustomerName AND pass=:pass");
        q.setParameter("CustomerName",user);
        q.setParameter("pass",pass);
        List<Admin> list = q.getResultList();
        Admin A = null;
       if(list.size()==1)
       {
           A=list.getFirst();
       }
       return A;
    }
    
    public String CheckUserExisted(Admin user) {
        String kq;
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Admin WHERE user =:CustomerName");
        q.setParameter("CustomerName",user.getUser());
       if(q.getResultList().size()==1)
       {
           kq="X";
       }else
       {
           kq="-";
       }
        
        Query q1 = ss.createQuery("FROM Admin WHERE phone LIKE :CustomerName");
        q1.setParameter("CustomerName",user.getPhone());
         if(q1.getResultList().size()==1)
        {
            kq+="X";
        }else
         {
            kq+="-"; 
         }
        
         Query q2 = ss.createQuery("FROM Admin WHERE email LIKE :CustomerName");
        q2.setParameter("CustomerName",user.getEmail());
         if(q2.getResultList().size()==1)
        {
            kq+="X";
        }else
         {
            kq+="-"; 
         }
        
        return kq;
    }
    
    public void AdminAdd(Admin R)
    {
       Session ss = HibnateUtils.getFactory().openSession();
       ss.save(R);
    }
    
    public void AdminEdit(Admin R)
    {
       Session ss = HibnateUtils.getFactory().openSession();
       ss.getTransaction().begin();
       ss.save(R);
       ss.getTransaction().commit();
    }
    
    public void AdminRemove(Admin R)
    {
        
       Session ss = HibnateUtils.getFactory().openSession();
       ss.getTransaction().begin();
       ss.delete(R);
       ss.getTransaction().commit();
    }
    
    public static void main(String[] args) {
        //AdminDao dao = new AdminDao();
        Admin a = getInstance().CheckLogin("thanhcom", "laodaica");
//        Admin A = new Admin();
//        A.setEmail("anhchang00@gmail.com");
//        A.setFullname("Nguyen DAnh Thanh");
//        A.setId(1);
//        A.setPhone("0962100123");
//        A.setRole(1);
//        A.setUser("thanhcom");
//        System.out.println(getInstance().CheckUserExisted(A));
        if(a!=null)
        System.out.println(a.getFullname()+"--Role :"+a.getRole());
        else
         System.out.println("Sai Tai Khoan");
}
}