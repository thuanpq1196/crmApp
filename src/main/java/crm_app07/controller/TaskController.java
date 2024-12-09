package crm_app07.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07.entity.JobEntity;
import crm_app07.entity.StatusEntity;
import crm_app07.entity.TaskEntity;
import crm_app07.entity.UserEntity;
import crm_app07.services.JobServices;
import crm_app07.services.StatusServices;
import crm_app07.services.TaskServices;
import crm_app07.services.UserServices;

@WebServlet(name="taskController", urlPatterns= {"/tasks","/task-add","/task-update","/task-delete"})
public class TaskController extends HttpServlet{
	private TaskServices ts = new TaskServices();
	private UserServices us = new UserServices();
	private JobServices js = new JobServices();
	private StatusServices ss = new StatusServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case ("/tasks"):
			loadTask(req, resp);
			break;
		case ("/task-add"):
			prepareAddOrUpdate(req, resp);
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
			break;
		case ("/task-update"):
			prepareAddOrUpdate(req, resp);
			int id = Integer.parseInt(req.getParameter("taskID"));
			TaskEntity te = ts.findTaskByID(id);
			req.setAttribute("task", te);
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
			break;
		case ("/task-delete"):
			int i = Integer.parseInt(req.getParameter("taskID"));
			if(ts.deleteTask(i)) {
				resp.sendRedirect(req.getContextPath()+"/tasks");
			}else
				req.getRequestDispatcher("404.html").forward(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case ("/task-add"):
			addTask(req,resp);
			break;
		case ("/task-update"):
			updateTask(req, resp);
			break;
		}
	}
	public void loadTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TaskEntity> tasks = ts.getAllTask();
		req.setAttribute("tasks", tasks);
		req.getRequestDispatcher("task.jsp").forward(req, resp);
	}
	public void addTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		int jobID = Integer.parseInt(req.getParameter("jobID"));
		int userID = Integer.parseInt(req.getParameter("userID"));
		int statusID = Integer.parseInt(req.getParameter("statusID"));
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		Date startDate = Date.valueOf(req.getParameter("startdate"));
		Date endDate = Date.valueOf(req.getParameter("enddate"));
		
		if(ts.addTask(name, description, startDate, endDate, statusID, userID, jobID)) {
			resp.sendRedirect(req.getContextPath()+"/tasks");
		}else {
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
		}
	}
	public void prepareAddOrUpdate(HttpServletRequest req, HttpServletResponse resp) {
		List<UserEntity> users = us.findUser();
		List<JobEntity> jobs = js.getAll();
		List<StatusEntity> statusList = ss.getAll();
		req.setAttribute("users", users);
		req.setAttribute("jobs", jobs);
		req.setAttribute("status", statusList);
	}
	public void updateTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		TaskEntity te = new TaskEntity();
		te.setId(Integer.parseInt(req.getParameter("id")));
		te.setJobId(Integer.parseInt(req.getParameter("jobID")));
		te.setUserId(Integer.parseInt(req.getParameter("userID")));
		te.setStatus(Integer.parseInt(req.getParameter("statusID")));
		te.setName(req.getParameter("name"));
		te.setDescription(req.getParameter("description"));
		te.setStartDate(Date.valueOf(req.getParameter("startdate")));
		te.setEndDate(Date.valueOf(req.getParameter("enddate")));
		if(ts.updateTask(te)) {
			resp.sendRedirect(req.getContextPath()+"/tasks");
		}else {
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
		}
	}
}
