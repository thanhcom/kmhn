/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package thanhtrang.online.thanhtrang.controller.oder;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.hibernate.Session;
import thanhtrang.online.thanhtrang.HibnateUtils;
import thanhtrang.online.thanhtrang.dto.ReceiptDao;

/**
 *
 * @author thanhcom
 */
@WebServlet(name = "Home", urlPatterns = {"/home"})
public class Home extends HttpServlet {

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
            Session ss = HibnateUtils.getFactory().openSession();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Instant now = Instant.now();
            Instant yesterday = now.minus(6, ChronoUnit.DAYS);
            String str = formatter.format(Date.from(yesterday));
            String str1 = formatter.format(Date.from(now));
            /*
            Query q = ss.createQuery("FROM Customer C INNER JOIN Receipt R ON C.id = R.customer.id WHERE R.date >=:date ORDER BY C.id DESC");
            q.setParameter("date", str);
            List<Customer> listCustomer = q.getResultList();*/
            request.setAttribute("str", str);
            request.setAttribute("listCustomer", ReceiptDao.getInstance().FindByDaytoDay(str, str1));
            request.getRequestDispatcher("oder/home.jsp").forward(request, response);
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
