package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import helper.DbOperation;
import helper.Employees;

public class ShowData implements Servlet {

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
	
ArrayList<Employees> al = DbOperation.viewData();
		
		PrintWriter pw = res.getWriter();
		
		pw.print("<table border=1 >\r\n"
				+ "<tr>\r\n"
				+ "<th>ID</th><th>Name</th><th>Department</th><th>Salary</th><th>Edit</th><th>Delete</th>\r\n"
				+ "</tr>");
		
		for(Employees e : al) {
			pw.print("\r\n"
					+ "<tr>\r\n"
					+ "<th>"+e.getId()+"</th><th>"+e.getName()+"</th><th>"+e.getDepartment()+"</th><th>"+e.getSalary()+"</th>\r\n"
					+ "<th><a href=\"show?id="+e.getId()+"\"><button>Edit</button></a></th>\r\n"
					+ "<th><a href=\"s1?id="+e.getId()+"\"><button>Delete</button></a></th>\r\n"
					+ "</tr>");
		}
		pw.print("</table>");
	}
}
