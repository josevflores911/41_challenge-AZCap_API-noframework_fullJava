package model;

import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private Room room;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int numberOfPeople;

    public Reservation(int id, Room room, LocalDateTime startTime, LocalDateTime endTime, int numberOfPeople) {
        this.id = id;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfPeople = numberOfPeople;
    }

    // Getters y setters para los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    // Método para verificar si hay superposición de horarios con otra reserva
    public boolean overlapsWith(Reservation other) {
        return this.startTime.isBefore(other.endTime) && other.startTime.isBefore(this.endTime);
    }
}
