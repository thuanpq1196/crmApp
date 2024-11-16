package crm_app07.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.MysqlConfig;
import crm_app07.entity.JobEntity;
import crm_app07.entity.UserEntity;
import utils.MD5;

public class UserRepository {

	public List<UserEntity> findByEmailAndPassword(String email, String password) {
		List<UserEntity> users = new ArrayList<UserEntity>();
		String sqlQuery ="SELECT u.id FROM users u WHERE u.email = ? AND u.password = ?";
		Connection conn = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet rs  = statement.executeQuery();
			while(rs.next()) {
				UserEntity ue = new UserEntity();
				ue.setId(rs.getInt("id"));
				
				users.add(ue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public List<UserEntity> findAll(){
		List<UserEntity> users = new ArrayList<UserEntity>();
		String sqlQuery = "SELECT u.id, u.email, u.fullname, u.role_id FROM users u";
		Connection conn = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				UserEntity user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setFullName(rs.getString("fullname"));
				user.setRoleId(rs.getInt("role_id"));
				users.add(user);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public int deleteById(int id) {
		int rowDeleted = 0;
		String sqlQuery ="DELETE FROM users u WHERE u.id = ?";
		Connection conn = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setInt(1,id);
			rowDeleted  = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowDeleted;
	}
	
	public int addUser(String email, String password, String fullName, String address, String phoneNumber, int roleID) {
		int rowInserted = 0;
		String sqlQuery = "INSERT INTO users(email, password, fullname, address, phone_number, role_id) VALUES(?,?,?,?,?,?)";
		Connection conn = MysqlConfig.getConnecttion();
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, email);
			ps.setString(2, MD5.getMd5(password));
			ps.setString(3, fullName);
			ps.setString(4, address);
			ps.setString(5, phoneNumber);
			ps.setInt(6, roleID);
			
			rowInserted = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowInserted;
		
	}
	public int updateJob(UserEntity user) {
		Connection conn = MysqlConfig.getConnecttion();
		int i = 0;
		String sqlQuery = "UPDATE users SET email = ?, password =?, fullname = ?, address = ?, phone_number =?, role_id =? WHERE id = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, user.getEmail());
			ps.setString(2, MD5.getMd5(user.getPassword()));
			ps.setString(3, user.getFullName());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getPhoneNumber());
			ps.setInt(6, user.getRoleId());
			ps.setInt(7, user.getId());
			
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
}
