package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Task;

public class DBOpearation {
private static Connection con=null;

private static void connect()
{
	String url = "jdbc:mysql://localhost:3306/java330";
	String uname="root";
	String upass="root";
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con= DriverManager.getConnection(url,uname,upass);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}

public static boolean insert(Task t) {
	connect();
	boolean b=true;
	try {
		PreparedStatement ps = con.prepareStatement("insert into task(task) values(?)");
		ps.setString(1,t.getTask());
		b=ps.execute();
		con.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return b;
}

public static boolean delete(int id) {
	connect();
	boolean b=true;
	try {
		PreparedStatement ps = con.prepareStatement("delete from task where id=?");
		ps.setInt(1, id);
		if(ps.executeUpdate()==0) {
			b=true;
		}
		else {
			b=false;
		}
		con.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return b;
}

public static Task getDataById(int id) {
	connect();
	Task t=null;
	try {
		PreparedStatement ps = con.prepareStatement("select * from task where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		t = new Task();
		t.setId(rs.getInt("id"))
		.setTask(rs.getString("task"))
		.setStatus(rs.getBoolean("status"));
		
		con.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return t;
}

public static ArrayList<Task> getData() {
	connect();
	ArrayList<Task> tArr= new ArrayList<Task>(); 
	try {
		PreparedStatement ps = con.prepareStatement("select * from task");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Task u = new Task();
			u.setId(rs.getInt(1));
			u.setTask(rs.getString(2));
			u.setStatus(rs.getBoolean(3));
			tArr.add(u);
		}
		con.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return tArr;
}
public static boolean update(Task u) {
	connect();
	boolean b=true;
	try {
		PreparedStatement ps = con.prepareStatement("update task set task=?,status=? where id=?");
		ps.setInt(3, u.getId());
		ps.setString(1, u.getTask());
		ps.setBoolean(2, u.isStatus());
		if(ps.executeUpdate()>0) {
			b=false;
		}
		con.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return b;
}
}