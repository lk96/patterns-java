package org.kevin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hotel {
    private final HotelDaoImpl hotelDao;

    public Hotel(HotelDaoImpl hotelDao) {
        this.hotelDao = hotelDao;
    }

    /**
     * Book a room.
     *
     * @param roomNumber room to book
     * @throws Exception if any error
     */
    public void bookRoom(int roomNumber) throws Exception {

        var room = hotelDao.getById(roomNumber);

        if (room.isEmpty()) {
            throw new Exception("Room number: " + roomNumber + " does not exist");
        } else {
            if (room.get().isBooked()) {
                throw new Exception("Room already booked!");
            } else {
                var updateRoomBooking = room.get();
                updateRoomBooking.setBooked(true);
                hotelDao.update(updateRoomBooking);
            }
        }
    }

    /**
     * Cancel a room booking.
     *
     * @param roomNumber room to cancel booking
     * @throws Exception if any error
     */
    public void cancelRoomBooking(int roomNumber) throws Exception {

        var room = hotelDao.getById(roomNumber);

        if (room.isEmpty()) {
            throw new Exception("Room number: " + roomNumber + " does not exist");
        } else {
            if (room.get().isBooked()) {
                var updateRoomBooking = room.get();
                updateRoomBooking.setBooked(false);
                int refundAmount = updateRoomBooking.getPrice();
                hotelDao.update(updateRoomBooking);

                log.info("Booking cancelled for room number: " + roomNumber);
                log.info(refundAmount + " is refunded");
            } else {
                throw new Exception("No booking for the room exists");
            }
        }
    }
}
