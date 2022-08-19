package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {
    public Button btnLogin;
    public TextField txtUserName;
    public TextField txtPassword;
    public AnchorPane mainContext;
    public AnchorPane loginContext;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        if(txtUserName.getText().equals("client1")){
//            URL resource = getClass().getResource("../views/ClientForm.fxml");
//            Parent load = FXMLLoader.load(resource);
//            loginContext.getChildren().clear();
//            loginContext.getChildren().add(load);
//            txtUserName.clear();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(FXMLLoader.load(getClass().getResource("/views/ClientForm.fxml"))));
            stage2.setTitle("Let's Chat");
            stage2.setResizable(false);
            stage2.centerOnScreen();
            stage2.show();
        }else if(txtUserName.getText().equals("client2")){
//            URL resource = getClass().getResource("../views/Client2Form.fxml");
//            Parent load = FXMLLoader.load(resource);
//            loginContext.getChildren().clear();
//            loginContext.getChildren().add(load);
//            txtUserName.clear();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(FXMLLoader.load(getClass().getResource("/views/Client2Form.fxml"))));
            stage2.setTitle("Let's Chat");
            stage2.setResizable(false);
            stage2.centerOnScreen();
            stage2.show();
        }else{
            URL resource = getClass().getResource("../views/LoginForm.fxml");
            Parent load = FXMLLoader.load(resource);
            loginContext.getChildren().clear();
            loginContext.getChildren().add(load);
            txtUserName.clear();
        }
    }
}
