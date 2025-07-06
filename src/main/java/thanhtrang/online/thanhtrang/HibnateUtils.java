/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhtrang.online.thanhtrang;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import thanhtrang.online.thanhtrang.Model.Admin;
import thanhtrang.online.thanhtrang.Model.Customer;
import thanhtrang.online.thanhtrang.Model.Other;
import thanhtrang.online.thanhtrang.Model.Receipt;
import thanhtrang.online.thanhtrang.Model.EyeService;
/**
 *
 * @author thanhcom
 */
public class HibnateUtils {
    
    private static final SessionFactory Factory;

    public static SessionFactory getFactory() {
        return Factory;
    }
    
 
    static {
        Configuration cof = new Configuration() {};
        Properties props = new Properties();
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://thanhcom1989.ddns.net/KinhMatHaNoi");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "@12345");
        props.put(Environment.SHOW_SQL, "true");
        cof.setProperties(props);
        cof.addAnnotatedClass(Customer.class);
        cof.addAnnotatedClass(Receipt.class);
        cof.addAnnotatedClass(EyeService.class);
        cof.addAnnotatedClass(Other.class);
        cof.addAnnotatedClass(Admin.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder().
                                   applySettings(cof.getProperties()).build();
        Factory=cof.buildSessionFactory(registry);
    }
    
}
