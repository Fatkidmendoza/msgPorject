package edu.msg.flightmanager.backend.repository.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.msg.flightmanager.backend.model.Airport;
import edu.msg.flightmanager.backend.model.FlightTemplate;
import edu.msg.flightmanager.backend.model.Itinerary;
import edu.msg.flightmanager.backend.repository.FlightTemplateRepository;
import edu.msg.flightmanager.backend.repository.RepositoryException;

@Stateless(name = "FlightTemplateRepository", mappedName = "ejb/FlightTemplateRepository")
public class FlightTemplateRepositoryBean extends BaseRepositoryBean<FlightTemplate, Long>
implements FlightTemplateRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(FlightTemplateRepositoryBean.class);

	public FlightTemplateRepositoryBean() {
		super(FlightTemplate.class);
	}

	@Override
	public List<FlightTemplate> getTheFlightTemplatesThatHaveAnItinerary(Itinerary itinerary)
			throws RepositoryException {
		try {
			LOGGER.info("Try to select flight templates that have a given itinerary.");
			TypedQuery<FlightTemplate> flightTemplates = entityManager.createQuery(
					"SELECT ft FROM FlightTemplate ft JOIN ft.itinerary i WHERE i.id = :id", FlightTemplate.class);
			flightTemplates.setParameter("id", itinerary.getId());
			List<FlightTemplate> queryResult = flightTemplates.getResultList();
			LOGGER.info("Flight templates that have a given itinerary selection successful");
			return queryResult;
		} catch (PersistenceException e) {
			LOGGER.error("Flight templates that have a given itinerary selection failed.", e);
			throw new RepositoryException("Flight templates that have a given itinerary selection failed.");
		}
	}

	@Override
	public List<FlightTemplate> getTheFlightTemplatesThatHaveAnAirport(Airport airport) throws RepositoryException {
		try {
			LOGGER.info("Try to select flight templates that have a given airport as arrival or destination.");
			TypedQuery<FlightTemplate> flightTemplates = entityManager.createQuery(
					"SELECT ft FROM FlightTemplate ft WHERE ft.arrival.id = :id1 OR ft.departure.id = :id2",
					FlightTemplate.class);
			flightTemplates.setParameter("id1", airport.getId());
			flightTemplates.setParameter("id2", airport.getId());
			List<FlightTemplate> queryResult = flightTemplates.getResultList();
			LOGGER.info("Flight templates that have a given airport as arrival or destination selection successful");
			return queryResult;
		} catch (PersistenceException e) {
			LOGGER.error("Flight templates that have a given airport as arrival or destination selection failed.", e);
			throw new RepositoryException("Flight templates that have a given airport selection failed.");
		}
	}

	@Override
	public FlightTemplate getFlightTemplateThatHasACode(String code) throws RepositoryException {
		try {
			LOGGER.info("Try to select the flight template that has the given code.");
			TypedQuery<FlightTemplate> flightTemplate = entityManager
					.createQuery("SELECT ft FROM FlightTemplate ft WHERE ft.code = :code", FlightTemplate.class);
			flightTemplate.setParameter("code", code);
			FlightTemplate queryResult = flightTemplate.getSingleResult();
			LOGGER.info("Flight template that has a given code selection successful.");
			return queryResult;
		} catch (PersistenceException e) {
			LOGGER.error("Flight template that has a given code selection failed.", e);
			throw new RepositoryException("Flight template that has a given code selection failed.");
		}
	}

}
