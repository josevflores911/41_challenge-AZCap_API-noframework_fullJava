package model;

public enum RoomType {
    MEETING_ROOM,
    CONFERENCE_ROOM,
    TRAINING_ROOM,
    OTHER;

    // Método para obtener un model.RoomType a partir de una cadena de texto
    public static RoomType fromString(String type) {
        for (RoomType roomType : RoomType.values()) {
            if (roomType.name().equalsIgnoreCase(type)) {
                return roomType;
            }
        }
        return OTHER; // Devolver OTHER si el tipo no coincide con ninguno de los valores conocidos
    }
}