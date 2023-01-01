package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import config.Connect;
import entity.CustomerInfo;

public class RegisterService {
	public RegisterService() {
	}

	public void register(String user, String pass) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		try {
			// query data
			PreparedStatement stmt = conn
					.prepareStatement("INSERT INTO customerLogin(username, password, email) VALUES (?, ?, ?); ");
			stmt.setString(1, user);
			stmt.setString(2, pass);
			stmt.setString(3, "tuandoan.potn@gmail.com");
			stmt.executeUpdate();
			// mapping data sang doi tuong
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
	}
}
