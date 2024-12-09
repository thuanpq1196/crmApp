package crm_app07.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.MysqlConfig;
import crm_app07.entity.JobEntity;

public class JobRepository {
	
	public List<JobEntity> getAllJob(){
		List<JobEntity> jobs = new ArrayList<>();
		Connection conn = MysqlConfig.getConnecttion();
		String sqlQuery = "SELECT * FROM jobs";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				JobEntity je = new JobEntity();
				je.setId(rs.getInt("id"));
				je.setName(rs.getString("name"));
				je.setDescription(rs.getString("descriptions"));
				je.setCreatorId(rs.getInt("creator_id"));
				je.setStartDate(rs.getDate("start_date"));
				je.setEndDate(rs.getDate("end_date"));
				
				jobs.add(je);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jobs;
	}
	
	public List<JobEntity> findAllJobByLeaderId(int id){
		List<JobEntity> jobs = new ArrayList<>();
		Connection conn = MysqlConfig.getConnecttion();
		String sqlQuery = "SELECT * FROM jobs j WHERE j.creator_id =?";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				JobEntity je = new JobEntity();
				je.setId(rs.getInt("id"));
				je.setName(rs.getString("name"));
				je.setDescription(rs.getString("descriptions"));
				je.setCreatorId(rs.getInt("creator_id"));
				je.setStartDate(rs.getDate("start_date"));
				je.setEndDate(rs.getDate("end_date"));
				
				jobs.add(je);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jobs;
	}
	
	public JobEntity findByID(int id){
		JobEntity je = new JobEntity();
		Connection conn = MysqlConfig.getConnecttion();
		String sqlQuery = "SELECT j.* FROM jobs j  WHERE j.id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				je.setId(rs.getInt("id"));
				je.setName(rs.getString("name"));
				je.setDescription(rs.getString("descriptions"));
				je.setCreatorId(rs.getInt("creator_id"));
				je.setStartDate(rs.getDate("start_date"));
				je.setEndDate(rs.getDate("end_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return je;
	}
	
	public int addJob(String name, String description, Date startDate, Date endDate, int creatorID) {
		Connection conn = MysqlConfig.getConnecttion();
		int i =0;
		String sqlQuery = "INSERT INTO jobs(name, descriptions, start_date, end_date, creator_id) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setDate(3, startDate);
			ps.setDate(4, endDate);
			ps.setInt(5, creatorID);
			
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public int updateJob(JobEntity job) {
		Connection conn = MysqlConfig.getConnecttion();
		int i = 0;
		String sqlQuery = "UPDATE jobs SET name = ?, descriptions = ?, start_date = ?, end_date = ?, creator_id =? WHERE id = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, job.getName());
			ps.setString(2, job.getDescription());
			ps.setDate(3, job.getStartDate());
			ps.setDate(4, job.getEndDate());
			ps.setInt(5, job.getCreatorId());
			ps.setInt(6, job.getId());
			
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public int deleteJob(int id){
		Connection conn = MysqlConfig.getConnecttion();
		int i = 0;
		String sqlQuery = "DELETE FROM jobs j WHERE j.id = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, id);
			
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	} 
	

}
