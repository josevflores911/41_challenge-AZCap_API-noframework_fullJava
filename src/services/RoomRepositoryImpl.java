package services;

import model.Reservation;
import model.Room;
import model.RoomType;
import repositories.RoomRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {

    private List<Room> rooms;

    public RoomRepositoryImpl() {
        // Inicializar la lista de salas (puede cargar datos de una base de datos aquí)
        rooms = new ArrayList<>();
        // Agregar algunas salas de ejemplo
        Room room1 = new Room(1, "Olimpo",RoomType.MEETING_ROOM, 10);
        Room room2 = new Room(2, "Atenas",RoomType.CONFERENCE_ROOM, 20);
        rooms.add(room1);
        rooms.add(room2);
    }

    @Override
    public List<Room> getAllRooms() {
        return rooms;
    }

    @Override
    public List<Room> getAvailableRooms(RoomType roomType, int numberOfPeople, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            boolean isAvailable = true;
            // Verificar disponibilidad para cada sala
            for (Reservation reservation : room.getReservations()) {
                if (reservation.getStartTime().isBefore(endDateTime) &&
                        reservation.getEndTime().isAfter(startDateTime)) {
                    isAvailable = false;
                    break;
                }
            }
            // Si la sala es del tipo correcto y tiene suficiente capacidad y está disponible, agregarla a la lista de salas disponibles
            if (room.getType() == roomType && room.getCapacity() >= numberOfPeople && isAvailable) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    @Override
    public void addReservation(Room room, Reservation reservation) {
        room.getReservations().add(reservation);
    }

    @Override
    public void removeReservation(Room room, Reservation reservation) {
        room.getReservations().remove(reservation);
    }

    @Override
    public void addRoom(Room newRoom) {
        int lastId=rooms.get(rooms.size()-1).getId();

        if(newRoom.getId()==0){
            newRoom.setId(++lastId);
        }

        rooms.add(newRoom);
    }
}
