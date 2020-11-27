package org.academiadecodigo.javabank.model;

import javax.persistence.Id;

/**
 * A generic model entity to be used as a base for concrete types of models
 */
public abstract class AbstractModel implements Model {

    @Id
    private Integer id;

    /**
     * @see Model#getId()
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * @see Model#setId(Integer)
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
