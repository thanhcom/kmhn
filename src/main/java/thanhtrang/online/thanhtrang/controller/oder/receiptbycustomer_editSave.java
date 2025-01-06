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
import java.util.Date;
import org.hibernate.Session;
import thanhtrang.online.thanhtrang.HibnateUtils;
import thanhtrang.online.thanhtrang.Model.Customer;
import thanhtrang.online.thanhtrang.Model.Receipt;

/**
 *
 * @author thanhcom
 */
@WebServlet(name = "receiptbycustomer_editSave", urlPatterns = {"/receiptbycustomer_editsave"})
public class receiptbycustomer_editSave extends HttpServlet {

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
        String cid = request.getParameter("cid");
       String rid = request.getParameter("rid");
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Session ss = HibnateUtils.getFactory().openSession();        
        //thanhtrang.online.thanhtrang.Model.Customer c= CustomerDao.getInstance().FinById(Integer.parseInt(cid));
        ss.getTransaction().begin();
        Customer c= new Customer();
        c.setId(Integer.parseInt(cid));
        c.setName(request.getParameter("name"));
        c.setPhone(request.getParameter("phone"));
        c.setAddress(request.getParameter("address"));
        c.setGender(Integer.parseInt(request.getParameter("gender")));
        c.setAge(Integer.parseInt(request.getParameter("age")));
        ss.merge(c);
        
        Receipt r = new Receipt();
        r.setId(Integer.parseInt(rid));
        r.setCustomer(c);
        r.setGkName(request.getParameter("gkname"));
        r.setTkName(request.getParameter("tkname"));
        r.setGkPrice(Integer.parseInt(request.getParameter("gkprice")));
        r.setTkPrice(Integer.parseInt(request.getParameter("tkprice")));
        r.setDiscount(Integer.parseInt(request.getParameter("discount")));
        r.setPaymentMethod(Integer.parseInt(request.getParameter("paymentmethod")));
        r.setNote(request.getParameter("note"));
        r.setDate(request.getParameter("date"));
        ss.merge(r);
        ss.getTransaction().commit();
        request.getRequestDispatcher("customerdetail?id="+cid).forward(request, response);
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
