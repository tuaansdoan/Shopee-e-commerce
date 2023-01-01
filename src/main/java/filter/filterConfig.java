package filter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.CustomerInfo;

@WebFilter("/*")
public class filterConfig extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public filterConfig() {
		super();
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		List<String> URLS = List.of("/home", "/login", "/login.jsp", "/product.jsp", "/register", "/register.jsp",
				"/shopping-cart", "/payment.jsp", "/history");

		List<String> URLS2 = List.of("/product-manage", "/user-account");
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;

		boolean isStaticResourceCSS = httpReq.getRequestURI().startsWith("/Shopee/css");
		boolean isStaticResourceIMG = httpReq.getRequestURI().startsWith("/Shopee/img");

		HttpSession session = httpReq.getSession();
		CustomerInfo sessionVal = (CustomerInfo) session.getAttribute("customerLogin");
		String payment = request.getParameter("payment");
		String path = httpReq.getServletPath(); // lay duong dan hien tai
		
		if (path.contains("error.jsp")) {
			chain.doFilter(request, response);
		} else if (Objects.isNull(sessionVal) && !URLS.contains(path) && !isStaticResourceCSS && !isStaticResourceIMG) {
			/* httpResp.sendError(HttpServletResponse.SC_UNAUTHORIZED); */

			httpResp.sendRedirect("http://localhost:8080/Shopee" + "/error.jsp"); 

			/*
			 * System.out.println("asdsa");
			 * 
			 * 
			 * httpResp.sendRedirect("error");
			 * 
			 * httpResp.setHeader("location", httpReq.getContextPath() + "error.jsp");
			 */

		} else if (Objects.nonNull(sessionVal) && sessionVal.isAdmin() == false && URLS2.contains(path)) {
			/* httpResp.sendError(HttpServletResponse.SC_UNAUTHORIZED); */
			httpResp.sendRedirect("http://localhost:8080/Shopee" + "/error.jsp"); 
		} else if (Objects.equals(path, "/payment.jsp") && payment == null) {
			/* httpResp.sendError(HttpServletResponse.SC_UNAUTHORIZED); */
			httpResp.sendRedirect("http://localhost:8080/Shopee" + "/error.jsp"); 
		} else {
			chain.doFilter(request, response);

		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
