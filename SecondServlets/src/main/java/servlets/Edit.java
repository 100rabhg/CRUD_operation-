package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import bean.Task;
import dao.DBOpearation;

public class Edit extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		Task t = DBOpearation.getDataById( Integer.parseInt(req.getParameter("id")));
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String s = t.isStatus()?"checked":"";
		pw.print("<form action=\"update\" method=\"post\"  >\r\n"
				+ "<input type=\"hidden\"  name=\"id\" value="+t.getId()+" readonly> <br><br>\r\n"
				+ "<input type=\"text\" name=\"task\" value=\""+t.getTask()+"\">\r\n"
				+ "<input type=\"checkbox\"  name=\"status\" "+s+" value=\"true\" ><br><br>\r\n"
				+ "<button type=submit>SUBMIT</button>\r\n"
				+ "\r\n"
				+ "</form>");
		
	}
		
}
