package org.academiadecodigo.javabank.converters;

import org.academiadecodigo.javabank.exceptions.JavaBankException;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A generic converter to be used as a base for concrete converter implementations
 *
 * @param <S> the source type
 * @param <T> the target type
 */
public abstract class AbstractConverter<S, T> implements Converter<S, T> {

    /**
     * Converts the source list of type S to target type T
     *
     * @param listToConvert the list to convert
     * @return the list of converted elements
     * @throws JavaBankException if cannot convert the source type to target type
     */
    public List<T> convert(List<S> listToConvert) {

        return listToConvert.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
