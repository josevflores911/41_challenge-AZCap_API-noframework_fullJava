package model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int id;
    private RoomType type;
    private String roomName;
    private int capacity;
    private List<Reservation> reservations; // Lista de reservas para esta sala



    public Room(int id,String roomName, RoomType type, int capacity) {
        this.id = id;
        this.type = type;
        this.capacity = capacity;
        this.roomName=roomName;
        this.reservations = new ArrayList<>();
    }

    // Getters y setters para los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    // Método para verificar la disponibilidad de la sala para una reserva específica
    public boolean isAvailable(Reservation reservation) {
        // Verificar si hay superposición de horarios con las reservas existentes
        for (Reservation existingReservation : reservations) {
            if (existingReservation.overlapsWith(reservation)) {
                return false; // Hay superposición, la sala no está disponible
            }
        }
        return true; // No hay superposición, la sala está disponible
    }

    @Override
    public String toString() {
        return "model.Room{" +
                "id=" + id +
                ", type=" + type +
                ", capacity=" + capacity +
                ", reservations=" + reservations +
                '}';
    }
}
