package crm_app07.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import config.MysqlConfig;
import crm_app07.entity.TaskEntity;

public class TaskRepository {
	public List<TaskEntity> getAllTask(){
		List<TaskEntity> tasks = new ArrayList<>();
		Connection conn = MysqlConfig.getConnecttion();
		String sqlQuery = "SELECT * FROM tasks";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TaskEntity te = new TaskEntity();
				te.setId(rs.getInt("id"));
				te.setName(rs.getString("name"));
				te.setDescription(rs.getString("description"));
				te.setStartDate(rs.getDate("start_date"));
				te.setEndDate(rs.getDate("end_date"));
				te.setStatus(rs.getInt("status"));
				te.setUserId(rs.getInt("user_id"));
				
				tasks.add(te);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}
	
	public TaskEntity findByID(int id) {
		List<TaskEntity> tasks = new ArrayList<>();
		Connection conn = MysqlConfig.getConnecttion();
		String sqlQuery = "SELECT * FROM tasks t WHERE t.id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TaskEntity te = new TaskEntity();
				te.setId(rs.getInt("id"));
				te.setName(rs.getString("name"));
				te.setDescription(rs.getString("description"));
				te.setStartDate(rs.getDate("start_date"));
				te.setEndDate(rs.getDate("end_date"));
				te.setStatus(rs.getInt("status"));
				te.setUserId(rs.getInt("user_id"));
				
				tasks.add(te);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks.get(0);
	}
	
	public int addTask(String name, String description, Date startDate, Date endDate, int status, int userID) {
		Connection conn = MysqlConfig.getConnecttion();
		String sqlQuery = "INSERT INTO tasks(name,description,start_date, end_date, status, user_id) VALUES(?,?,?,?,?,?)";
		int rowInserted = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setDate(3, startDate);
			ps.setDate(4, endDate);
			ps.setInt(5, status);
			ps.setInt(6, userID);
			rowInserted = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowInserted;
	}
	
	public int updateTask(TaskEntity te) {
		Connection conn = MysqlConfig.getConnecttion();
		String sqlQuery = "UPDATE tasks SET name = ?, description = ?, start_date =?, end_date =?, status =?, user_id =? WHERE id =?";
		int rowUpdated = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, te.getName());
			ps.setString(2, te.getDescription());
			ps.setDate(3, te.getStartDate());
			ps.setDate(4, te.getEndDate());
			ps.setInt(5, te.getStatus());
			ps.setInt(6, te.getUserId());
			ps.setInt(7, te.getId());
			rowUpdated = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}
	
	public int deleteTask(int id) {
		Connection conn = MysqlConfig.getConnecttion();
		String sqlQuery = "DELETE FROM tasks WHERE id =?";
		int rowDeleted = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, id);
			rowDeleted = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}

}
