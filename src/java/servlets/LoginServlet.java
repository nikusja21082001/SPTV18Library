/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.Reader;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.BookFacade;
import session.ReaderFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "LoginServlet",loadOnStartup = 1, urlPatterns = {
    "/listBooks",
    "/showLogin",
    "/index",
    "/login",
    "/listReaders",
})
public class LoginServlet extends HttpServlet {
@EJB private BookFacade bookFacade;
@EJB private ReaderFacade readerFacade;
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        switch (request.getServletPath()) {
            case "/index":
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;
            case "/listBooks":
                List<Book> listBooks= bookFacade.findAll();
                request.setAttribute("listBooks", listBooks);
                request.setAttribute("info", "Привет из IndexServlet");
                request.getRequestDispatcher("/listBooks.jsp")
                        .forward(request, response);
                break;
            case "/listReaders":
                List<Reader> listReaders= readerFacade.findAll();
                request.setAttribute("listReaders", listReaders);
                request.setAttribute("info", "Привет из IndexServlet");
                request.getRequestDispatcher("/listReaders.jsp")
                        .forward(request, response);
                break;
            case "/showLogin":
                request.setAttribute("info", "Привет из LoginServlet");
                request.getRequestDispatcher("/showLogin.jsp")
                        .forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                if("admin".equals(login)
                        && "123123".equals(password)){
                    request.setAttribute("info", "Привет "+login);
                } else{
                    request.setAttribute("info", "Неправелньный логин или пароль");
                }   
                request.getRequestDispatcher("/index")
                        .forward(request, response);
                break;
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
