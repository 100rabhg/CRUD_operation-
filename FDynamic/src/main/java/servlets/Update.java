package servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import helper.*;

public class Update implements Servlet{

	@Override
	public void destroy() {
		System.out.println("Destroy");
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("Init");
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		Employees e = new Employees();
		e.setId(Integer.parseInt(req.getParameter("id")));
		e.setName(req.getParameter("name"));
		e.setDepartment(req.getParameter("department"));
		e.setSalary(Integer.parseInt(req.getParameter("salary")));
		if (DbOperation.update(e)) {
			res.getWriter().println("<h1>Data update successfully</h1>");
		}
		else {
			res.getWriter().println("<h1>Data update failed</h1>");
		}
	}
}
