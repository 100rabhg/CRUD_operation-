package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import dao.DBOpearation;

public class Delete extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		if(!DBOpearation.delete(Integer.parseInt(req.getParameter("id")))){
			pw.print("<h1>Task delete Successful</h1>");
		}
		else {
			pw.print("<h1>Task delete Fail</h1>");
		}
	}
}
