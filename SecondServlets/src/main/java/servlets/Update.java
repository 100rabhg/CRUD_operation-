package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import bean.Task;
import dao.DBOpearation;

public class Update  extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		Task t  = new Task();
		
		t.setId(Integer.parseInt(req.getParameter("id")));
		t.setTask(req.getParameter("task"));
		String status = req.getParameter("status");
		if(status==null) {
			t.setStatus(false);
		}
		else if(status.equals("true")){
			t.setStatus(true);
		}
		
		PrintWriter pw = res.getWriter();
		if(!DBOpearation.update(t)) {
			pw.print("<h1>Task update Successful</h1>");
		}
		else {
			pw.print("<h1>Task update Fail</h1>");
		}
	}
}
