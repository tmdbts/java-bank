package org.academiadecodigo.javabank.model;

/**
 * Common interface for a model, provides methods to get and set ids
 */
public interface Model {

    /**
     * Gets the model id
     *
     * @return the model id
     */
    Integer getId();

    /**
     * Sets the model id
     *
     * @param id the id to set
     */
    void setId(Integer id);
}
