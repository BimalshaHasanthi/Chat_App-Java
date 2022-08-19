package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    public TextField txtServerMsg;
    public Button btnServerSendMsg;

    Socket accept=null;

    public void initialize(){
        new Thread(() -> {
            try {

                ServerSocket serverSocket=new ServerSocket(5000);
                System.out.println("Server Started");
                accept = serverSocket.accept();
                System.out.println("Server connected");

                InputStreamReader inputStreamReader=new InputStreamReader(accept.getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String record=bufferedReader.readLine();
                System.out.println(record);

            }catch (Exception e){
                e.printStackTrace();

            }
        }).start();
    }

    public void serverSendMsgOnAction(ActionEvent actionEvent) throws IOException {

        PrintWriter printWriter = new PrintWriter(accept.getOutputStream());
        printWriter.println(txtServerMsg.getText());
        printWriter.flush();

    }
}
