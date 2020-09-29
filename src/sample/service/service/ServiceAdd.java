package sample.service.service;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.connectionPool.ConnectionPool;
import sample.entity.ServiceEntity;

public class ServiceAdd {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TextField setName;

    @FXML
    private Button addButton;

    @FXML
    private TextField setPrice;

    @FXML
    private TextField setDescription;

    @FXML
    private TextField setId;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField setIdForUPDT;

    @FXML
    private Button updateButton;

    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            try {
                registerService();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        deleteButton.setOnAction(event -> {
            String getId = setId.getText();
            int id = Integer.parseInt(getId);
            deleteService(id);
        });
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/service/service/service.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });
        updateButton.setOnAction(event -> {
            try {
                registerUpdateService();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
    public void registerService() throws SQLException, ClassNotFoundException {
        String name = setName.getText();
        String getPrice = setPrice.getText();
        int price = Integer.parseInt(getPrice);
        String description = setDescription.getText();
        ServiceEntity client = new ServiceEntity(name, price, description);
        ServiceAdd register = new ServiceAdd();
        register.addService(client);
    }

    private void addService(ServiceEntity service) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "insert into Services (name, price,description) values (?,?,?)";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setString(1, service.getName());
        preparedStatement.setInt(2,service.getPrice());
        preparedStatement.setString(3,service.getDescription());
        preparedStatement.executeUpdate();
    }

    public void registerUpdateService() throws SQLException, ClassNotFoundException {
        String name = setName.getText();
        String getPrice = setPrice.getText();
        int price = Integer.parseInt(getPrice);
        String description = setDescription.getText();
        String getID = setIdForUPDT.getText();
        int id = Integer.parseInt(getID);
        ServiceEntity client = new ServiceEntity(name, price, description);
        ServiceAdd register = new ServiceAdd();
        register.updateService(client,id);
    }

    private void updateService(ServiceEntity service, int id) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "update Services set name = ?, price = ?,description = ? where id_services = ?";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setString(1, service.getName());
        preparedStatement.setInt(2,service.getPrice());
        preparedStatement.setString(3,service.getDescription());
        preparedStatement.setInt(4,id);
        preparedStatement.executeUpdate();
    }
    private void deleteService(int id){
        ConnectionPool connectionPool = new ConnectionPool();
        String remove = "delete  from  Services where id_services=?";
        try {
            PreparedStatement newData = connectionPool.getConnection().prepareStatement(remove);
            newData.setInt(1,id);
            newData.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
