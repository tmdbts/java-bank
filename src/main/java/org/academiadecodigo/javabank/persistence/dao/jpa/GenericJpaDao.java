package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.Model;
import org.academiadecodigo.javabank.persistence.TransactionException;
import org.academiadecodigo.javabank.persistence.dao.Dao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * A generic jpa data access object to be used as a base for concrete jpa service implementations
 *
 * @param <T> the model type
 * @see Dao
 */
public abstract class GenericJpaDao<T extends Model> implements Dao<T> {

    protected JpaSessionManager sm;
    private Class<T> modelType;

    /**
     * Initializes a new JPA DAO instance given a session manager and a model type
     *
     * @param sm        the session manager factory
     * @param modelType the model type
     */
    public GenericJpaDao(JpaSessionManager sm, Class<T> modelType) {

        this.sm = sm;
        this.modelType = modelType;
    }

    /**
     * @see Dao#findAll()
     */
    @Override
    public List<T> findAll() {

        try {
            EntityManager em = sm.getCurrentSession();

            return em.createQuery("from " + modelType.getSimpleName(), modelType).getResultList();
        } catch (PersistenceException ex) {
            throw new TransactionException(ex);
        }
    }

    /**
     * @see Dao#findById(Integer)
     */
    @Override
    public T findById(Integer id) {

        try {
            EntityManager em = sm.getCurrentSession();

            return em.find(modelType, id);
        } catch (PersistenceException ex) {
            throw new TransactionException(ex);
        }
    }

    /**
     * @see Dao#saveOrUpdate(Model)
     */
    @Override
    public T saveOrUpdate(T modelObject) {

        try {
            EntityManager em = sm.getCurrentSession();

            return em.merge(modelObject);
        } catch (PersistenceException ex) {
            throw new TransactionException(ex);
        }
    }

    /**
     * @see Dao#delete(Integer)
     */
    @Override
    public void delete(Integer id) {

        try {
            EntityManager em = sm.getCurrentSession();

            em.remove(em.find(modelType, id));
        } catch (PersistenceException ex) {
            throw new TransactionException(ex);
        }
    }
}
