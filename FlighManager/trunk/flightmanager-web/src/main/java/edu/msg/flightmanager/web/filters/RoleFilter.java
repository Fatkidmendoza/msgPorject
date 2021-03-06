package edu.msg.flightmanager.web.filters;

import java.io.IOException;

import javax.faces.validator.ValidatorException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.msg.flightmanager.backend.dto.UserDto;
import edu.msg.flightmanager.web.util.Constants;

@WebFilter(filterName = "RoleFilter", urlPatterns = { "*.xhtml" })
public class RoleFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException, ValidatorException {
		try {

			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			HttpSession httpSession = httpRequest.getSession(false);

			String requestUrl = httpRequest.getRequestURI();

			boolean isPlanesPage = requestUrl.contains("/planes.xhtml");
			boolean isItinerariesPage = requestUrl.contains("/itineraries.xhtml");
			boolean isFlightTemplatesPage = requestUrl.contains("/flightTemplates.xhtml");
			boolean isAirportsPage = requestUrl.contains("/airports.xhtml");
			boolean isUsersPage = requestUrl.contains("/users.xhtml");
			boolean isAddAirportPage = requestUrl.contains("/addAirport.xhtml");
			boolean isAddPlanePage = requestUrl.contains("/addPlane.xhtml");
			boolean isAddUserPage = requestUrl.contains("/addUser.xhtml");
			boolean isAddCompanyPage = requestUrl.contains("/addCompany.xhtml");
			boolean isAddFlightPage = requestUrl.contains("/addFlight.xhtml");
			boolean isAddFlightTemplatePage = requestUrl.contains("/addFlightTemplate.xhtml");
			boolean isAddItineraryPage = requestUrl.contains("/addItinerary.xhtml");
			boolean isCompaniesPage = requestUrl.contains("/companies.xhtml");
			boolean isUserLoggedIn = httpSession != null && httpSession.getAttribute(Constants.USER) != null;
			boolean isManageData = requestUrl.indexOf("/manageData.xhtml") >= 0;
			boolean isErrorHome = requestUrl.indexOf("/errorHome.xhtml") >= 0;
			boolean isFlightHistoryPage = requestUrl.contains("/flightHistory.xhtml");
			boolean isCompanyAirportsPage = requestUrl.contains("/companyAirports.xhtml");

			boolean isReportsPage = requestUrl.indexOf("/reports.xhtml") >= 0;
			boolean isFlightsPage = requestUrl.indexOf("/flights.xhtml") >= 0;

			if (isUserLoggedIn) {
				UserDto userInSession = (UserDto) httpSession.getAttribute(Constants.USER);
				String role = userInSession.getType();
				if (role.equals(Constants.PILOT) || role.equals(Constants.STEWARD)) {
					if (isItinerariesPage || isAddPlanePage || isAddUserPage || isPlanesPage || isFlightTemplatesPage
							|| isUsersPage || isAirportsPage || isCompaniesPage || isManageData || isManageData
							|| isAddCompanyPage || isAddFlightPage || isAddFlightTemplatePage || isAddItineraryPage
							|| isReportsPage || isFlightsPage || isAddAirportPage || isFlightHistoryPage
							|| isCompanyAirportsPage) {
						httpResponse.sendRedirect(httpRequest.getContextPath() + "/home.xhtml");

					} else {
						chain.doFilter(request, response);
					}
				} else if (role.equals(Constants.FLIGHT_MANAGER)) {
					if (isItinerariesPage || isAddPlanePage || isAddUserPage || isPlanesPage || isUsersPage
							|| isAirportsPage || isCompaniesPage || isManageData || isManageData || isAddCompanyPage
							|| isAddItineraryPage || isReportsPage || isAddAirportPage || isCompanyAirportsPage) {
						httpResponse.sendRedirect(httpRequest.getContextPath() + "/home.xhtml");

					} else {
						chain.doFilter(request, response);
					}
				} else if (role.equals(Constants.COMPANY_MANAGER)) {
					if (isCompaniesPage || isFlightTemplatesPage || isManageData || isManageData || isFlightsPage
							|| isAddFlightPage || isCompanyAirportsPage || isAddCompanyPage
							|| isAddFlightTemplatePage) {
						httpResponse.sendRedirect(httpRequest.getContextPath() + "/home.xhtml");

					} else {
						chain.doFilter(request, response);
					}
				}

				else {
					chain.doFilter(request, response);
				}

			} else {
				chain.doFilter(request, response);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@Override
	public void destroy() {

	}

}
