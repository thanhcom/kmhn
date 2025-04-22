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
import thanhtrang.online.thanhtrang.Model.EyeService;
import thanhtrang.online.thanhtrang.dto.CustomerDao;

/**
 *
 * @author thanhcom
 */
@WebServlet(name = "receiptbycustomer_eyeonlyeditSave", urlPatterns = {"/receiptbycustomer_eyeonlyeditsave"})
public class receiptbycustomer_eyeonlyeditSave extends HttpServlet {

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
       String eid = request.getParameter("eid");
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Session ss = HibnateUtils.getFactory().openSession();        
        thanhtrang.online.thanhtrang.Model.Customer c= CustomerDao.getInstance().FinById(Integer.parseInt(cid));
        ss.getTransaction().begin();
//        Customer c= new Customer();
//        c.setId(Integer.parseInt(cid));
//        c.setName(request.getParameter("name"));
//        c.setPhone(request.getParameter("phone"));
//        c.setAddress(request.getParameter("address"));
//        c.setGender(Integer.parseInt(request.getParameter("gender")));
//        c.setAge(Integer.parseInt(request.getParameter("age")));
//        ss.merge(c);
        EyeService e = new EyeService();
        e.setEyeid(Integer.parseInt(eid));
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
        //e.setEyedatetime(formatter.format(date));
        ss.merge(e);
        ss.getTransaction().commit();
        request.getRequestDispatcher("customerdetail?id="+c.getId()).forward(request, response);
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
