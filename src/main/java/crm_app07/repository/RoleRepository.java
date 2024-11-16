package crm_app07.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.MysqlConfig;
import crm_app07.entity.RoleEntity;

public class RoleRepository {
	
	public List<RoleEntity> getAllRole(){
		List<RoleEntity> roles = new ArrayList<>();
		String sqlQuery = "SELECT * FROM roles r";
		Connection conn = MysqlConfig.getConnecttion();
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				RoleEntity re = new RoleEntity();
				re.setId(rs.getInt("id"));
				re.setName(rs.getString("name"));
				re.setDescription(rs.getString("description"));
				
				roles.add(re);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}
	
	public RoleEntity findByID(int id) {
		RoleEntity re = new RoleEntity();
		String sqlQuery = "SELECT * FROM roles r WHERE r.id = ?";
		Connection conn = MysqlConfig.getConnecttion();
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				re.setId(rs.getInt("id"));
				re.setName(rs.getString("name"));
				re.setDescription(rs.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return re;
	}

}
