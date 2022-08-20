import controller.ClientHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.ScanEnvironment;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerInitializer{

//    public static void main(String[] args) { launch(args); }
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("views/ServerForm.fxml"))));
//        primaryStage.show();
//    }
private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(50006);
        Socket accept;

        while (true) {
            System.out.println("waiting for client");
            accept = serverSocket.accept();
            System.out.println("new member connected!");
            ClientHandler clientHandler = new ClientHandler(accept, clientHandlers);
            clientHandlers.add(clientHandler);
            clientHandler.start();

        }


    }


}
