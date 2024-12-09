package crm_app07.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.MysqlConfig;
import crm_app07.entity.StatusEntity;

public class StatusRepository {
	public List<StatusEntity> getAll(){
		List<StatusEntity> statusList = new ArrayList<>();
		String sqlQuery = "SELECT * FROM status";
		Connection conn = MysqlConfig.getConnecttion();
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				StatusEntity se = new StatusEntity();
				se.setId(rs.getInt("id"));
				se.setName(rs.getString("name"));
				
				statusList.add(se);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return statusList;
	}
}
