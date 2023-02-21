package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import helper.*;

public class FirstServlet implements Servlet {

	@Override
	public void destroy() {
		System.out.println("Servelt Destroy");
		
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
	public void init(ServletConfig config) throws ServletException {
		System.out.println("servlet initialized");
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		PrintWriter pw =  res.getWriter();
		if(DbOperation.delete(id)) {
			pw.println("<h1> delete Succesful </h1>");
		}
		else {
			pw.println("<h1 style=\"color:red;\"> delete Failed </h1>");
		}
	}

}