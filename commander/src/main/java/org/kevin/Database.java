package org.kevin;

import org.kevin.exceptions.DatabaseUnavailableException;

public abstract class Database<T> {

    public abstract T add(T obj) throws DatabaseUnavailableException;

    public abstract T get(String id) throws DatabaseUnavailableException;
}
