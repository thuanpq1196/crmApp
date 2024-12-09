package crm_app07.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07.entity.JobEntity;
import crm_app07.entity.TaskEntity;
import crm_app07.services.JobServices;
import crm_app07.services.TaskServices;
import dto.StatusPercent;
import utils.GetCookie;

@WebServlet(urlPatterns= {"/home"})
public class DashboardController extends HttpServlet{
	
	private TaskServices ts = new TaskServices();
	private JobServices js = new JobServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String role = GetCookie.getCookieByKey("role", req, resp).getValue();
		List<TaskEntity> tasks = new ArrayList<>();
		int id = Integer.parseInt(GetCookie.getCookieByKey("userID", req, resp).getValue());
		switch(role) {
		case("ROLE_ADMIN"):
			tasks = ts.getAllTask();
			break;
		case("ROLE_MANAGER"):
			List<JobEntity> jobs = js.findJobByLeaderId(id);
			for(JobEntity job : jobs) {
				tasks.addAll(ts.findTaskByJobID(job.getId()));
			}
			break;
		case("ROLE_USER"):
			tasks = ts.findTaskByUserID(id);
			break;
		}
		StatusPercent sp = ts.calculatedPercent(tasks);
		req.setAttribute("status", sp);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	

}
