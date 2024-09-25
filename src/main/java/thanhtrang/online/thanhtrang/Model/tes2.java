/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhtrang.online.thanhtrang.Model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author thanhcom
 */
public class tes2 {
    public static void main(String[] args) {
//        Date today = new Date();  
//        Calendar calendar = Calendar.getInstance();  
//        calendar.setTime(today);  
//        calendar.add(Calendar.MONTH, 0);  
//        calendar.set(Calendar.DAY_OF_MONTH, 1);  
//        calendar.add(Calendar.DATE, -1);  
//        Date lastDayOfMonth = calendar.getTime();  
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM");  
//        DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); 
//        String str1 = sdf.format(lastDayOfMonth)+"-01";
//        String str2 = sdf1.format(lastDayOfMonth);
//        System.out.println("Start            : " + str1);  
//        System.out.println("End: " + str2); 
//        
//           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            Instant now = Instant.now();
//            Instant yesterday = now.minus(7, ChronoUnit.DAYS);
//            String str = formatter.format(Date.from(yesterday));
//            System.out.println(str);


                     // String amount 
          int price = 20000000;
        Locale locale = new Locale("vi", "VN");
        Currency currency = Currency.getInstance("VND");
        DecimalFormatSymbols df = DecimalFormatSymbols.getInstance(locale);
        df.setCurrency(currency);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        numberFormat.setCurrency(currency);
        System.out.println(numberFormat.format(price));
        
    }
    
}
