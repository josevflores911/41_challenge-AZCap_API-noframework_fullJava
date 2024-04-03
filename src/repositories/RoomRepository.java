package repositories;

import model.Reservation;
import model.Room;
import model.RoomType;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomRepository {
    List<Room> getAllRooms();
    List<Room> getAvailableRooms(RoomType roomType, int numberOfPeople, LocalDateTime startDateTime, LocalDateTime endDateTime);
    void addReservation(Room room, Reservation reservation);
    void removeReservation(Room room, Reservation reservation);

    void addRoom(Room newRoom);
}
