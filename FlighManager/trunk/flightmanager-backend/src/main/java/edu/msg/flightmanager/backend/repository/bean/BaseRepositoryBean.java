package edu.msg.flightmanager.backend.repository.bean;

import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.msg.flightmanager.backend.exception.DifferentVersionException;
import edu.msg.flightmanager.backend.model.AbstractModel;
import edu.msg.flightmanager.backend.repository.BaseRepository;
import edu.msg.flightmanager.backend.repository.RepositoryException;

public class BaseRepositoryBean<M extends AbstractModel, I> implements BaseRepository<M, I> {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseRepositoryBean.class);

	@PersistenceContext(unitName = "flight_manager")
	protected EntityManager entityManager;

	private Class<M> entityClass;

	public BaseRepositoryBean(Class<M> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public List<M> getAll() throws RepositoryException {
		try {
			LOGGER.info("Try to select all entities.");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<M> cq = cb.createQuery(entityClass);
			Root<M> root = cq.from(entityClass);
			CriteriaQuery<M> allEntities = cq.select(root);
			TypedQuery<M> tq = entityManager.createQuery(allEntities);
			LOGGER.info("Entities selection successful.");
			return tq.getResultList();
		} catch (PersistenceException ex) {
			LOGGER.error("Entities selection failed.", ex);
			throw new RepositoryException("Entities selection failed.");
		}
	}

	@Override
	public M insert(M model) throws RepositoryException {
		try {
			LOGGER.info("Try to insert an entity.");
			entityManager.persist(model);
			entityManager.flush();
			LOGGER.info("Entity insertion successful.");
			return model;
		} catch (PersistenceException | DatabaseException | EJBTransactionRolledbackException e) {
			LOGGER.error("Entity insertion failed.", e);
			throw new RepositoryException("Entity insertion failed.");
		}
	}

	@Override
	public M update(M model) throws DifferentVersionException, RepositoryException {
		try {
			LOGGER.info("Try to update an entity.");
			model = entityManager.merge(model);
			entityManager.flush();
			LOGGER.info("Entity update successful");
			return model;
		} catch (OptimisticLockException e) {
			LOGGER.error("Entity update failed, not the same version.", e);
			throw new DifferentVersionException("Entity update failed, not the same version.");
		} catch (DatabaseException | PersistenceException e) {
			LOGGER.error("Entity update failed.", e);
			throw new RepositoryException("Entity update failed.");
		}

	}

	/**
	 * delete operation it will be only logical, with changing the delete flag
	 * to true
	 */
	@Override
	public void delete(M model) throws RepositoryException,DifferentVersionException {
		try {
			LOGGER.info("Try to logically delete an entity.");
			model.setDeleted(true);
			model = entityManager.merge(model);
			entityManager.flush();
			LOGGER.info("Entity logical deletion successful.");
		} catch (OptimisticLockException e) {
			LOGGER.error("Entity logically delete failed, not the same version.", e);
			throw new DifferentVersionException("Entity logically delete failed, not the same version.");
		} catch (PersistenceException e) {
			LOGGER.error("Entity logical deletion failed.", e);
			throw new RepositoryException("Entity logical deletion failed.");
		}
	}

	@Override
	public M getById(I id) throws RepositoryException,DifferentVersionException {
		try {
			LOGGER.info("Try to get an entity by its id.");
			M model = entityManager.find(entityClass, id);
			LOGGER.info("Entity selection by id successful.");
			return model;
		}catch (OptimisticLockException e) {
			LOGGER.error("Entity activation failed, not the same version.", e);
			throw new DifferentVersionException("Entity activation failed, not the same version.");
		} catch (PersistenceException | NullPointerException e) {
			LOGGER.error("Entity selection by id failed.", e);
			throw new RepositoryException("Entity selection by id failed.");
		}
	}

	/**
	 * the activation consist, in changing the flag deleted to false. Enable
	 * entity, that was deleted.
	 */
	@Override
	public void activate(M model) throws RepositoryException,DifferentVersionException {
		try {
			LOGGER.info("Try to activate an entity.");
			model.setDeleted(false);
			model = entityManager.merge(model);
			entityManager.flush();
			LOGGER.info("Entity activation successful.");
		} catch (OptimisticLockException e) {
			LOGGER.error("Entity activation failed, not the same version.", e);
			throw new DifferentVersionException("Entity activation failed, not the same version.");
		} catch (PersistenceException e) {
			LOGGER.error("Entity activation failed.", e);
			throw new RepositoryException("Entity activation failed.");
		}
	}

}
