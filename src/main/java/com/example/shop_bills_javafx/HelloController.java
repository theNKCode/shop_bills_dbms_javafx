package com.example.shop_bills_javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private AnchorPane main_form;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button loginBtn;
    @FXML
    private Button close;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void login() {
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        connect = database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            result = prepare.executeQuery();

            Alert alert;
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                if (result.next()) {
                    //CORRECT USERNAME AND PASSWORD
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Welcome Mr. Vaidya");
                    alert.showAndWait();
                    //TO HIDE LOGIN FORM
                    loginBtn.getScene().getWindow().hide();

                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                }else{
                    //Wrong username or  password
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Entered Wrong Username or Password");
                    alert.showAndWait();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }

    public void initialize(URL url, ResourceBundle rb) {

    }
}