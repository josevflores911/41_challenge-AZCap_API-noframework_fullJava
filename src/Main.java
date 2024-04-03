import com.sun.net.httpserver.HttpServer;
import endpoints.AddRoomHandler;
import endpoints.RoomHandler;
import repositories.RoomRepository;
import services.RoomRepositoryImpl;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        // Creamos el servidor HTTP en el puerto 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Creamos una instancia del repositorio de salas
        RoomRepository roomRepository = new RoomRepositoryImpl();

        // Configuramos los manejadores de las rutas
        server.createContext("/rooms/all", new RoomHandler(roomRepository));
        server.createContext("/rooms/add", new AddRoomHandler(roomRepository));

        // Iniciamos el servidor
        server.start();

        System.out.println("Servidor iniciado en el puerto 8000...");
    }
}
//repos
//https://mvnrepository.com/artifact/com.google.code.gson/gson/2.9.1
