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
		String sqlQuery ="SELECT u.id, r.name FROM users u"
				+ " JOIN roles r ON u.role_id = r.id"
				+ " WHERE u.email = ? AND u.password = ? AND u.is_active = 1";
		Connection conn = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet rs  = statement.executeQuery();
			while(rs.next()) {
				UserEntity ue = new UserEntity();
				ue.setId(rs.getInt("id"));
				ue.setRoleName(rs.getString("name"));				
				users.add(ue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public UserEntity findByID(int id){
		List<UserEntity> users = new ArrayList<UserEntity>();
		String sqlQuery = "SELECT u.*,r.name  FROM users u JOIN roles r ON u.role_id = r.id WHERE u.id =?";
		Connection conn = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				UserEntity user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setFullName(rs.getString("fullname"));
				user.setRoleId(rs.getInt("role_id"));
				user.setAddress(rs.getString("address"));
				user.setPhoneNumber(rs.getString("phone_number"));
				user.setRoleName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setIsActive(rs.getInt("is_active"));
				users.add(user);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users.get(0);
	}
	
	public List<UserEntity> findAll(){
		List<UserEntity> users = new ArrayList<UserEntity>();
		String sqlQuery = "SELECT u.id, u.email, u.fullname, u.role_id, u.address, u.phone_number, r.description AS role_name, u.is_active FROM users u JOIN roles r ON u.role_id = r.id";
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
				user.setAddress(rs.getString("address"));
				user.setPhoneNumber(rs.getString("phone_number"));
				user.setRoleName(rs.getString("role_name"));
				user.setIsActive(rs.getInt("is_active"));
				users.add(user);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public int deleteById(int id) {
		int rowUpdated = 0;
		int isActive = 0;
		Connection conn = MysqlConfig.getConnecttion();
		String selectQuery ="SELECT u.is_active FROM users u WHERE u.id =?";
		try {
			PreparedStatement statusStatement = conn.prepareStatement(selectQuery);
			statusStatement.setInt(1, id);
			ResultSet rs = statusStatement.executeQuery();
			while(rs.next()) {
				isActive = rs.getInt("is_active");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sqlQuery =( isActive == 1 ? "UPDATE users u SET u.is_active = 0 WHERE u.id = ?" : "UPDATE users u SET u.is_active = 1 WHERE u.id = ?");
		
		try {
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			statement.setInt(1,id);
			rowUpdated  = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowUpdated;
	}
	
	public int addUser(String email, String password, String fullName, String address, String phoneNumber, int roleID) {
		int rowInserted = 0;
		String sqlQuery = "INSERT INTO users(email, password, fullname, address, phone_number, role_id, is_active) VALUES(?,?,?,?,?,?,1)";
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
	public int updateUser(UserEntity user) {
		Connection conn = MysqlConfig.getConnecttion();
		int i = 0;
		String sqlQuery = "UPDATE users SET email = ?, password =?, fullname = ?, address = ?, phone_number =?, role_id =?, is_active = ? WHERE id = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, user.getEmail());
			ps.setString(2, MD5.getMd5(user.getPassword()));
			ps.setString(3, user.getFullName());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getPhoneNumber());
			ps.setInt(6, user.getRoleId());
			ps.setInt(7, user.getIsActive());
			ps.setInt(8, user.getId());
			
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	public List<UserEntity> findByRole(){
		List<UserEntity> users = new ArrayList<UserEntity>();
		String sqlQuery = "SELECT u.id,u.fullname  FROM users u WHERE u.role_id =3 AND u.is_active = 1";
		Connection conn = MysqlConfig.getConnecttion();
		try {
			PreparedStatement statement = conn.prepareStatement(sqlQuery);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				UserEntity user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setFullName(rs.getString("fullname"));
				users.add(user);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
}
