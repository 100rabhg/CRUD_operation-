package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import emp.Employees;

public class DbOperation {
	
	static Connection con = null;
	
	public static void connect() {
//		
		String url="jdbc:mysql://localhost:3306/java330";
		String uname = "main";
		String pass = "12345";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,uname,pass);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	data insert method 
	public static void insert(Employees e) {
		try {
			connect();
			PreparedStatement ps = con.prepareStatement("insert into employees values(?,?,?,?)");
			ps.setInt(1, e.getId());
			ps.setString(2, e.getName());
			ps.setString(3, e.getDepartment());
			ps.setInt(4, e.getSalary());
			
			if(!ps.execute()) {
				System.out.println("Data inserted");
			}
			else {
				System.err.println("Data not inserted");
			}
			con.close();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
//	data update method 
	public static void update(Employees e){
		System.out.println(e.getName()+" "+e.getDepartment()+" "+e.getSalary());
		try {
			connect();
			PreparedStatement ps = con.prepareStatement("select * from employees where id=?");
			ps.setInt(1, e.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			
			ps = con.prepareStatement("update employees set name=?, department=?, salary=? where id=?");
			if(e.getName()!= null)
				ps.setString(1,e.getName());
			else
				ps.setString(1, rs.getString(2));
			if(e.getDepartment()!= null)
				ps.setString(2, e.getDepartment());
			else 
				ps.setString(2, rs.getString(3));
			if(e.getSalary()!= null)
				ps.setInt(3, e.getSalary());
			else 
				ps.setInt(3, rs.getInt(4));
			ps.setInt(4, e.getId());
			
			if(!ps.execute()) {
				System.out.println("data updated");
				ps = con.prepareStatement("select * from employees where id=?");
				ps.setInt(1, e.getId());
				rs = ps.executeQuery();
				rs.next();
				System.err.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
			}
			}
			else
				System.err.println("no row affect");
			
			con.close();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
//	delete method
	public static void delete(int id) {
		connect();
		try {
			PreparedStatement ps = con.prepareStatement("delete from employees where id=?");
			ps.setInt(1, id);
			int i=ps.executeUpdate();
			if(i==0) 
				System.err.println("record not found");
		
			else
				System.out.println(i+" record delete");
			con.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	data view method 
	public static void viewData() {
		connect();
		try {
			PreparedStatement ps = con.prepareStatement("select * from employees");
			ResultSet rs = ps.executeQuery();
			if(rs!=null)
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
				}
			else
				System.err.println("error in fetching");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
