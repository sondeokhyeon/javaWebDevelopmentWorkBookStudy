package exam02.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloGeneric extends GenericServlet {
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int a = Integer.parseInt(req.getParameter("a"));
		int b = Integer.parseInt(req.getParameter("b"));
		
		res.setContentType("text/plain");
		res.setCharacterEncoding("UTF-8");

		PrintWriter writer = res.getWriter();
		writer.println("a=" + a + "," + "b=" + b + "계신결과입니다.");
		writer.println("a + b = " +  (a + b));
		writer.println("a - b = " +  (a - b));
		writer.println("a * b = " +  (a * b));
		writer.println("a / b = " +  (a / b));
		
	}

}
