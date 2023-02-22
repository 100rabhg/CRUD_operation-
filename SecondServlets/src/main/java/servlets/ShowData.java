package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import bean.Task;
import dao.DBOpearation;



public class ShowData extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
	
ArrayList<Task> al = DBOpearation.getData();
		
		PrintWriter pw = res.getWriter();
		
		res.setContentType("text/html");
		pw.print("<table border=1 >\r\n"
				+ "<tr>\r\n"
				+ "<th>Task</th><th>Status</th><th>Edit</th><th>Delete</th>\r\n"
				+ "</tr>");
		for(Task t : al) {
			String s = t.isStatus()?"checked":"";
			pw.print("\r\n"
					+ "<tr>\r\n"
					+ "<th>"+t.getTask()+"</th><th><input type=\"checkbox\" "+s+" onclick=\"return false\" ></th>\r\n"
					+ "<th><a href=\"edit?id="+t.getId()+"\"><button>Edit</button></a></th>\r\n"
					+ "<th><a href=\"delete?id="+t.getId()+"\"><button>Delete</button></a></th>\r\n"
					+ "</tr>");
		}
		pw.print("</table>");
	}

}
