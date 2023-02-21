package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbOperation {
		
		private static Connection con = null;
		
		public static void connect() {
//			
			String url="jdbc:mysql://localhost:3306/java330";
			String uname = "root";
			String pass = "root";
		
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url,uname,pass);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
//		data insert method 
		public static boolean insert(Employees e) {
			boolean flag=false;
			try {
				connect();
				PreparedStatement ps = con.prepareStatement("insert into employees values(?,?,?,?)");
				
				ps.setInt(1, e.getId());
				ps.setString(2, e.getName());
				ps.setString(3, e.getDepartment());
				ps.setInt(4, e.getSalary());
				flag = !ps.execute();
				
				con.close();
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
			return flag;
		}
		
		public static boolean update(Employees e){
			boolean b=false;
			try {
				connect();
				
				PreparedStatement ps = con.prepareStatement("update employees set name=?, department=?, salary=? where id=?");
				
				ps.setString(1,e.getName());
				ps.setString(2, e.getDepartment());
				ps.setInt(3, e.getSalary());
				ps.setInt(4, e.getId());
				
				b=ps.executeUpdate()>0?true:false;
				con.close();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return b;
		}
		
//		delete method
		public static boolean delete(int id) {
			connect();
			boolean flag=false;
			try {
				PreparedStatement ps = con.prepareStatement("delete from employees where id=?");
				ps.setInt(1, id);
				
				flag = ps.executeUpdate()>0?true:false;
				
				con.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return flag;
		}
		
//		data view method 
		public static ArrayList<Employees> viewData() {
			connect();
			ArrayList<Employees> al = new ArrayList<Employees>();
			try {
				PreparedStatement ps = con.prepareStatement("select * from employees");
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
						Employees e = new Employees();
						e.setId(rs.getInt("id"));
						e.setName(rs.getString("name"));
						e.setDepartment(rs.getString("department"));
						e.setSalary(rs.getInt("salary"));
						al.add(e);
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return al;
		}
		
		public static Employees viewDataById(int id) {
			connect();
			Employees e = new Employees();
			try {
				PreparedStatement ps = con.prepareStatement("select * from employees where id=?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
						e.setId(rs.getInt(1));
						e.setName(rs.getString(2));
						e.setDepartment(rs.getString(3));
						e.setSalary(rs.getInt(4));
				}
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return e;
		}
	}
