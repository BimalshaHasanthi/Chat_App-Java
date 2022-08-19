package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client2FormController extends Thread{
    public Button btnClientSend;
    public JFXTextField txtClientMsg;
    public VBox vBox;
    public AnchorPane emojiPaneContext;
    public Label lblClient2;



    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    public FileChooser chooser;
    public File path;
    public ImageView imgEmoji;


    String[] imageList=new String[10];

    public void initialize() throws IOException {
        emojiPaneContext.setVisible(false);

//        String userName = LoginFormController.userName;
//        lblUser.setText("Client1");

        try {
            socket = new Socket("localhost", 10002);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            this.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < imageList.length; i++) {
            imageList[i] = "asserts/" + (i + 1) + ".png";
            //System.out.println(ePath[i]);

        }

    }

    public void clientSendMsgOnAction(ActionEvent actionEvent) {
        String massage = txtClientMsg.getText();
        printWriter.println(lblClient2.getText() + ": " +massage);
        txtClientMsg.clear();
        printWriter.flush();
        if (massage.equalsIgnoreCase("exit")) {
            Stage stage = (Stage) txtClientMsg.getScene().getWindow();
            stage.close();
        }
    }

    public void sendFileClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        chooser = new FileChooser();
        chooser.setTitle("Open Image");
        this.path = chooser.showOpenDialog(stage);
        printWriter.println(lblClient2.getText() + ": " +"img " + path.getPath());
        printWriter.flush();
    }

    public void openEmojiPaneOnAction(MouseEvent mouseEvent) {
        if (!emojiPaneContext.isVisible()) {
            emojiPaneContext.setVisible(true);
        } else {
            emojiPaneContext.setVisible(false);
        }
    }

    public void run() {
        try {
            while (true) {
                String massage = bufferedReader.readLine();
                String[] tokens = massage.split(" ");
                String command = tokens[0];

                StringBuilder clientMassage = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    clientMassage.append(tokens[i] + " ");
                }

                String[] massageAr = massage.split(" ");
                String string = "";
                for (int i = 0; i < massageAr.length - 1; i++) {
                    string += massageAr[i + 1] + " ";
                }

                Text text = new Text(string);
                String fChar = "";

                if (string.length() > 3) {
                    fChar = string.substring(0, 3);
                }

                if (fChar.equalsIgnoreCase("img")) {
                    string = string.substring(3, string.length() - 1);

                    File file = new File(string);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);

                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);

                    if (!command.equalsIgnoreCase(lblClient2.getText())) {
                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);

                        Text text1 = new Text("  " + command + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);
                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text(": Me ");
                        hBox.getChildren().add(text1);
                    }

                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));

                } else {
                    TextFlow tempTextFlow = new TextFlow();

                    if (!command.equalsIgnoreCase(lblClient2.getText() + ":")) {
                        Text name = new Text(command + " ");
                        name.getStyleClass().add("name");
                        tempTextFlow.getChildren().add(name);
                    }

                    tempTextFlow.getChildren().add(text);
                    tempTextFlow.setMaxWidth(200);

                    TextFlow textFlow = new TextFlow(tempTextFlow);
                    HBox hBox = new HBox(10);

                    if (!command.equalsIgnoreCase(lblClient2.getText() + ":")) {
                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(textFlow);
                    } else {
                        Text text1 = new Text(clientMassage + ": Me");
                        TextFlow textFlow1 = new TextFlow(text1);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(textFlow1);
                    }
                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEmojiOnAction(MouseEvent mouseEvent) {
//        if (mouseEvent.getSource ( ) instanceof ImageView) {
//            ImageView icon = (ImageView) mouseEvent.getSource();
//            switch (icon.getId()) {
//                case "emoji2":
//                    byte[] emojiBytes1 = new byte[]{(byte) 0xF0, (byte) 0xE2, (byte) 0x9D, (byte) 0xA4};
//                    String emojiAsString1 = new String(emojiBytes1, StandardCharsets.UTF_8);
//                    if (txtClientMsg.getText().equalsIgnoreCase("") || txtClientMsg.getText().equalsIgnoreCase(null)) {
//                        ImageView imageView = new ImageView();
//                        Image image = new Image(imageList[0]);
//                        imageView.setImage(image);
//                        imageView.setFitWidth(50);
//                        imageView.setFitHeight(50);
//                        VBox vBoxSub = new VBox(imageView);
//                        vBoxSub.setAlignment(Pos.CENTER_LEFT);
//                        vBoxSub.setPadding(new Insets(5, 10, 5, 5));
//                        vBox.getChildren().add(vBoxSub);
////                        printWriter.println(lblClient1 + ": " + imageList[0]);
//                        imgEmoji.setVisible(true);
//                    } else {
//                        txtClientMsg.appendText(emojiAsString1);
//
//                    }
//                    break;
//                case "emoji1":
//                    byte[] emojiBytes2 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0xA1};
//                    String emojiAsString2 = new String(emojiBytes2, StandardCharsets.UTF_8);
//                    if (txtClientMsg.getText().equalsIgnoreCase("") || txtClientMsg.getText().equalsIgnoreCase(null)) {
//                        ImageView imageView = new ImageView();
//                        Image image = new Image(imageList[1]);
//                        imageView.setImage(image);
//                        imageView.setFitWidth(50);
//                        imageView.setFitHeight(50);
//                        VBox vBoxSub = new VBox(imageView);
//                        vBoxSub.setAlignment(Pos.CENTER_LEFT);
//                        vBoxSub.setPadding(new Insets(5, 10, 5, 5));
//                        vBox.getChildren().add(vBoxSub);
//                        //printWriter.println(lblClient1 + ": " + imageList[1]);
//                        imgEmoji.setVisible(true);
//                    } else {
//                        txtClientMsg.appendText(emojiAsString2);
//                    }
//                    break;
//
//            }
//        }



    }


    public void sendEmoji2OnAction(MouseEvent mouseEvent) {
        txtClientMsg.appendText("\uD83D\uDE42");
    }

    public void sendEmoji1OnAction(MouseEvent mouseEvent) {
        txtClientMsg.appendText("\uD83D\uDE01");
    }
}
