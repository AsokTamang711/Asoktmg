package com.example.lab_ems;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    public Label login;
    public PasswordField password;
    public TextField username;
    public Label errormessage;
    @FXML
    private Label welcomeText;

    

    public void login(ActionEvent actionEvent) {
        String uname = username.getText();
        String upass = password.getText();
        if (uname.equals("") && upass.equals("")) {
            errormessage.setText("Please, enter email and password");

        } else {
            String jdbcUrl = "jdbc:mysql://localhost:3306/labems";
            String dbUser = "root";
            String dbPassword = "";
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                    dbPassword)) {
                // Execute a SQL query to retrieve data from the database
                String query = "SELECT * FROM tbl_admin WHERE `email`='" + uname + "' and `password`='" + upass + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                // Populate the table with data from the database
                if (resultSet.next()) {
                    try {
                        Parent secondScene = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                        Stage secondStage = new Stage();
                        secondStage.setTitle("Dashboard");
                        secondStage.setScene(new Scene(secondScene));
                        Stage firstSceneStage = (Stage) username.getScene().getWindow();
                        firstSceneStage.close();


                        secondStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    errormessage.setText("Please, enter valid email/password");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }    }
