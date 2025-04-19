/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhtrang.online.thanhtrang.resources;

import thanhtrang.online.thanhtrang.BCryptEncode;
import thanhtrang.online.thanhtrang.Model.Customer;
import thanhtrang.online.thanhtrang.Model.EyeService;
import thanhtrang.online.thanhtrang.Model.Receipt;
import thanhtrang.online.thanhtrang.SendPostWithHttpClient;

/**
 *
 * @author thanhcom
 */
public class test {
    public static void main(String[] args) throws Exception {
        //String password = "12345678";
        String password1 = "laodaicaha";
        BCryptEncode b = new BCryptEncode();
        String bcryptHashString = b.PassEncode(password1);
        System.out.println(bcryptHashString);
        //BCrypt.Result result = BCrypt.verifyer().verify(password1.toCharArray(),bcryptHashString);
       // System.out.println("SS:"+result.verified);
//        
//        AdminDao dao = AdminDao.getInstance();
//        Admin A = dao.FinByUserName("thanhcom");
//        if(A!=null)
//        {
//            System.out.println("User:"+A.getFullname());
//        }
        SendPostWithHttpClient client = new  SendPostWithHttpClient();
        EyeService eyeService = new EyeService();
        Customer c = new Customer();
        c.setName("Hello Usser Thanhf");
        c.setPhone("0962100123");
        Receipt r = new Receipt();
        r.setGkPrice(550);
        r.setTkPrice(350);
        client.SendRequest(eyeService, c, r);
    }
    
}
