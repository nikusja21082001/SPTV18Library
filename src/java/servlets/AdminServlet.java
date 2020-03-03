/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.History;
import entity.Reader;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.BookFacade;
import session.HistoryFacade;
import session.ReaderFacade;

/**
 *
 * @author user
 */
@WebServlet(name = "AdminServlet", urlPatterns = {
    "/showNewBook",
    "/addBook",
    "/showNewReader",
    "/addReader",
    "/showTakeBook",
    "/createHistory",
    "/showReturnBook",
    "/returnBook",
    
})
public class AdminServlet extends HttpServlet {
    @EJB private BookFacade bookFacade;
    @EJB private ReaderFacade readerFacade;
    @EJB private HistoryFacade historyFacade;
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
            case "/showNewBook":
                request.getRequestDispatcher("/showNewBook.jsp")
                        .forward(request, response);
                break;
            case "/addBook":
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                String publishedYear = request.getParameter("publishedYear");
                String quantity = request.getParameter("quantity");
                if(title == null || "".equals(title)
                        || author == null || "".equals(author)
                        || publishedYear == null || "".equals(publishedYear)
                        || quantity == null || "".equals(quantity)){
                    request.setAttribute("title",title);
                    request.setAttribute("author",author);
                    request.setAttribute("publishedYear",publishedYear);
                    request.setAttribute("quantity",quantity);
                    request.setAttribute("info", "Заполните все поля!");
                    request.getRequestDispatcher("/showNewBook")
                            .forward(request, response);
                }
                Book book = new Book(title, author,
                        Integer.parseInt(publishedYear), 
                        Integer.parseInt(quantity)
                );
                bookFacade.create(book);
                request.setAttribute("info", "Книга \""+book.getTitle()+"\" добавлена в базу");
                request.getRequestDispatcher("/index")
                        .forward(request, response);
                break;
            case "/showNewReader":
                request.getRequestDispatcher("/showNewReader.jsp")
                        .forward(request, response);
                break;
            case "/addReader":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                
                if(firstname == null || "".equals(firstname)
                        || lastname == null || "".equals(lastname)
                        || phone == null || "".equals(phone)){
                    request.setAttribute("firstname",firstname);
                    request.setAttribute("lastname",lastname);
                    request.setAttribute("phone",phone);
                    
                    request.setAttribute("info", "Заполните все поля!");
                    request.getRequestDispatcher("/showNewReader")
                            .forward(request, response);
                }
                Reader reader = new Reader(firstname, lastname, phone);
                readerFacade.create(reader);
                request.setAttribute("info", 
                        "Читатель \""+reader.getFirstname() 
                                + " " 
                                + reader.getLastname() 
                                + "\" добавлена в базу"
                );
                request.getRequestDispatcher("/index")
                        .forward(request, response);
                break;  
            case "/showTakeBook":
                List<Book> listBooks = bookFacade.findAll();
                List<Reader> listReaders = readerFacade.findAll();
                request.setAttribute("listBooks", listBooks);
                request.setAttribute("listReaders", listReaders);
                request.getRequestDispatcher("/showTakeBook.jsp")
                        .forward(request, response);
                break;
            case "/createHistory":
                String readerId = request.getParameter("readerId");
                String bookId = request.getParameter("bookId");
                if(readerId == null || "".equals(readerId)
                        || bookId == null || "".equals(bookId)){
                    request.setAttribute("readerId", readerId);
                    request.setAttribute("bookId", bookId);
                    request.setAttribute("info", "Выберите и читателя и книгу");
                    request.getRequestDispatcher("/showTakeBook")
                            .forward(request, response);
                }
                reader = readerFacade.find(Long.parseLong(readerId));
                book = bookFacade.find(Long.parseLong(bookId));
                Calendar c = Calendar.getInstance();
                if(book.getCount()-1 >= 0){
                    History history = new History(book, reader, c.getTime(), null);
                    book.setCount(book.getCount()-1);
                    bookFacade.edit(book);
                    historyFacade.create(history);
                    request.setAttribute("info", "Читателю "
                                            + reader.getFirstname()
                                            + " "
                                            + reader.getLastname()
                                            + " выдана книга: "
                                            + book.getTitle()
                                    );
                }else{
                    request.setAttribute("info", "Нет в наличии данной книги");
                }
                request.getRequestDispatcher("/index")
                            .forward(request, response);
                break;
            case "/showReturnBook":
                List<History> listHistories = historyFacade.findByReturnNull();
                request.setAttribute("listHistories", listHistories);
                request.getRequestDispatcher("/showReturnBook.jsp")
                            .forward(request, response);
                break;
            case "/returnBook":
                String historyId = request.getParameter("historyId");
                if(historyId == null || "".equals(historyId)){
                    request.setAttribute("info", "Выберите книгу!");
                    request.getRequestDispatcher("/showReturnBook")
                            .forward(request, response);
                    break;
                }
                History history = historyFacade.find(Long.parseLong(historyId));
                book = history.getBook();
                boolean isNotReturnBook = book.getQuantity() >= book.getCount()+1;
                if(!isNotReturnBook){//даст true,если isNotReturnBook == false, 
                                     //т.е. все книги уже возвращены
                    request.setAttribute("info", "Все книги с таким названием уже возвращены!");
                    request.getRequestDispatcher("/showReturnBook")
                            .forward(request, response);
                    break; 
                }
                book.setCount(book.getCount()+1);
                bookFacade.edit(book);
                history.setReturnBook(Calendar.getInstance().getTime());
                historyFacade.edit(history);
                request.setAttribute("info", "книга возвращена!");
                    request.getRequestDispatcher("/showReturnBook")
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
