package com.example.lab_ems;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    public Button adminbtn;
    public Button employeebtn;
    public Button exitbtn;
    public Button logoutbtn;

    @FXML
    private Label welcomeText;


    public void Admin(ActionEvent actionEvent) {
        try {
            Parent secondScene = FXMLLoader.load(getClass().getResource("Admin.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("Admin");
            secondStage.setScene(new Scene(secondScene));
            Stage firstSceneStage = (Stage) adminbtn.getScene().getWindow();
            firstSceneStage.close();


            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void Employee(ActionEvent actionEvent) {
        try {
            Parent secondScene = FXMLLoader.load(getClass().getResource("Employee.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("Employee");
            secondStage.setScene(new Scene(secondScene));
            Stage firstSceneStage = (Stage) employeebtn.getScene().getWindow();
            firstSceneStage.close();


            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Exit(ActionEvent actionEvent) {
        System.exit(0);

    }

    public void Logout(ActionEvent actionEvent) {
        try {
            Parent secondScene = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("login");
            secondStage.setScene(new Scene(secondScene));
            Stage firstSceneStage = (Stage) logoutbtn.getScene().getWindow();
            firstSceneStage.close();


            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
