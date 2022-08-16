package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientFormController {

    public Button btnClientSend;
    public TextField txtClientMsg;

    Socket socket=null;

    public void initialize() throws IOException{
        new Thread(()->{
            try {
                socket=new Socket("localhost",5000);
                InputStreamReader inputStreamReader=new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String record=bufferedReader.readLine();
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }).start();

        System.out.println(socket);

    }

    public void clientSendMsgOnAction(ActionEvent actionEvent) throws IOException {

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(txtClientMsg.getText());
        printWriter.flush();
    }
}
