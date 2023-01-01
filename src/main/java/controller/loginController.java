package controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.CustomerInfo;
import entity.Product;
import service.HomeService;
import service.LoginService;

@WebServlet("/login")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService;

	@Override
	public void init() throws ServletException {
		this.loginService = new LoginService();
	}

	public loginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		CustomerInfo customerLogin = (CustomerInfo) httpSession.getAttribute("customerLogin");
		String cartlogin = (String) httpSession.getAttribute("cart-login");

		if (Objects.nonNull(customerLogin)	&& Objects.nonNull(cartlogin)) {
			response.sendRedirect("shopping-cart");
		} else if (Objects.nonNull(httpSession.getAttribute("customerLogin"))) {
			response.sendRedirect("home");
		} else {
			response.sendRedirect("login.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		CustomerInfo customerLogin = loginService.logIn(username, password);

		if (customerLogin.getUsername() != null && customerLogin.getPassword() != null
				&& Objects.nonNull(httpSession.getAttribute("cartLogin"))) {
			httpSession.setAttribute("customerLogin", customerLogin);
			response.sendRedirect("shopping-cart");
		} else if (customerLogin.getUsername() != null && customerLogin.getPassword() != null) {
			httpSession.setAttribute("customerLogin", customerLogin);
			response.sendRedirect("home");
		} else {
			response.sendRedirect("login.jsp");
		}

	}

}
