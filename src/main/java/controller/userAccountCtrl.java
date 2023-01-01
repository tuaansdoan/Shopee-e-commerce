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
import service.LoginService;
import service.UserAccountService;

@WebServlet("/user-account")
public class userAccountCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAccountService userAccountService;

	@Override
	public void init() throws ServletException {
		this.userAccountService = new UserAccountService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		CustomerInfo customerLogin = (CustomerInfo) httpSession.getAttribute("customerLogin");

		if (Objects.nonNull(customerLogin) && customerLogin.isAdmin()) {
			List<CustomerInfo> userList = userAccountService.displayAllUser();
			request.setAttribute("userList", userList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("user.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String active = request.getParameter("activate");
		String deactive = request.getParameter("deactivate");
		String username = request.getParameter("username");
		
		if (active != null) {
			userAccountService.updateUserStatus(username, active);
		}

		if (deactive != null) {
			userAccountService.updateUserStatus(username, deactive);
		}

		response.sendRedirect("user-account");
	}

}
