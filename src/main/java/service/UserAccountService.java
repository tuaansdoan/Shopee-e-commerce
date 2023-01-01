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

public class UserAccountService {
	public List<CustomerInfo> displayAllUser() {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		List<CustomerInfo> userList = new ArrayList<CustomerInfo>();
		try {
			// query data
			PreparedStatement stmt = conn.prepareStatement("select * from customerlogin");
			ResultSet rs = stmt.executeQuery();

			// mapping data sang doi tuong
			while (rs.next()) {
				String username = rs.getString(1);
				String image = rs.getString(3);
				boolean admin = rs.getBoolean(4);
				boolean status = rs.getBoolean(5);

				CustomerInfo userObj = new CustomerInfo();

				userObj.setUsername(username);
				userObj.setUserImage(image);
				userObj.setAdmin(admin);
				userObj.setStatus(status);

				userList.add(userObj);

			}
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
		return Collections.emptyList();
	}

	public void updateUserStatus(String user, String status) {
		// tao ket noi voi database
		Connection conn = Connect.openConnect();
		try {
			// query data
			PreparedStatement stmt = conn.prepareStatement("UPDATE customerlogin SET `status` = ? WHERE username = ?;");
			stmt.setString(1, status);
			stmt.setString(2, user);

			stmt.executeUpdate();	

			// mapping data sang doi tuong
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnect();
		}
	}
}
