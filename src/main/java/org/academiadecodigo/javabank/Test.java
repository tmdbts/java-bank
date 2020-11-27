package org.academiadecodigo.javabank;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        emf.close();
    }
}
