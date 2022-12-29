package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.ClassNotFoundException;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost3306/demo","root","Y1012Jqkhkp");
		String n=request.getParameter("txtName");
		String p=request.getParameter("txtPwd");
		PreparedStatement ps = con.prepareStatement("select uname from login where uname=? and pass=?");
		ps.setString(1, n);
		ps.setString(2, p);
		
		ResultSet rs = ps.executeQuery();
	
		if(rs.next()) {
			
			RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
			rd.forward(request, response);
		}
		else {
			out.println("<font color=red size=18>Login Failed!!<br>");
			out.println("< href=login.jsp>TRY AGAIN!!");
		}
		
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}

}
}
