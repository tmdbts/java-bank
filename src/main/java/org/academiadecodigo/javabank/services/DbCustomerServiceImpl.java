package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DbCustomerServiceImpl {

    EntityManagerFactory emf = EntityManagerFactoryCreatorService.getEmf();

    public Customer get(Integer id) {
        
        EntityManager em = emf.createEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);

        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("id"), id));

        return em.createQuery(criteriaQuery).getSingleResult();
    }
}
