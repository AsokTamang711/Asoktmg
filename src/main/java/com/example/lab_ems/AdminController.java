package com.example.lab_ems;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class AdminController implements Initializable {

    public TextField iid;
    public TextField iname;
    public TextField iemail;
    public TextField ipassword;
    @FXML
    private TableView<Admin> tableView;
    @FXML
    private TableColumn<Admin,Integer > id;
    @FXML
    private TableColumn<Admin, String> name;
    @FXML
    private TableColumn<Admin,String> email;
    @FXML
    private TableColumn<Admin,String> password;
    ObservableList<Admin> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new
                PropertyValueFactory<Admin,Integer>("id"));
        name.setCellValueFactory(new
                PropertyValueFactory<Admin,String>("name"));
        email.setCellValueFactory(new
                PropertyValueFactory<Admin,String>("email"));
        password.setCellValueFactory(new
                PropertyValueFactory<Admin,String>("password"));
        tableView.setItems(list);
    }
    @FXML
    protected void onHelloButtonClick() {
        list.clear();tableView.setItems(list);
        populateTable();
    }
    public void populateTable() {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/labems";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM tbl_admin";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                tableView.getItems().add(new Admin(id, name, email,
                        password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadData(ActionEvent actionEvent) {

        String getid = iid.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/labems";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM tbl_admin WHERE `id`= '"+getid+"' ";
            Statement statement = connection.createStatement();
            try (ResultSet resultSet = statement.executeQuery(query)) {
                // Populate the table with data from the database
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");


                    iname.setText(name);
                    iemail.setText(String.valueOf(email));
                    ipassword.setText(String.valueOf(password));


                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }



    public void DeleteData(ActionEvent actionEvent) {

        String getid = iid.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/labems";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM tbl_admin WHERE `id`= '"+getid+"' ";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void InsertData(ActionEvent actionEvent) {

        String getid = iid.getText();
        String getName = iname.getText();
        String getemail = iemail.getText();
        String getpassword = ipassword.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/labems";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `tbl_admin`(`name`, `email`, `password`) VALUES ('"+getName+"','"+getemail+"','"+getpassword+"')";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void UpdateData(ActionEvent actionEvent) {

        String getid = iid.getText();
        String getName = iname.getText();
        String getemail = iemail.getText();
        String getpassword = ipassword.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/labems";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "UPDATE `tbl_admin` SET `name`='"+getName+"',`email`='"+getemail+"',`password`='"+getpassword+"' WHERE `id` = '"+getid+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void backbtn(ActionEvent actionEvent) {
        try {
            Parent secondScene = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("Dashboard");
            secondStage.setScene(new Scene(secondScene));
            Stage firstSceneStage = (Stage) iid.getScene().getWindow();
            firstSceneStage.close();


            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}