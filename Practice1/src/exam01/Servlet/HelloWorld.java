package exam01.Servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// servlet 구현

public class HelloWorld implements Servlet {
	ServletConfig arg;

	@Override
	public void init(ServletConfig arg) throws ServletException {
		System.out.println("init 호출");
		this.arg = arg;
		// TODO Auto-generated method stub
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy 호출");
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		System.out.println("getServletConfig 호출");
		return this.arg;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		System.out.println("getServletInfo 호출");
		return "version=1.0;author=sondeokhyeon;copyright=sondeokhyeon";
	}
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("service 호출");
		// TODO Auto-generated method stub
		
	}

}
