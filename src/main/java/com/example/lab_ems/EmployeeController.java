package com.example.lab_ems;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    public TextField iid;
    public TextField iname;
    public TextField iaddress;
    public TextField isalary;
    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee,Integer > id;
    @FXML
    private TableColumn<Employee, String> name;
    @FXML
    private TableColumn<Employee,String> address;
    @FXML
    private TableColumn<Employee,String> salary;
    ObservableList<Employee> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new
                PropertyValueFactory<Employee,Integer>("id"));
        name.setCellValueFactory(new
                PropertyValueFactory<Employee,String>("name"));
        address.setCellValueFactory(new
                PropertyValueFactory<Employee,String>("address"));
        salary.setCellValueFactory(new
                PropertyValueFactory<Employee,String>("salary"));
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
            String query = "SELECT * FROM tbl_employee";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String salary = resultSet.getString("salary");

                tableView.getItems().add(new Employee(id, name, address,
                        salary));
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
            String query = "SELECT * FROM tbl_employee WHERE `id`= '"+getid+"' ";
            Statement statement = connection.createStatement();
            try (ResultSet resultSet = statement.executeQuery(query)) {
                // Populate the table with data from the database
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    String salary = resultSet.getString("salary");


                    iname.setText(name);
                    iaddress.setText(String.valueOf(address));
                    isalary.setText(String.valueOf(salary));


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
            String query = "DELETE FROM tbl_employee WHERE `id`= '"+getid+"' ";
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
        String getaddress = iaddress.getText();
        String getsalary = isalary.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/labems";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `tbl_employee`(`name`, `address`, `salary`) VALUES ('"+getName+"','"+getaddress+"','"+getsalary+"')";
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
        String getaddress = iaddress.getText();
        String getsalary = isalary.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/labems";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "UPDATE `tbl_employee` SET `name`='"+getName+"',`address`='"+getaddress+"',`salary`='"+getsalary+"' WHERE `id` = '"+getid+"'";
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