package exam04.jdbcSevletFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet (
		urlPatterns = {"/members/update"},
		initParams= {
				@WebInitParam(name="driver", value="com.mysql.jdbc.Driver"),
				@WebInitParam(name="url", value="jdbc:mysql://localhost:3306/studydb"),
				@WebInitParam(name="username", value="root"),
				@WebInitParam(name="password", value="1111")
		}
)
@SuppressWarnings("serial")
public class MemberUpdateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
			try {
				ServletContext sc = this.getServletContext();
				Class.forName(sc.getInitParameter("driver"));
				conn = DriverManager.getConnection(
						sc.getInitParameter("url"),
						sc.getInitParameter("username"),
						sc.getInitParameter("password"));
				stmt = conn.createStatement();
				rs = stmt.executeQuery(
						"select MNO, EMAIL, MNAME, CRE_DATE from MEMBERS"
						+" where MNO = " + req.getParameter("no"));
				rs.next();
				
				res.setContentType("text/html; charset=UTF-8"); 
				PrintWriter out = res.getWriter();
				
				out.println("<html></head><title>회원정보</title></head>");
				out.println("<form action='update' method='post'>");
				out.println("번호 : <input type='text' name='no' value='" + req.getParameter("no")+"'readonly><br>");
				out.println("이름 : <input type='text' name='name'" + "value='" + rs.getString("MNAME")+"'><br>");
				out.println("이메일 : <input type='text' name='email'" + "value='" + rs.getString("EMAIL") + "'><br>");
				out.println("가입일 : " + rs.getString("CRE_DATE") + "<br>");
				out.println("<input type='submit' value='전송'>");
				out.println("<input type='button' value='삭제' onclick='location.href=\"delete?no=" 
							+ req.getParameter("no") + "\"'>");
				out.println("<input type='button' value='취소' onclick='location.href=\"list2\"'>" );
				out.println("</form>");
				out.println("</body></html>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			
			try { if(rs != null) rs.close();} catch (Exception e) {}
			try { if(stmt != null) stmt.close();} catch (Exception e) {}
			try { if(conn != null) conn.close();} catch (Exception e) {}
			
			}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName(this.getInitParameter("driver"));
			conn = DriverManager.getConnection(this.getInitParameter("url"), 
											  this.getInitParameter("username"), 
											  this.getInitParameter("password"));
			stmt = conn.prepareStatement(
					"UPDATE MEMBERS SET EMAIL=?, MNAME=?, MOD_DATE=NOW()"
					+ "WHERE MNO = ?");

			stmt.setString(1, req.getParameter("email"));
			stmt.setString(2, req.getParameter("name"));
			stmt.setInt(3, Integer.parseInt(req.getParameter("no")));

			stmt.executeUpdate();
			
			res.sendRedirect("list");
			
		} catch (Exception e){ 
			e.printStackTrace();
		} finally {
			try { if(stmt != null ) stmt.close();} catch (Exception e) {}
			try { if(conn != null ) conn.close();} catch (Exception e) {}
		}
		
	}

}








