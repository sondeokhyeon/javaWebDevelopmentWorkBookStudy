package exam04.jdbcSevletFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	FilterConfig config;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	} 
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain nextFilter)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding(config.getInitParameter("encoding"));
		nextFilter.doFilter(req, res);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}