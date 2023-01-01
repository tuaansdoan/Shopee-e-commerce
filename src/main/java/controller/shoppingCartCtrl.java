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

/*import com.mysql.cj.Session;*/

import entity.CustomerInfo;
import entity.ShoppingCart;
import service.HomeService;
import service.ShoppingCartService;

@WebServlet("/shopping-cart")
public class shoppingCartCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShoppingCartService shoppingCartService;

	@Override
	public void init() throws ServletException {
		this.shoppingCartService = new ShoppingCartService();
		new HomeService();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		CustomerInfo sessionCustomer = (CustomerInfo) session.getAttribute("customerLogin");

		String cartLogin = request.getParameter("cart-login");
		session.setAttribute("cartLogin", cartLogin);

		if (Objects.nonNull(sessionCustomer)) {

			// list all items in cart
			List<ShoppingCart> cartList = shoppingCartService.displayAllProductInCart(sessionCustomer.getUsername());
			request.setAttribute("cartList", cartList);

			if (!cartList.isEmpty()) {
				String invoice = cartList.get(0).getInvoice();
				session.setAttribute("invoice", invoice);
			} else {
				session.removeAttribute("invoice");
			}

			// sum Cart
			double sum = shoppingCartService.sumCart(sessionCustomer.getUsername());
			request.setAttribute("sum", sum);

			RequestDispatcher dispatcher = request.getRequestDispatcher("shopping-cart.jsp");
			dispatcher.forward(request, response);

		} else {
			response.sendRedirect("login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		CustomerInfo sessionCustomer = (CustomerInfo) session.getAttribute("customerLogin");
		
		String productID = request.getParameter("ID");
		String cateID = request.getParameter("cID");
		String plusQntt = request.getParameter("plus-quantt");
		String minusQntt = request.getParameter("minus-quantt");
		
		
		// add to cart
		if (Objects.isNull(sessionCustomer)) {
			response.sendRedirect("login.jsp");
			
		} else if(Objects.nonNull(productID)) {
			String username = sessionCustomer.getUsername();
			shoppingCartService.addToCart(username, productID);
			response.sendRedirect("home?ID=" + productID);
			
		} else if (Objects.nonNull(cateID) && Objects.nonNull(plusQntt)) {
			String username = sessionCustomer.getUsername();
			shoppingCartService.addToCart(username, cateID);
			response.sendRedirect("shopping-cart");
			
		} else if (Objects.nonNull(cateID) && Objects.nonNull(minusQntt))  {
			String username = sessionCustomer.getUsername();
			shoppingCartService.minusToCart(username, cateID);
			response.sendRedirect("shopping-cart");
		} 
		
		// delete cart item
		String deletCartItem = request.getParameter("deleteCartItem");
		if (Objects.nonNull(sessionCustomer) && Objects.nonNull(deletCartItem)) {
			String username = sessionCustomer.getUsername();
			shoppingCartService.deleteCartItem(username, cateID);
			response.sendRedirect("shopping-cart");
		}
		
		// delete all items
		String deleteAllItem = request.getParameter("deleteAllItem");
		if (Objects.nonNull(sessionCustomer) && Objects.nonNull(deleteAllItem)) {
			String username = sessionCustomer.getUsername();
			shoppingCartService.deleteAllItem(username);
			response.sendRedirect("shopping-cart");
		}

		// mua ngay từ trang product detail
		String muaID = request.getParameter("muaID");
		
		if (Objects.nonNull(sessionCustomer) && Objects.nonNull(muaID)){
			String username = sessionCustomer.getUsername();
			shoppingCartService.addToCart(username, muaID);
			
			List<ShoppingCart> userCartList = shoppingCartService.displayAllProductInCart(username);
			session.setAttribute("userCartList", userCartList);
			
			response.sendRedirect("shopping-cart");
		}
		// mua ngay tại shopping cart and send email
		String payment = request.getParameter("payment");

		if (Objects.nonNull(payment) && Objects.nonNull(sessionCustomer)){
			String username = sessionCustomer.getUsername();
			shoppingCartService.paymentOnCart(username);
			
			List<ShoppingCart> userCartList = shoppingCartService.displayAllProductInCart(username);
			session.setAttribute("userCartList", userCartList);
			
			List<ShoppingCart> notifyList = shoppingCartService.notification(username);
			session.setAttribute("notifyList", notifyList);
			
			/*
			 * Properties props = new Properties();
			 * props.put("mail.smtp.starttls.enable","true"); props.put("mail.smtp.auth",
			 * "true"); // If you need to authenticate
			 * props.put("mail.smtp.socketFactory.port", "587");
			 * props.put("mail.smtp.socketFactory.fallback", "false");
			 * props.put("mail.smtp.host", "localhost");
			 */
//			props.put("mail.smtp.port", "587");
//			props.put("mail.smtp.auth","true");
//			props.put("mail.smtp.starttls","true");
//			props.put("mail.smtp.user","tuandoan.potn@gmail.com");
//			props.put("password","vkctwczszuvdltmu");
		

			/*
			 * final String user = "tuandoan.potn@gmail.com";// final String password =
			 * "vkctwczszuvdltmu"; try{ Session sessionemail =
			 * Session.getDefaultInstance(props, new Authenticator(){ protected
			 * PasswordAuthentication getPasswordAuthentication() { return new
			 * PasswordAuthentication(user, password); }});
			 * 
			 * // -- Create a new message -- MimeMessage msg = new
			 * MimeMessage(sessionemail);
			 * 
			 * // -- Set the FROM and TO fields -- msg.setFrom(new InternetAddress(user));
			 * 
			 * msg.setRecipients(Message.RecipientType.TO,
			 * InternetAddress.parse("tuandoan.potn@gmail.com",false));
			 * msg.setSubject("Hello"); msg.setText("How are you"); msg.setSentDate(new
			 * Date()); Transport.send(msg); System.out.println("Message sent."); }catch
			 * (MessagingException e){ System.out.println("Erreur d'envoi, cause: " + e); }
			 */
			
			response.sendRedirect("payment.jsp?payment=1");
		}

}

}
