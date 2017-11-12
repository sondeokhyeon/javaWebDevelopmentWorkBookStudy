package exam04.jdbcSevletFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/members/add")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<html><head><title>회원 등록</title></head>");
		out.println("<body><h1>회원 등록</h1>");
		out.println("<form action='add' method='post'>");
		out.println("이름 : <input type='text' name='name'><br>");
		out.println("이메일 : <input type='text' name='email'><br>");
		out.println("비밀번호 : <input type='password' name='password'><br> ");
		out.println("<input type='submit' value='추가'>");
		out.println("<input type='reset' value='취소'>");
		out.println("</form>");
		out.println("</body></html>");
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// req.setCharacterEncoding("UTF-8"); 주석 처리 필터 사용으로 대체
		
		
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			ServletContext sc = getServletContext();
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password")
					);
					
			stmt = conn.prepareStatement(
					"INSERT INTO MEMBERS(EMAIL, PWD, MNAME,CRE_DATE, MOD_DATE)"
					+ "VALUES (?,?,?,NOW(),NOW())");
			stmt.setString(1, req.getParameter("email"));
			stmt.setString(2, req.getParameter("password"));
			stmt.setString(3, req.getParameter("name"));
			stmt.executeUpdate();
			
			res.setContentType("text/html; charset=UTF-8");
			
			res.sendRedirect("list"); // list 페이지로 이동
			
			/*	PrintWriter out = res.getWriter();
			out.println("<html></head><title>회원등록결과</title>");
			out.println("<body>");
			out.println("<p>등록 성공입니다.</p>");
			out.println("<body></html>");
			*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			try { if(stmt != null) stmt.close(); } catch(Exception e) {} 
			try { if(conn != null) conn.close(); } catch(Exception e) {}
		}
	}
}
