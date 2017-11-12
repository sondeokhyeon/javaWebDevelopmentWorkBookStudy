package exam04.jdbcSevletFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/members/list")
public class MemberListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			ServletContext sc = getServletContext();
			
			conn = DriverManager.getConnection(
				/*	"jdbc:mysql://localhost:3306/studydb", // JDBC URL 
					"root", // DBMS 아이디
					"1111" */
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password")
					); // DBMS 사용자 암호
			stmt = conn.createStatement(); 
			rs = stmt.executeQuery(
					"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
							" FROM MEMBERS" +
							" ORDER BY MNO ASC");
					
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			
			out.println("<html><head><title>회원목록</title></head>");
			out.println("<body><h1>회원목록</h1>");
			out.println("<p><a href='add'>신규회원</a></p>");
			while(rs.next()) {
				out.println(rs.getInt("mno") + "," + 
							"<a href='update?no=" + rs.getInt("mno") + "'>" +
						    rs.getString("MNAME") + "</a>," + 
						    rs.getString("EMAIL") + "," +
						    rs.getDate("cre_date") + 
						    "<a href='delete?no=" + rs.getInt("mno") +"'>{삭제}</a><br>"
				);
			}
			out.println("</body></html>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
	}
}
