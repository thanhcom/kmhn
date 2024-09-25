/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhtrang.online.thanhtrang.Model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import thanhtrang.online.thanhtrang.HibnateUtils;

/**
 *
 * @author thanhcom
 */
public class test3 {
    public static void main(String[] args) {
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Receipt");
        List<Receipt> list = q.getResultList();
        list.forEach(action->System.out.println(action.getCustomer().getName()+"Trong Kinh :"+action.getTkName()));
    }
    
}
