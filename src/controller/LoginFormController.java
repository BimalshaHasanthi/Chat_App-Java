package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {
    public Button btnLogin;
    public TextField txtUserName;
    public TextField txtPassword;
    public AnchorPane mainContext;
    public AnchorPane loginContext;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        if(txtUserName.getText().equals("client")){
            URL resource = getClass().getResource("../views/ClientForm.fxml");
            Parent load = FXMLLoader.load(resource);
            loginContext.getChildren().clear();
            loginContext.getChildren().add(load);
            txtUserName.clear();
        }else if(txtUserName.getText().equals("server")){
            URL resource = getClass().getResource("../views/ServerForm.fxml");
            Parent load = FXMLLoader.load(resource);
            loginContext.getChildren().clear();
            loginContext.getChildren().add(load);
            txtUserName.clear();
        }else{
            URL resource = getClass().getResource("../views/LoginForm.fxml");
            Parent load = FXMLLoader.load(resource);
            loginContext.getChildren().clear();
            loginContext.getChildren().add(load);
            txtUserName.clear();
        }
    }
}
