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
import entity.History;
import service.HistoryService;

@WebServlet("/history")
public class historyCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HistoryService historyService;

	@Override
	public void init() throws ServletException {
		this.historyService = new HistoryService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		CustomerInfo sessionCustomer = (CustomerInfo) session.getAttribute("customerLogin");

		if (Objects.nonNull(sessionCustomer)) {
			List<History> historyList = historyService.displayHistoryShopping(sessionCustomer.getUsername());
			request.setAttribute("historyList", historyList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("historyShopping.jsp");
			dispatcher.forward(request, response);

		} else {
			response.sendRedirect("login");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
