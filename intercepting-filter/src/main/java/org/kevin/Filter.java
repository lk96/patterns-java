package org.kevin;

public interface Filter {

    String execute(Order order);

    void setNext(Filter filter);

    Filter getNext();

    Filter getLast();
}
