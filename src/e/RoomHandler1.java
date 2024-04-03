package e;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RoomHandler1 implements HttpHandler {

    // Simulamos una base de datos de salas
    private Map<Integer, String> rooms = new HashMap<>();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        URI uri = exchange.getRequestURI();
        String path = uri.getPath();

        switch (method) {
            case "GET":
                handleGet(exchange, path);
                break;
            case "POST":
                handlePost(exchange, path);
                break;
            case "PUT":
                handlePut(exchange, path);
                break;
            case "DELETE":
                handleDelete(exchange, path);
                break;
            default:
                sendResponse(exchange, 405, "Method Not Allowed");
                break;
        }
    }

    private void handleGet(HttpExchange exchange, String path) throws IOException {
        // Aquí implementamos la lógica para manejar una solicitud GET
        if ("/rooms".equals(path)) {
            // Respondemos con la lista de salas
            String response = rooms.toString();
            sendResponse(exchange, 200, response);
        } else {
            sendResponse(exchange, 404, "Not Found");
        }
    }

    private void handlePost(HttpExchange exchange, String path) throws IOException {
        // Aquí implementamos la lógica para manejar una solicitud POST
        if ("/rooms".equals(path)) {
            // Agregamos una nueva sala a la base de datos simulada
            //String requestBody = Utils.convertStreamToString(exchange.getRequestBody());
            //rooms.put(rooms.size() + 1, requestBody);
            sendResponse(exchange, 201, "Created");
        } else {
            sendResponse(exchange, 404, "Not Found");
        }
    }

    private void handlePut(HttpExchange exchange, String path) throws IOException {
        // Aquí implementamos la lógica para manejar una solicitud PUT
        if (path.startsWith("/rooms/")) {
            // Actualizamos los datos de una sala existente en la base de datos simulada
            //String requestBody = Utils.convertStreamToString(exchange.getRequestBody());
            int id = Integer.parseInt(path.substring("/rooms/".length()));
            //rooms.put(id, requestBody);
            sendResponse(exchange, 200, "OK");
        } else {
            sendResponse(exchange, 404, "Not Found");
        }
    }

    private void handleDelete(HttpExchange exchange, String path) throws IOException {
        // Aquí implementamos la lógica para manejar una solicitud DELETE
        if (path.startsWith("/rooms/")) {
            // Eliminamos una sala de la base de datos simulada
            int id = Integer.parseInt(path.substring("/rooms/".length()));
            rooms.remove(id);
            sendResponse(exchange, 200, "OK");
        } else {
            sendResponse(exchange, 404, "Not Found");
        }
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String message) throws IOException {
        exchange.sendResponseHeaders(statusCode, message.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(message.getBytes());
        os.close();
    }
}
