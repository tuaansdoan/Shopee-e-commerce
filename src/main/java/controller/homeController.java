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

import entity.Category;
import entity.CustomerInfo;
import entity.Product;
import entity.ShoppingCart;
import service.HomeService;
import service.ShoppingCartService;

@WebServlet("/home")
public class homeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HomeService homeService;
	private ShoppingCartService shoppingCartService;

	@Override
	public void init() throws ServletException {
		this.homeService = new HomeService();
		this.shoppingCartService = new ShoppingCartService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		System.out.println("home [get]");
		
		String productID = request.getParameter("ID");
		String searchID = request.getParameter("search");
		String page = request.getParameter("page");
		String categoryID = request.getParameter("category");

		int totalPage = homeService.countTotalProduct();
		int numPrdct = 12; // tong so luong san pham tren 1 trang
		totalPage = totalPage / numPrdct + 1;

		List<Category> categoryList = homeService.categoryAllProduct();
		request.setAttribute("categoryList", categoryList);
		
		List<Product> productSession = homeService.findAllProduct();
		session.setAttribute("productSession", productSession);
		

		// create cart session
		CustomerInfo sessionCustomer = (CustomerInfo) session.getAttribute("customerLogin");
		
		
		if (Objects.nonNull(sessionCustomer)) {
			List<ShoppingCart> userCartList = shoppingCartService
					.displayAllProductInCart(sessionCustomer.getUsername());
			List<ShoppingCart> notifyList = shoppingCartService.notification(sessionCustomer.getUsername());
			session.setAttribute("notifyList", notifyList);
			session.setAttribute("userCartList", userCartList);
		}
		

		if (Objects.nonNull(categoryID)) {
			List<Product> productList = homeService.displayProductByCategory(categoryID);
			request.setAttribute("productList", productList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);

		} else if (Objects.nonNull(searchID) && searchID != "") {
			List<Product> productList = homeService.searchAllProduct(searchID);
			request.setAttribute("productList", productList);
			request.setAttribute("keyWord", searchID);

			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);

		} else if (Objects.nonNull(productID)) {
			Product productDetail = homeService.displayProductById(productID);
			request.setAttribute("productDetail", productDetail);

			RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
			dispatcher.forward(request, response);

		} else if (Objects.nonNull(page)) {
			List<Product> productList = homeService.findAllProduct(page, numPrdct);

			int pg = Integer.parseInt(page);
			request.setAttribute("pg", pg);
			request.setAttribute("productList", productList);
			request.setAttribute("totalPage", totalPage);

			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);

		} else {
			page = "1";
			List<Product> productList = homeService.findAllProduct(page, numPrdct);

			int pg = Integer.parseInt(page);
			request.setAttribute("pg", pg);
			request.setAttribute("productList", productList);
			request.setAttribute("totalPage", totalPage);

			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		 session.invalidate(); 
			/*
			 * session.removeAttribute("customerLogin");
			 * session.removeAttribute("userCartList"); session.removeAttribute("invoice");
			 */
		response.sendRedirect("home");
		
	}

}
