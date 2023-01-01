package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import config.Connect;
import entity.ShoppingCart;

public class ShoppingCartService {
	public ShoppingCartService() {
	}

	public void addToCart(String user, String id) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		try {
			// query data
			PreparedStatement stmt = conn
					.prepareStatement("SELECT * FROM shoppingcart WHERE username = ? and payment = 0");
			stmt.setString(1, user);
			/* stmt.setString(2, id); */
			ResultSet rs = stmt.executeQuery();

			String pID = null;
			String invoice = null;

			while (rs.next()) {

				if (Objects.nonNull(rs.getString(6))) {
					invoice = rs.getString(6);
				}

				if (id.equalsIgnoreCase(rs.getString(3))) {
					pID = rs.getString(3);
					PreparedStatement stmt2 = conn.prepareStatement(
							"UPDATE shoppingcart SET quantity = quantity + 1 WHERE username = ? and pID = ? and payment = 0");
					stmt2.setString(1, user);
					stmt2.setString(2, id);
					stmt2.executeUpdate();
				}

			}

			if (invoice == null) {
				PreparedStatement stmt1 = conn.prepareStatement(
						"INSERT INTO  shoppingcart  (username, pID, invoice) VALUES (?, ?, FLOOR(RAND()*1000000))");
				stmt1.setString(1, user);
				stmt1.setString(2, id);
				stmt1.executeUpdate();
			}

			if (invoice != null && pID == null) {
				PreparedStatement stmt1 = conn
						.prepareStatement("INSERT INTO  shoppingcart  (username, pID, invoice) VALUES (?, ?, ?)");
				stmt1.setString(1, user);
				stmt1.setString(2, id);
				stmt1.setString(3, invoice);
				stmt1.executeUpdate();
			}


			// mapping data sang doi tuong
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
	}

	public void minusToCart(String user, String id) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		try {
			// query data
			PreparedStatement stmt = conn
					.prepareStatement("SELECT * FROM shoppingcart WHERE username = ? and pID = ? ");
			stmt.setString(1, user);
			stmt.setString(2, id);
			ResultSet rs = stmt.executeQuery();
			int quantt = 0;
			while (rs.next()) {
				quantt = rs.getInt(4);
			}

			if (quantt > 1) {
				PreparedStatement stmt2 = conn.prepareStatement(
						"UPDATE shoppingcart SET quantity = quantity - 1 WHERE username = ? and pID = ?");
				stmt2.setString(1, user);
				stmt2.setString(2, id);
				stmt2.executeUpdate();
			} else {
				PreparedStatement stmt2 = conn
						.prepareStatement("DELETE FROM shoppingcart WHERE username = ? and pID = ?");
				stmt2.setString(1, user);
				stmt2.setString(2, id);
				stmt2.executeUpdate();
			}

			// mapping data sang doi tuong
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
	}

	public List<ShoppingCart> displayAllProductInCart(String user) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		List<ShoppingCart> cartList = new ArrayList<ShoppingCart>();
		try {
			// query data
			PreparedStatement stmt = conn
					.prepareStatement("SELECT p.pID, pImage, productName, price, quantity, invoice, orderDate "
							+ "FROM shoppingcart s JOIN product P ON s.pID = p.pID WHERE username = ? and payment = 0 GROUP BY p.pID");
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
			// mapping data sang doi tuong
			while (rs.next()) {
				int ID = rs.getInt(1);
				String image = rs.getString(2);
				String productName = rs.getString(3);
				double price = rs.getDouble(4);
				int quantt = rs.getInt(5);
				String invoice = rs.getString(6);
				Date orderDate = rs.getTimestamp(7);

				ShoppingCart ShoppingCartObj = new ShoppingCart();

				ShoppingCartObj.setId(ID);
				ShoppingCartObj.setImage(image);
				ShoppingCartObj.setProductName(productName);
				ShoppingCartObj.setPrice(price);
				ShoppingCartObj.setQuantity(quantt);
				ShoppingCartObj.setInvoice(invoice);
				ShoppingCartObj.setOrderDate(orderDate);

				cartList.add(ShoppingCartObj);

			}
			return cartList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
		return Collections.emptyList();
	}

	public void deleteCartItem(String user, String id) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		try {
			PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM shoppingcart WHERE username = ? and pID = ?");
			stmt2.setString(1, user);
			stmt2.setString(2, id);
			stmt2.executeUpdate();

			// mapping data sang doi tuong
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
	}

	public void deleteAllItem(String user) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		try {
			PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM shoppingcart WHERE username = ?");
			stmt2.setString(1, user);
			stmt2.executeUpdate();

			// mapping data sang doi tuong
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}

	}

	public double sumCart(String user) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();

		try {
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT sum(quantity*price) FROM shoppingcart s JOIN product P ON s.pID = p.pID WHERE username = ? and payment = 0 ");
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
			double sum = 0;
			// mapping data sang doi tuong
			if (rs.next()) {
				sum = rs.getDouble(1);
			}
			return sum;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
		return 0;

	}

	public void paymentOnCart(String user) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		try {
			PreparedStatement stmt2 = conn
					.prepareStatement("SELECT * FROM shoppingcart WHERE username = ? and payment = 0;");
			stmt2.setString(1, user);
			ResultSet rs = stmt2.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(3);
				String invoice = rs.getString(6);

				PreparedStatement stmt3 = conn.prepareStatement(
						"UPDATE product SET sold = sold + (SELECT quantity FROM shoppingcart WHERE  username = ? and pID = ? and invoice = ?)  WHERE pID = ?;");
				stmt3.setString(1, user);
				stmt3.setInt(2, id);
				stmt3.setString(3, invoice);
				stmt3.setInt(4, id);
				stmt3.executeUpdate();
			}

			PreparedStatement stmt4 = conn.prepareStatement(
					"UPDATE shoppingcart SET payment = 1,  orderDate = current_timestamp()  WHERE username = ? and payment = 0;");
			stmt4.setString(1, user);
			stmt4.executeUpdate();

			// mapping data sang doi tuong
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}

	}

	public List<ShoppingCart> notification(String user) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		List<ShoppingCart> notifyList = new ArrayList<ShoppingCart>();
		try {
			// query data
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT * FROM shoppingcart WHERE username = ? and payment = 1 GROUP BY invoice ORDER BY orderDate DESC LIMIT 4;");
			stmt.setString(1, user);
			/* stmt.setString(2, id); */
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String invoice = rs.getString(6);
				Date orderDate = rs.getTimestamp(7);
				
				ShoppingCart shoppingCartObj = new ShoppingCart();
				shoppingCartObj.setInvoice(invoice);
				shoppingCartObj.setOrderDate(orderDate);

				notifyList.add(shoppingCartObj);

			}
			return notifyList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
		return Collections.emptyList();
	}
}
