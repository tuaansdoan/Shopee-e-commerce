package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.RegisterService;

/**
 * Servlet implementation class resgisterController
 */
@WebServlet("/register")
public class resgisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RegisterService registerService;
   
    public resgisterController() {
        super();
       
    }
    @Override
	public void init() throws ServletException {
    	this.registerService = new RegisterService();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("register.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		registerService.register(username, password);
		
		response.sendRedirect("login.jsp");
	}

}
