package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import config.Connect;
import entity.Category;
import entity.Product;

public class HomeService {

	public HomeService() {
	}

	public List<Product> findAllProduct() {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		List<Product> productList = new ArrayList<Product>();
		try {
			// query data
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from product");
			// mapping data sang doi tuong
			while (rs.next()) {
				int id = rs.getInt(1);
				String productName = rs.getString(2);
				double price = rs.getDouble(3);
				int sold = rs.getInt(4);
				String image = rs.getString(5);

				Product productObj = new Product();
				productObj.setId(id);
				productObj.setProductName(productName);
				productObj.setPrice(price);
				productObj.setSold(sold);
				productObj.setImage(image);

				productList.add(productObj);

			}
			return productList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
		return Collections.emptyList();
	}

	public List<Product> findAllProduct(String page, int numOfPd) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		List<Product> productList = new ArrayList<Product>();
		try {

			PreparedStatement stmt = conn.prepareStatement("SELECT pID, productName, price, sold, pImage, cName  FROM product p JOIN category c ON p.caID = c.caID LIMIT ?,?;");
			stmt.setInt(1, (Integer.parseInt(page) - 1) * numOfPd);
			stmt.setInt(2, numOfPd);
			ResultSet rs = stmt.executeQuery();
			
			// mapping data sang doi tuong
			while (rs.next()) {
				int id = rs.getInt(1);
				String productName = rs.getString(2);
				double price = rs.getDouble(3);
				int sold = rs.getInt(4);
				String image = rs.getString(5);
				String cName = rs.getString(6);

				Product productObj = new Product();

				productObj.setId(id);
				productObj.setProductName(productName);
				productObj.setPrice(price);
				productObj.setSold(sold);
				productObj.setImage(image);
				productObj.setcName(cName);
				
				productList.add(productObj);

			}
			return productList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
		return Collections.emptyList();
	}

	public int countTotalProduct() {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		try {
			// query data
			PreparedStatement stmt = conn.prepareStatement("select count(*) from product");
			ResultSet rs = stmt.executeQuery();
			System.out.println();
			int page = 0;
			while (rs.next()) {
				page = rs.getInt(1);
			}
			return page;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
		return 0;
	}

	public Product displayProductById(String pId) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		Product productObj = new Product();
		try {
			// query data
			PreparedStatement stmt = conn.prepareStatement("select * from product WHERE pID = ?");
			stmt.setString(1, pId);
			ResultSet rs = stmt.executeQuery();

			// mapping data sang doi tuong
			while (rs.next()) {
				int id = rs.getInt(1);
				String productName = rs.getString(2);
				double price = rs.getDouble(3);
				int sold = rs.getInt(4);
				String image = rs.getString(5);

				productObj.setId(id);
				productObj.setProductName(productName);
				productObj.setPrice(price);
				productObj.setSold(sold);
				productObj.setImage(image);

			}
			return productObj;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
		return null;
	}

	public List<Product> searchAllProduct(String searchID) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		List<Product> productList = new ArrayList<Product>();
		try {

			String[] splitStr = searchID.trim().split("\\s+");

			// query data

			for (int i = 0; i < splitStr.length; i++) {
				PreparedStatement stmt = conn
						.prepareStatement("SELECT DISTINCT * FROM product WHERE productName REGEXP ?");
				stmt.setString(1, splitStr[i]);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					String productName = rs.getString(2);
					double price = rs.getDouble(3);
					int sold = rs.getInt(4);
					String image = rs.getString(5);

					Product productObj = new Product();
					productObj.setId(id);
					productObj.setProductName(productName);
					productObj.setPrice(price);
					productObj.setSold(sold);
					productObj.setImage(image);

					productList.add(productObj);

				}
			}

			for (int i = 0; i < productList.size() - 1; i++) {
				for (int j = i + 1; j < productList.size(); j++) {
					if (productList.get(i).getId() == productList.get(j).getId()) {
						productList.remove(j);
					}
				}
			}

			// mapping data sang doi tuong
			return productList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
		return Collections.emptyList();

	}
	
	public List<Category> categoryAllProduct(){
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		List<Category> categoryList = new ArrayList<Category>();
		try {
			// query data
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from category");
			// mapping data sang doi tuong
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String image = rs.getString(3);

				Category categoryObj = new Category();
				categoryObj.setcID(id);
				categoryObj.setcName(name);
				categoryObj.setcImage(image);

				categoryList.add(categoryObj);

			}
			return categoryList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
		return Collections.emptyList();
	}
	
	public List<Product> displayProductByCategory(String cID){
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		List<Product> productList = new ArrayList<Product>();
		try {
			// query data
			PreparedStatement stmt = conn
					.prepareStatement("SELECT * FROM product WHERE caID = ?");
			stmt.setString(1, cID);
			ResultSet rs = stmt.executeQuery();
			
			// mapping data sang doi tuong
			while (rs.next()) {
				int id = rs.getInt(1);
				String productName = rs.getString(2);
				double price = rs.getDouble(3);
				int sold = rs.getInt(4);
				String image = rs.getString(5);

				Product productObj = new Product();
				productObj.setId(id);
				productObj.setProductName(productName);
				productObj.setPrice(price);
				productObj.setSold(sold);
				productObj.setImage(image);

				productList.add(productObj);

			}
			return productList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
		return Collections.emptyList();
	}
	
}
