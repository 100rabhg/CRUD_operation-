package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import bean.Task;
import dao.DBOpearation;

public class Add extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		Task t = new Task();
		t.setTask(req.getParameter("task"));
		PrintWriter pw = res.getWriter();
		if(!DBOpearation.insert(t)) {
			pw.print("<h1>Task Add Successful</h1>");
		}
		else {
			pw.print("<h1>Task Add Fail</h1>");
		}
	}

}
