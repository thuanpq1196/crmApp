package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConfig {
	public static Connection getConnecttion() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Sai tên thư viện");
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/crm_app","root","admin123");
		} catch (SQLException e) {
			System.out.println("Lỗi kết nối cơ sơ dữ liệu");
		}
		return conn;
	}
}
