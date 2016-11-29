package edu.msg.flightmanager.web.filters;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.msg.flightmanager.backend.dto.UserDto;
import edu.msg.flightmanager.backend.exception.EntityNotFoundException;
import edu.msg.flightmanager.backend.exception.ExpiredTokenException;
import edu.msg.flightmanager.backend.service.AuthenticationService;
import edu.msg.flightmanager.backend.service.ServiceException;

@WebFilter(filterName = "TokenFilter", urlPatterns = { "/changePassword.xhtml" })
public class TokenFilter implements Filter {

	@EJB
	private AuthenticationService authenticationService;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String token = httpRequest.getParameter("token");
		if (token != null) {
			try {
				UserDto userDto = authenticationService.validateToken(token);
				request.setAttribute("userForChangePassword", userDto);
				chain.doFilter(request, response);
			} catch (ExpiredTokenException | EntityNotFoundException | ServiceException e) {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.xhtml");

			}
		} else {
			chain.doFilter(request, response);
			//			if (request.getAttribute("userForChangePassword") != null)
			//				chain.doFilter(request, response);
			//			else
			//				httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.xhtml");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
