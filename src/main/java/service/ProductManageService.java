package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.Connect;
import entity.Product;

public class ProductManageService {
	public ProductManageService() {}
	
	public void deleteProduct(String pid) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		try {
			// query data
			PreparedStatement stmt = conn
					.prepareStatement("DELETE FROM product WHERE pID = ?");
			stmt.setString(1, pid);
			stmt.executeUpdate();
			// mapping data sang doi tuong
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
	}
	
	public void addNewProduct(String namePrdct, double pricePrdct, String img, int cID) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		try {
			// query data
			if (img.isBlank()) {
				PreparedStatement stmt = conn
						.prepareStatement("INSERT INTO product(productName, price, caID) VALUES (?,?,?);");
				stmt.setString(1, namePrdct);
				stmt.setDouble(2, pricePrdct);
				stmt.setInt(3, cID);
				stmt.executeUpdate();
			} else {
				PreparedStatement stmt = conn
						.prepareStatement("INSERT INTO product(productName, price, pImage, caID) VALUES (?,?,?,?);");
				stmt.setString(1, namePrdct);
				stmt.setDouble(2, pricePrdct);
				stmt.setString(3, img);
				stmt.setInt(4, cID);
				stmt.executeUpdate();
			}
			
			// mapping data sang doi tuong
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
	}
	
	public void editProduct(int idPrdct, String namePrdct, double pricePrdct, String img) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		
		try {
			// query data
			
			if (img.isBlank()) {
				PreparedStatement stmt = conn
						.prepareStatement("UPDATE product SET productName = ? , price = ? WHERE pID = ?;");
				
				stmt.setString(1, namePrdct);
				stmt.setDouble(2, pricePrdct);
				stmt.setInt(3, idPrdct);
				System.out.println(namePrdct);
				stmt.executeUpdate();
			} else {
				PreparedStatement stmt = conn
						.prepareStatement("UPDATE product SET productName = ? , price = ?, pImage = ? WHERE pID = ?;");
				
				stmt.setString(1, namePrdct);
				stmt.setDouble(2, pricePrdct);
				stmt.setString(3, img);
				stmt.setInt(4, idPrdct);
				System.out.println(namePrdct);
				stmt.executeUpdate();
			}
			
			// mapping data sang doi tuong
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
	}
	
	
}
