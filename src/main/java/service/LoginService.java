package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import config.Connect;
import entity.CustomerInfo;

public class LoginService {
	public LoginService() {}
	
	public CustomerInfo logIn(String user, String pass) {
		// tao ket noi voi database
				Connection conn = Connect.openConnect();
				 CustomerInfo userLogin = new CustomerInfo();
				try {
					//query data
					PreparedStatement stmt = conn.prepareStatement("SELECT * from customerlogin where username = ? and password = ? and status = 1;" );
					stmt.setString(1, user);
					stmt.setString(2, pass);
					ResultSet rs = stmt.executeQuery();
					//mapping data sang doi tuong
					if (rs.next()) {
						String username = rs.getString(1);
						String password = rs.getString(2);
						boolean admin = rs.getBoolean(4);
						boolean status = rs.getBoolean(5);
						String email = rs.getString(6);
						
						userLogin.setUsername(username);
						userLogin.setPassword(password);
						userLogin.setAdmin(admin);
						userLogin.setStatus(status);
						userLogin.setEmail(email);
					}
					return userLogin; 
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					Connect.closeConnect();
				}
				return null;
		
	}
}
