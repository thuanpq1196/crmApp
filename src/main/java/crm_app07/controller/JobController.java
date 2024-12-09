package crm_app07.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07.entity.JobEntity;
import crm_app07.entity.TaskEntity;
import crm_app07.services.JobServices;
import crm_app07.services.TaskServices;
import crm_app07.services.UserServices;
import dto.StatusPercent;
import utils.GetCookie;

@WebServlet(name = "jobController", urlPatterns = {"/jobs","/job-add","/job-update","/job-delete","/job-detail"})
public class JobController extends HttpServlet{
	private JobServices js = new JobServices();
	private TaskServices ts = new TaskServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = 0;
		if(req.getParameter("jobID") != null) {
			id = Integer.parseInt(req.getParameter("jobID"));
		}
		switch(req.getServletPath()) {
		case ("/jobs"):
			loadJobs(req,resp);
			break;
		case("/job-add"):
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
			break;
		case("/job-update"):
			loadUpdatedJob(req, resp);
			break;
		case("/job-delete"):
			js.deleteJob(id);
			resp.sendRedirect(req.getContextPath()+"/jobs");
			break;
		case("/job-detail"):
			int jobID = Integer.parseInt(req.getParameter("jobID"));
			List<TaskEntity> tasks = ts.findTaskByJobID(jobID);
			StatusPercent sp = ts.calculatedPercent(tasks);
			req.setAttribute("tasks", tasks);
			req.setAttribute("status", sp);
			req.getRequestDispatcher("groupwork-details.jsp").forward(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case("/job-add"):
			addJob(req, resp);
			break;
		case("/job-update"):
			updateJob(req, resp);
			break;
		}
	}
	
	public void loadJobs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<JobEntity> jobs = new ArrayList<>();
		String role = GetCookie.getCookieByKey("role", req, resp).getValue();
		int id = Integer.parseInt(GetCookie.getCookieByKey("userID", req, resp).getValue());
		if(role.equals("ROLE_ADMIN")){
			jobs = js.getAll();
		}else
			jobs = js.findJobByLeaderId(id);
		req.setAttribute("jobs", jobs);
		req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
	}
	
	public void loadUpdatedJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JobEntity job = new JobEntity();
		int jobID = Integer.parseInt(req.getParameter("jobID"));
		job = js.findJobByID(jobID);
		req.setAttribute("job", job);
		req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
	}
	
	public void addJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String jobName = req.getParameter("name");
		System.out.println(jobName);
		String description = req.getParameter("description");
		
		
		String startDateString = req.getParameter("startdate");
		Date startDate = Date.valueOf(startDateString);

		String endDateString = req.getParameter("enddate");
		Date endDate = Date.valueOf(endDateString);
		
		int creatorID = 0;
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie item : cookies) {
				if(item.getName().equals("userID")) {
					creatorID = Integer.parseInt(item.getValue());
				}
			}
		}
		if(js.addJob(jobName, description, startDate, endDate, creatorID)) {
//			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
			resp.sendRedirect(req.getContextPath()+"/jobs");
		}else{
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		}
	}
	public void updateJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(req.getParameter("id"));
		String jobName = req.getParameter("name");
		String description = req.getParameter("description");
		
		
		String startDateString = req.getParameter("startdate");
		Date startDate = Date.valueOf(startDateString);

		String endDateString = req.getParameter("enddate");
		Date endDate = Date.valueOf(endDateString);
		
		int creatorID = 0;
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie item : cookies) {
				if(item.getName().equals("userID")) {
					creatorID = Integer.parseInt(item.getValue());
				}
			}
		}
		JobEntity job = new JobEntity();
		job.setId(id);
		job.setName(jobName);
		job.setDescription(description);
		job.setStartDate(startDate);
		job.setEndDate(endDate);
		job.setCreatorId(creatorID);
		
		if(js.updateJob(job)) {
			resp.sendRedirect(req.getContextPath()+"/jobs");
		}else
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
	}
}
