package controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import entity.Category;
import entity.Product;
import service.HomeService;
import service.ProductManageService;

@WebServlet("/product-manage")
@MultipartConfig()
public class productManageCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HomeService homeService;
	private ProductManageService productManageService;

	@Override
	public void init() throws ServletException {
		this.homeService = new HomeService();
		this.productManageService = new ProductManageService();
	}

	public productManageCtrl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		

		String page = request.getParameter("page");

		int totalPage = homeService.countTotalProduct();
		int numPrdct = 5;
		if (totalPage % numPrdct != 0) {
			totalPage = totalPage / numPrdct + 1;
		} else {
			totalPage = totalPage / numPrdct;
		}


		List<Category> categoryList = homeService.categoryAllProduct();
		request.setAttribute("categoryList", categoryList);
		int pg = 1;

		if (Objects.nonNull(page)) {
			List<Product> productList = homeService.findAllProduct(page, numPrdct);

			pg = Integer.parseInt(page);
			request.setAttribute("pg", pg);
			request.setAttribute("productList", productList);
			request.setAttribute("totalPage", totalPage);

			RequestDispatcher dispatcher = request.getRequestDispatcher("productManage.jsp");
			dispatcher.forward(request, response);
		} else {
			page = "1";
			List<Product> productList = homeService.findAllProduct(page, numPrdct);

			request.setAttribute("pg", pg);
			request.setAttribute("productList", productList);
			request.setAttribute("totalPage", totalPage);

			RequestDispatcher dispatcher = request.getRequestDispatcher("productManage.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		// delete
		String id = request.getParameter("ID");

		if (Objects.nonNull(id)) {
			productManageService.deleteProduct(id);
		}

		// add new and upload image
		String productName = request.getParameter("name-product");
		String p = request.getParameter("price");
		String cID = request.getParameter("choose-cID");

		if (Objects.nonNull(productName) && Objects.nonNull(p)) {
			Part part = request.getPart("image");
			String realPath = request.getServletContext().getRealPath("/img/product");
			String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			if (!fileName.isBlank()) {
				part.write(realPath + "/" + fileName);
			}
			double price = Double.parseDouble(p);
			int cateID = Integer.parseInt(cID);
			productManageService.addNewProduct(productName, price, fileName, cateID);
		}

		// edit
		String eproductName = request.getParameter("eProductName");
		String price = request.getParameter("ePrice");
		String eid = request.getParameter("eId");

		if (Objects.nonNull(price) && Objects.nonNull(eproductName) && Objects.nonNull(eid)) {
			double ePrice = Double.parseDouble(price);
			int eID = Integer.parseInt(eid);
			request.setCharacterEncoding("UTF-8");
			Part partEdit = request.getPart("imageEdit");
			String realPathEdit = request.getServletContext().getRealPath("/img/product");
			String fileNameEdit = Paths.get(partEdit.getSubmittedFileName()).getFileName().toString();
			if (!fileNameEdit.isBlank()) {
				partEdit.write(realPathEdit + "/" + fileNameEdit);
			}

			productManageService.editProduct(eID, eproductName, ePrice, fileNameEdit);
		}

		String page = request.getParameter("page");
		if (Objects.nonNull(page)) {
			response.sendRedirect("product-manage?page=" + page);
		} else {
			response.sendRedirect("product-manage");
		}

		// upload image

	}

}
