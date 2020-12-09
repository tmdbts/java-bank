package org.academiadecodigo.javabank.services.mock;

import org.academiadecodigo.javabank.persistence.model.AbstractModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A generic mock service to be used as a base for concrete mock service implementations
 *
 * @param <T> the model type
 */
@Service
@Profile("test")
public abstract class AbstractMockService<T extends AbstractModel> {

    protected Map<Integer, T> modelMap = new HashMap<>();

    /**
     * Gets the next model id
     *
     * @return the model id
     */
    protected Integer getNextId() {
        return modelMap.isEmpty() ? 1 : Collections.max(modelMap.keySet()) + 1;
    }
}
