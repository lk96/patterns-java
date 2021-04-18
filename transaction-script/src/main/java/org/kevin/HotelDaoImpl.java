package org.kevin;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
public class HotelDaoImpl implements HotelDao {

    private final DataSource dataSource;

    public HotelDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Stream<Room> getAll() throws Exception {
        try {
            var connection = getConnection();
            var statement = connection.prepareStatement("SELECT * FROM ROOMS"); // NOSONAR
            var resultSet = statement.executeQuery(); // NOSONAR
            return StreamSupport.stream(new Spliterators.AbstractSpliterator<Room>(Long.MAX_VALUE,
                    Spliterator.ORDERED) {

                @Override
                public boolean tryAdvance(Consumer<? super Room> action) {
                    try {
                        if (!resultSet.next()) {
                            return false;
                        }
                        action.accept(createRoom(resultSet));
                        return true;
                    } catch (Exception e) {
                        throw new RuntimeException(e); // NOSONAR
                    }
                }
            }, false).onClose(() -> {
                try {
                    mutedClose(connection, statement, resultSet);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            });
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Room> getById(int id) throws Exception {
        ResultSet resultSet = null;

        try (var connection = getConnection();
             var statement = connection.prepareStatement("SELECT * FROM ROOMS WHERE ID = ?")) {

            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(createRoom(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    @Override
    public Boolean add(Room room) throws Exception {
        if (getById(room.getId()).isPresent()) {
            return false;
        }

        try (var connection = getConnection();
             var statement = connection.prepareStatement("INSERT INTO ROOMS VALUES (?,?,?,?)")) {
            statement.setInt(1, room.getId());
            statement.setString(2, room.getRoomType());
            statement.setInt(3, room.getPrice());
            statement.setBoolean(4, room.isBooked());
            statement.execute();
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Boolean update(Room room) throws Exception {
        try (var connection = getConnection();
             var statement =
                     connection
                             .prepareStatement("UPDATE ROOMS SET ROOM_TYPE = ?, PRICE = ?, BOOKED = ?"
                                     + " WHERE ID = ?")) {
            statement.setString(1, room.getRoomType());
            statement.setInt(2, room.getPrice());
            statement.setBoolean(3, room.isBooked());
            statement.setInt(4, room.getId());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Boolean delete(Room room) throws Exception {
        try (var connection = getConnection();
             var statement = connection.prepareStatement("DELETE FROM ROOMS WHERE ID = ?")) {
            statement.setInt(1, room.getId());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    private Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    private void mutedClose(Connection connection, PreparedStatement statement, ResultSet resultSet)
            throws Exception {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    private Room createRoom(ResultSet resultSet) throws Exception {
        return new Room(resultSet.getInt("ID"),
                resultSet.getString("ROOM_TYPE"),
                resultSet.getInt("PRICE"),
                resultSet.getBoolean("BOOKED"));
    }
}
