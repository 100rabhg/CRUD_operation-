package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import helper.*;

public class GetDataByID implements Servlet {

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
		Employees e =  DbOperation.viewDataById(id);
		
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		pw.print("<form action=\"update\">\r\n"
				+ "<fieldset>\r\n"
				+ "    <legend>update</legend>\r\n"
				+ "<input name=\"id\"  type=\"number\" value="+id+"> <br><br>\r\n"
				+ "<input name=\"name\"  type=\"text\" value="+e.getName()+"> <br><br>\r\n"
				+ "<input name=\"department\"  type=\"text\" value="+e.getDepartment()+"><br><br>\r\n"
				+ "<input name=\"salary\"  type=\"number\" value="+e.getSalary()+"> <br><br>\r\n"
				+ "<button type=\"submit\" >Update</button>\r\n"
				+ "</fieldset>\r\n"
				+ "</form>");
	}
}
