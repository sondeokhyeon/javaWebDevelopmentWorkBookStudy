package exam03.jdbcSevlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/member/delete"})
public class MemberDeleteServlet  extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		Statement stmt = null;
		int rs;
		
		try {
			ServletContext sc = getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
							sc.getInitParameter("url"), 
							sc.getInitParameter("username"), 
							sc.getInitParameter("password"));
			stmt = conn.createStatement();
			rs = stmt.executeUpdate(
					"DELETE FROM MEMBERS " 
					+ "WHERE MNO = " + Integer.parseInt(req.getParameter("no")));
			
			
			res.sendRedirect("list2");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(stmt != null)	stmt.close();	} catch (Exception e) {}
			try { if(conn != null)	conn.close();	} catch (Exception e) {}
		}
	}
}
