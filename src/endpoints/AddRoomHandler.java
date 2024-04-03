package endpoints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Room;
import repositories.RoomRepository;
import utils.LocalDateTimeAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class AddRoomHandler implements HttpHandler {

    private RoomRepository roomRepository;

    public AddRoomHandler(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Check if the request method is POST
        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            // If not, respond with error code 405 (Method Not Allowed)
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        // Get the request body as a string
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            requestBody.append(line);
        }
        br.close();
        isr.close();
        System.out.println("requestBody.toString()");
        System.out.println(requestBody.toString());
        // Parse the JSON data in the request body into a model.Room object

//        Gson gson = new Gson();
//        model.Room newRoom = gson.fromJson(requestBody.toString(), model.Room.class);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        Room newRoom=null;
        try{

            newRoom= gson.fromJson(requestBody.toString(), Room.class);
            System.out.println(newRoom);
        }catch (Exception e){
            System.out.println(e);
        }

        // Add the new room to the repository (assuming your repository has a method for adding rooms)
        roomRepository.addRoom(newRoom);

        // Respond with a success status code
        exchange.sendResponseHeaders(200, -1);
    }
}
