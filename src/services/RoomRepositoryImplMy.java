package services;

import model.Reservation;
import model.Room;
import model.RoomType;
import repositories.RoomRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoomRepositoryImplMy implements RoomRepository {

    private Connection connection;

    public RoomRepositoryImplMy() {
        // Establecer la conexión con la base de datos
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_name", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        // Consultar todas las salas de la base de datos y crear objetos model.Room
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM rooms");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                String name = resultSet.getString("roomName");
                int capacity = resultSet.getInt("capacity");
                Room room = new Room(id, name,RoomType.valueOf(type), capacity);
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public List<Room> getAvailableRooms(RoomType roomType, int numberOfPeople, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<Room> availableRooms = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM rooms WHERE type = ? AND capacity >= ?");
            statement.setString(1, roomType.name());
            statement.setInt(2, numberOfPeople);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int roomId = resultSet.getInt("id");
                Room room = getRoomById(roomId);
                if (isRoomAvailable(room, startDateTime, endDateTime)) {
                    availableRooms.add(room);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableRooms;
    }

    @Override
    public void addReservation(Room room, Reservation reservation) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO reservations (room_id, start_datetime, end_datetime, number_of_people) VALUES (?, ?, ?, ?)");
            statement.setInt(1, room.getId());
            statement.setTimestamp(2, Timestamp.valueOf(reservation.getStartTime()));
            statement.setTimestamp(3, Timestamp.valueOf(reservation.getEndTime()));
            statement.setInt(4, reservation.getNumberOfPeople());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeReservation(Room room, Reservation reservation) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM reservations WHERE room_id = ? AND start_datetime = ? AND end_datetime = ?");
            statement.setInt(1, room.getId());
            statement.setTimestamp(2, Timestamp.valueOf(reservation.getStartTime()));
            statement.setTimestamp(3, Timestamp.valueOf(reservation.getEndTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRoom(Room newRoom) {

    }

    private Room getRoomById(int roomId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM rooms WHERE id = ?");
        statement.setInt(1, roomId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String type = resultSet.getString("type");
            int capacity = resultSet.getInt("capacity");
            String name= resultSet.getString("roomName");
            return new Room(roomId, name,RoomType.valueOf(type), capacity);
        } else {
            throw new IllegalArgumentException("model.Room with ID " + roomId + " not found");
        }
    }

    private boolean isRoomAvailable(Room room, LocalDateTime startDateTime, LocalDateTime endDateTime) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservations WHERE room_id = ? AND (? < end_datetime AND ? > start_datetime)");
        statement.setInt(1, room.getId());
        statement.setTimestamp(2, Timestamp.valueOf(startDateTime));
        statement.setTimestamp(3, Timestamp.valueOf(endDateTime));
        ResultSet resultSet = statement.executeQuery();
        return !resultSet.next(); // Si no hay resultados, la sala está disponible en ese intervalo de tiempo
    }

}
