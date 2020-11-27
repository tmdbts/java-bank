package org.academiadecodigo.javabank.services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryCreatorService {

    private static EntityManagerFactory emf;

    public EntityManagerFactoryCreatorService() {
        emf = Persistence.createEntityManagerFactory("test");

    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }
}
