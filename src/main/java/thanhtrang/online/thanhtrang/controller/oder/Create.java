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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import thanhtrang.online.thanhtrang.HibnateUtils;
import thanhtrang.online.thanhtrang.Model.Customer;
import thanhtrang.online.thanhtrang.Model.Receipt;
import thanhtrang.online.thanhtrang.Model.EyeService;
import thanhtrang.online.thanhtrang.SendPostWithHttpClient;

/**
 *
 * @author thanhcom
 */
@WebServlet(name = "Create", urlPatterns = {"/create"})
public class Create extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        SendPostWithHttpClient client = new  SendPostWithHttpClient();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Session ss = HibnateUtils.getFactory().openSession();        
        Customer c= new Customer();
        
        c.setName(request.getParameter("name"));
        c.setPhone(request.getParameter("phone"));
        c.setAddress(request.getParameter("address"));
        c.setGender(Integer.parseInt(request.getParameter("gender")));
        c.setAge(Integer.parseInt(request.getParameter("age")));
        ss.getTransaction().begin();
        ss.persist(c);
        
        Receipt r = new Receipt();
        r.setCustomer(c);
        r.setGkName(request.getParameter("gkname"));
        r.setTkName(request.getParameter("tkname"));
        r.setGkPrice(Integer.parseInt(request.getParameter("gkprice")));
        r.setTkPrice(Integer.parseInt(request.getParameter("tkprice")));
        r.setDiscount(Integer.parseInt(request.getParameter("discount")));
        r.setPaymentMethod(Integer.parseInt(request.getParameter("paymentmethod")));
        r.setNote(request.getParameter("note"));
        r.setDate(formatter.format(date));
        ss.persist(r);
        
        EyeService e = new EyeService();
        e.setCustomer(c);
        e.setEyesphl(Double.parseDouble(request.getParameter("SPHL")));
        e.setEyesphr(Double.parseDouble(request.getParameter("SPHR")));
        e.setEyecylr(Double.parseDouble(request.getParameter("CYLR")));
        e.setEyecyll(Double.parseDouble(request.getParameter("CYLL")));
        e.setEyeaxr(Integer.parseInt(request.getParameter("AXR")));
        e.setEyeaxl(Integer.parseInt(request.getParameter("AXL")));
        e.setEyeadd(Double.parseDouble(request.getParameter("ADD")));
        e.setEyepd(Integer.parseInt(request.getParameter("PD")));
        e.setEyeapproved(request.getParameter("approved"));
        e.setEyedatetime(formatter.format(date));
        ss.persist(e);
        ss.getTransaction().commit();
        client.SendRequest(e, c, r);
        response.sendRedirect("home");
        
       
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Create.class.getName()).log(Level.SEVERE, null, ex);
        }
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
