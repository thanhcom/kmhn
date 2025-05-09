/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package thanhtrang.online.thanhtrang.controller.authenticate;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Objects;
import thanhtrang.online.thanhtrang.Model.Admin;
import thanhtrang.online.thanhtrang.dto.AdminDao;

/**
 *
 * @author thanhcom
 */
@WebServlet(name = "DoLogin", urlPatterns = {"/dologin"})
public class DoLogin extends HttpServlet {

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
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        Admin A = AdminDao.getInstance().FinByUserName(user);        
        if (!Objects.isNull(A)) {
            BCrypt.Result result = BCrypt.verifyer().verify(pass.toCharArray(), A.getPass());
            if (user.equals(A.getUser()) && result.verified) {
                HttpSession session = request.getSession();
                session.setAttribute("account", A);
                response.sendRedirect("home");
            } else {
                request.setAttribute("ERR", "Nhập Sai Mật Khẩu ");
                request.getRequestDispatcher("login").forward(request, response);
            }
        } else {
            request.setAttribute("ERR", "Tài Khoản Không Tồn Tại  ");
            request.getRequestDispatcher("login").forward(request, response);

        }

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
