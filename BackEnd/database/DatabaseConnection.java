package com.extentia.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import com.extentia.constructor.ToDo;
import com.extentia.constructor.User;
import com.extentia.training.DateTime;

public class DatabaseConnection {
	Date datetime = new Date();
	SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY");

	public boolean addToDo(String toDo, String username) {

		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");

			String sql = "INSERT INTO to_do_list (username,task,status,startDateTime) "
					+ "VALUES (?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, toDo);
			preparedStmt.setString(3, "doing");
			preparedStmt.setString(4, df.format(datetime));
			preparedStmt.execute();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public ArrayList<ToDo> displayAll(String currentUser) {
		Connection con;
		ArrayList<ToDo> toDoList = new ArrayList<ToDo>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");

			Statement stmt = con.createStatement();
			String sql = "select * from to_do_list where username='"
					+ currentUser + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int ID = rs.getInt(1);
				String username = rs.getString(2);
				String task = rs.getString(3);
				String status = rs.getString(4);
				String startTaskDate = "" + rs.getString(5);
				String endTaskDate = "" + rs.getString(6);
				toDoList.add(new ToDo(ID, username, task, status,
						startTaskDate, endTaskDate));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return toDoList;
	}

	public void deleteToDo(int ID) {
		Connection con;
		ArrayList<ToDo> toDoList = new ArrayList<ToDo>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");

			Statement stmt = con.createStatement();

			String sql = "UPDATE to_do_list SET status= ?, completedDateTime = STR_TO_DATE( ?, '%m/%d/%Y') WHERE ID= ?";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, "delete");
			preparedStmt.setString(2, df.format(datetime));
			preparedStmt.setInt(3, ID);
			preparedStmt.executeUpdate();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void completeTask(int ID) {
		Connection con;
		ArrayList<ToDo> toDoList = new ArrayList<ToDo>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");

			Statement stmt = con.createStatement();

			String sql = "UPDATE to_do_list SET status= ?, completedDateTime = ? WHERE ID= ?";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, "completed");
			preparedStmt.setString(2, df.format(datetime));
			preparedStmt.setInt(3, ID);
			preparedStmt.executeUpdate();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void undoTask(int ID) {
		Connection con;
		ArrayList<ToDo> toDoList = new ArrayList<ToDo>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");

			Statement stmt = con.createStatement();

			String sql = "UPDATE to_do_list SET status= ?, completedDateTime = ? WHERE ID= ?";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, "doing");
			preparedStmt.setString(2, "");
			preparedStmt.setInt(3, ID);
			preparedStmt.executeUpdate();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateToDo(int ID, String task) {
		Connection con;
		ArrayList<ToDo> toDoList = new ArrayList<ToDo>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");

			String sql = "UPDATE to_do_list SET task= ? WHERE ID= ?";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, task);
			preparedStmt.setInt(2, ID);
			preparedStmt.executeUpdate();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<User> getUser() {
		ArrayList<User> userList = new ArrayList<User>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from User");

			while (rs.next()) {
				String username = rs.getString(1);
				String password = rs.getString(2);

				userList.add(new User(username, password));
			}

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}

	public void createUser(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");
			Statement stmt = con.createStatement();

			String sql = "INSERT INTO user (username,password) "
					+ "VALUES (?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);

			preparedStmt.execute();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean assignUser(int ID, String username) {

		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");

			String sql = "UPDATE to_do_list SET username = ? WHERE id =?";

			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, username);
			preparedStmt.setInt(2, ID);

			preparedStmt.execute();

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public ArrayList<String> getTaskList(String username) {
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement ps = null;
		String data;
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT username FROM user  WHERE username  LIKE '"
							+ username + "%'");

			while (rs.next()) {
				data = rs.getString(1);
				list.add(data);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	public ArrayList<ToDo> reportList(String user, String reportStatus,
			String startDate, String endDate) {
		Connection con;
		ArrayList<ToDo> toDoList = new ArrayList<ToDo>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");

			String sql;

			Statement stmt = con.createStatement();

			System.out.print(reportStatus);
			System.out.print(user);

			if (reportStatus.equals("doing") && !user.equals("all")) {
				sql = "select * from to_do_list where username ='" + user
						+ "' and status ='" + reportStatus
						+ "' and startDateTime >'" + startDate + "'";
			} else if (reportStatus.equals("doing") && user.equals("all")) {
				sql = "select * from to_do_list where status ='" + reportStatus
						+ "' and startDateTime >'" + startDate + "'";
			} else if (reportStatus.equals("delete") && user.equals("all")) {
				sql = "select * from to_do_list where status ='" + reportStatus
						+ "' and startDateTime >'" + startDate
						+ "' and completedDateTime <'" + endDate + "'";
			}else if (user.equals("all") && !reportStatus.equals("all")) {
				sql = "select * from to_do_list where status ='" + reportStatus
						+ "' and startDateTime >'" + startDate
						+ "' and completedDateTime <'" + endDate + "'";
			}  else if (reportStatus.equals("all") && user.equals("all")) {
				sql = "select * from to_do_list where startDateTime >'"
						+ startDate + "' and completedDateTime <'" + endDate
						+ "' || startDateTime <'" + startDate + "'";
			}else if (!user.equals("all") && reportStatus.equals("all")) {
				sql = "select * from to_do_list where username ='" + user
						+ "' and startDateTime >'" + startDate
						+ "' and completedDateTime <'" + endDate + "'";
			}   else {
				sql = "select * from to_do_list where username ='" + user
						+ "' and status ='" + reportStatus
						+ "' and startDateTime >'" + startDate
						+ "' and completedDateTime <'" + endDate + "'";
			}
			ResultSet rs = stmt.executeQuery(sql);

			System.out.print(sql);
			while (rs.next()) {
				int ID = rs.getInt(1);
				String username = rs.getString(2);
				String task = rs.getString(3);
				String status = rs.getString(4);
				String startTaskDate = rs.getString(5);
				String endTaskDate = rs.getString(6);
				toDoList.add(new ToDo(ID, username, task, status,
						startTaskDate, endTaskDate));
			}

			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return toDoList;
	}
}
