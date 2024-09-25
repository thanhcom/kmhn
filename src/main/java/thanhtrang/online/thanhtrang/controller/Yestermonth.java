/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package thanhtrang.online.thanhtrang.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import thanhtrang.online.thanhtrang.HibnateUtils;
import thanhtrang.online.thanhtrang.Model.Customer;

/**
 *
 * @author thanhcom
 */
@WebServlet(name = "Yestermonth", urlPatterns = {"/yestermonth"})
public class Yestermonth extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Date today = new Date();  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(today);  
        calendar.add(Calendar.MONTH, 0);  
        calendar.set(Calendar.DAY_OF_MONTH, 1);  
        calendar.add(Calendar.DATE, -1);  
        Date lastDayOfMonth = calendar.getTime();  
        DateFormat sdf = new SimpleDateFormat("yyyy-MM");  
        DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); 
        String str1 = sdf.format(lastDayOfMonth)+"-01";
        String str2 = sdf1.format(lastDayOfMonth);
        Session ss = HibnateUtils.getFactory().openSession();
        Query q = ss.createQuery("FROM Customer C INNER JOIN Receipt R ON C.id = R.customer.id WHERE R.date >=:date and R.date<=:date1 ORDER BY C.id DESC");
        q.setParameter("date", str1);
        q.setParameter("date1", str2);
        List<Customer> listCustomer = q.getResultList();
        request.setAttribute("listCustomer", listCustomer);
        request.setAttribute("datetime", sdf.format(lastDayOfMonth));
        request.getRequestDispatcher("yestermonth.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
