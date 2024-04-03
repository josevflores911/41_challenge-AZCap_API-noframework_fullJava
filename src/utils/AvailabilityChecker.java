package utils;

import model.Reservation;
import model.Room;

import java.time.LocalDateTime;
import java.util.List;

public class AvailabilityChecker {

    // Método para verificar la disponibilidad de una sala en un intervalo de tiempo específico
    public static boolean isRoomAvailable(Room room, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        // Verificar si la sala está disponible en el intervalo de tiempo especificado
        List<Reservation> reservations = room.getReservations();
        for (Reservation reservation : reservations) {
            LocalDateTime reservationStart = reservation.getStartTime();
            LocalDateTime reservationEnd = reservation.getEndTime();
            if (startDateTime.isBefore(reservationEnd) && endDateTime.isAfter(reservationStart)) {
                // Existe una reserva que se solapa con el intervalo de tiempo especificado
                return false;
            }
        }
        // La sala está disponible en el intervalo de tiempo especificado
        return true;
    }
}
