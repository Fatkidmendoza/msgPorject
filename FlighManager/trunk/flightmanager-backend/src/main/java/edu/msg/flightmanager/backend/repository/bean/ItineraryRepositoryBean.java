package edu.msg.flightmanager.backend.repository.bean;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.msg.flightmanager.backend.model.Itinerary;
import edu.msg.flightmanager.backend.repository.ItineraryRepository;
import edu.msg.flightmanager.backend.repository.RepositoryException;

@Stateless(name = "ItineraryRepository", mappedName = "ejb/ItineraryRepository")
public class ItineraryRepositoryBean extends BaseRepositoryBean<Itinerary, Long> implements ItineraryRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItineraryRepositoryBean.class);

	public ItineraryRepositoryBean() {
		super(Itinerary.class);
	}

	@Override
	public Itinerary getByCode(String code) throws RepositoryException {
		try {
			LOGGER.info("Try to select an itinerary by its code.");
			TypedQuery<Itinerary> itineraries = entityManager
					.createQuery("SELECT i FROM Itinerary i WHERE i.code= :code", Itinerary.class);
			itineraries.setParameter("code", code);
			Itinerary itinerary = itineraries.getSingleResult();
			LOGGER.info("Itinerary selection by code successful.");
			return itinerary;
		} catch (PersistenceException e) {
			LOGGER.error("Itinerary selection by code failed.", e);
			throw new RepositoryException("Itinerary selection by code failed.");
		}
	}

}
