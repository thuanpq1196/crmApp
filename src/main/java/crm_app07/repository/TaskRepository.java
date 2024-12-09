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
		String sqlQuery = "SELECT t.*,u.fullname,j.name AS job_name, s.name AS status_name FROM tasks t JOIN users u ON t.user_id = u.id"
												+ " JOIN jobs j ON t.job_id = j.id"
												+ " JOIN status s ON t.status_id = s.id";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TaskEntity te = new TaskEntity();
				te.setId(rs.getInt("id"));
				te.setName(rs.getString("name"));
				te.setDescription(rs.getString("descriptions"));
				te.setStartDate(rs.getDate("start_date"));
				te.setEndDate(rs.getDate("end_date"));
				te.setStatus(rs.getInt("status_id"));
				te.setUserId(rs.getInt("user_id"));
				te.setUserName(rs.getString("fullname"));
				te.setJobName(rs.getString("job_name"));
				te.setStatusName(rs.getString("status_name"));
				
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
		String sqlQuery = "SELECT t.*,u.fullname,j.name AS job_name, s.name AS status_name FROM tasks t JOIN users u ON t.user_id = u.id"
				+ " JOIN jobs j ON t.job_id = j.id"
				+ " JOIN status s ON t.status_id = s.id"
				+ " WHERE t.id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TaskEntity te = new TaskEntity();
				te.setId(rs.getInt("id"));
				te.setName(rs.getString("name"));
				te.setDescription(rs.getString("descriptions"));
				te.setStartDate(rs.getDate("start_date"));
				te.setEndDate(rs.getDate("end_date"));
				te.setStatus(rs.getInt("status_id"));
				te.setUserId(rs.getInt("user_id"));
				te.setUserName(rs.getString("fullname"));
				te.setJobName(rs.getString("job_name"));
				te.setStatusName(rs.getString("status_name"));
				
				tasks.add(te);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks.get(0);
	}
	
	public int addTask(String name, String description, Date startDate, Date endDate, int statusID, int userID, int jobID) {
		Connection conn = MysqlConfig.getConnecttion();
		String sqlQuery = "INSERT INTO tasks(name,descriptions,start_date, end_date, status_id, user_id, job_id) VALUES(?,?,?,?,?,?,?)";
		int rowInserted = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setDate(3, startDate);
			ps.setDate(4, endDate);
			ps.setInt(5, statusID);
			ps.setInt(6, userID);
			ps.setInt(7, jobID);
			rowInserted = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowInserted;
	}
	
	public int updateTask(TaskEntity te) {
		Connection conn = MysqlConfig.getConnecttion();
		String sqlQuery = "UPDATE tasks SET name = ?, descriptions = ?, start_date =?, end_date =?, status_id =?, user_id =? WHERE id =?";
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
	
	public List<TaskEntity> findTaskByJobID(int jobID){
		List<TaskEntity> tasks = new ArrayList<>();
		Connection conn = MysqlConfig.getConnecttion();
		String sqlQuery = "SELECT t.*, u.fullname, s.name AS status_name FROM tasks t JOIN status s ON s.id = t.status_id "
												+ "JOIN users u ON u.id = t.user_id "
												+ "WHERE t.job_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, jobID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TaskEntity te = new TaskEntity();
				te.setId(rs.getInt("id"));
				te.setName(rs.getString("name"));
				te.setDescription(rs.getString("descriptions"));
				te.setStartDate(rs.getDate("start_date"));
				te.setEndDate(rs.getDate("end_date"));
				te.setStatus(rs.getInt("status_id"));
				te.setUserId(rs.getInt("user_id"));
				te.setUserName(rs.getString("fullname"));
				te.setStatusName(rs.getString("status_name"));
				
				tasks.add(te);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}
	
	public List<TaskEntity> findTaskByUserID(int userID){
		List<TaskEntity> tasks = new ArrayList<>();
		Connection conn = MysqlConfig.getConnecttion();
		String sqlQuery = "SELECT t.*, j.name AS job_name, s.name AS status_name FROM tasks t JOIN status s ON t.status_id = s.id"
																							+ " JOIN jobs j ON t.job_id = j.id"
																							+ " WHERE t.user_id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TaskEntity te = new TaskEntity();
				te.setId(rs.getInt("id"));
				te.setName(rs.getString("name"));
				te.setDescription(rs.getString("descriptions"));
				te.setStartDate(rs.getDate("start_date"));
				te.setEndDate(rs.getDate("end_date"));
				te.setStatus(rs.getInt("status_id"));
				te.setJobId(rs.getInt("job_id"));
				te.setJobName(rs.getString("job_name"));
				te.setStatusName(rs.getString("status_name"));
				
				tasks.add(te);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}
	
	public int deleteTaskByJobId(int jobId) {
		int deletedTask = 0;
		Connection conn = MysqlConfig.getConnecttion();
		String query = "DELETE FROM tasks t WHERE t.job_id = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, jobId);
			
			deletedTask = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return deletedTask;
	}
	
	public int updateTaskStatusById(int taskId, int statusId) {
		int updatedTask = 0;
		Connection conn = MysqlConfig.getConnecttion();
		String query = "UPDATE tasks t SET t.status_id = ? WHERE t.id = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, statusId);
			ps.setInt(2, taskId);
			updatedTask = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return updatedTask;
	}

}
