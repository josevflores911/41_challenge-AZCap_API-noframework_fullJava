package endpoints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Room;
import repositories.RoomRepository;
import utils.LocalDateTimeAdapter;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

public class RoomHandler implements HttpHandler {

    private RoomRepository roomRepository;

    public RoomHandler(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Definimos el cuerpo de la respuesta
//        String response = "Hello, this is the room endpoint!";

        // Obtenemos la lista de salas disponibles
        List<Room> rooms = roomRepository.getAllRooms();
        //System.out.println(rooms.toString());
        //String response = rooms.toString();

        // Convertimos la lista de salas a formato JSON
//        Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        String jsonResponse="";
        try{

            jsonResponse= gson.toJson(rooms);
            System.out.println(jsonResponse);
        }catch (Exception e){
            System.out.println(e);
        }


        // Configuramos la cabecera de la respuesta con el código 200 (OK)
        exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);

        // Obtenemos el flujo de salida para escribir la respuesta
        OutputStream os = exchange.getResponseBody();
        os.write(jsonResponse.getBytes());
        os.close();
    }
}


