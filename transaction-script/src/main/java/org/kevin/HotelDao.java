package org.kevin;

import java.util.Optional;
import java.util.stream.Stream;

public interface HotelDao {

    Stream<Room> getAll() throws Exception;

    Optional<Room> getById(int id) throws Exception;

    Boolean add(Room room) throws Exception;

    Boolean update(Room room) throws Exception;

    Boolean delete(Room room) throws Exception;
}
