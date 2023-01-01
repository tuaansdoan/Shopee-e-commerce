package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import config.Connect;
import entity.History;
import entity.ShoppingCart;

public class HistoryService {
	public HistoryService() {
	}

	public List<History> displayHistoryShopping(String user) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		List<History> historyList = new ArrayList<History>();
		try {
			// query data
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT  invoice, orderDate FROM shoppingcart WHERE username = ? and payment = 1  GROUP BY invoice ORDER BY orderDate DESC, invoice;");
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
			// mapping data sang doi tuong
			while (rs.next()) {
				String invoice = rs.getString(1);
				Date orderDate = rs.getTimestamp(2);

				History historyObj = new History();

				historyObj.setInvoice(invoice);
				historyObj.setOrderDate(orderDate);

				PreparedStatement stmt1 = conn.prepareStatement(
						"SELECT p.pID, pImage, productName, price, quantity, invoice, orderDate FROM shoppingcart s JOIN product P ON s.pID = p.pID WHERE username = ? and payment=1  and invoice = ?;");
				stmt1.setString(1, user);
				stmt1.setString(2, invoice);
				
				ResultSet rs1 = stmt1.executeQuery();
				
				List<ShoppingCart> cartList = new ArrayList<ShoppingCart>();
				
				while (rs1.next()) {
					int ID1 = rs1.getInt(1);
					String image1 = rs1.getString(2);
					String productName1 = rs1.getString(3);
					double price1 = rs1.getDouble(4);
					int quantt1 = rs1.getInt(5);
					String invoice1 = rs1.getString(6);
					Date orderDate1 = rs1.getTimestamp(7);
					
					ShoppingCart cartObj = new ShoppingCart();

					cartObj.setId(ID1);
					cartObj.setImage(image1);
					cartObj.setProductName(productName1);
					cartObj.setPrice(price1);
					cartObj.setQuantity(quantt1);
					cartObj.setInvoice(invoice1);
					cartObj.setOrderDate(orderDate1);
					
					cartList.add(cartObj);
				}

				historyObj.setShoppingCart(cartList);
				
				historyList.add(historyObj);
				
			}
			return historyList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
		return Collections.emptyList();
	}
}
