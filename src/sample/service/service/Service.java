package sample.service.service;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.connectionPool.ConnectionPool;
import sample.entity.ServiceEntity;

public class Service {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ServiceEntity> table;

    @FXML
    private TableColumn<ServiceEntity, Integer> setID;

    @FXML
    private TableColumn<ServiceEntity, String> setName;

    @FXML
    private TableColumn<ServiceEntity, Integer> setPrice;

    @FXML
    private TableColumn<ServiceEntity, String> setDescription;

    @FXML
    private Button openButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button getTourButton;

    @FXML
    private Button getCountryButton;

    @FXML
    private Button getClientButton;

    @FXML
    private Button getServiceButton;

    @FXML
    private Button getCashboxButton;

    @FXML
    private Button getContractButton;

    @FXML
    private Button openSCButton;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        exitButton.setOnAction(event -> {
            exitButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/service/authorization/sample.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });
        getTourButton.setOnAction(event -> {
            getTourButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/service/tour/tour.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });
        getCountryButton.setOnAction(event -> {
            getCountryButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/service/country/country.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });
        getClientButton.setOnAction(event -> {
            getClientButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/service/client/client.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });
        getServiceButton.setOnAction(event -> {
            getServiceButton.getScene().getWindow().hide();
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
        getCashboxButton.setOnAction(event -> {
            getCashboxButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/service/cashbox/cashbox.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });
        getContractButton.setOnAction(event -> {
            getContractButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/service/contract/contract.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });
        openSCButton.setOnAction(event -> {
            openSCButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/service/servicecontract/servicecontract.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });
        openButton.setOnAction(event -> {
            openButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/service/service/serviceadd.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });
        table.setItems(getService());
        setID.setCellValueFactory(new PropertyValueFactory<>("id"));
        setName.setCellValueFactory(new PropertyValueFactory<>("name"));
        setPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        setDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }
    public ObservableList<ServiceEntity> getService() throws SQLException, ClassNotFoundException {
        String selectRequest = "SELECT * FROM Services";
        Connection connection = null;
        ConnectionPool connectionPool = new ConnectionPool();
        ObservableList<ServiceEntity> res = FXCollections.observableArrayList();
        Statement statement = connectionPool.getConnection().createStatement();
        ResultSet set = statement.executeQuery(selectRequest);
        String name,description;
        int id, price;
        while (set.next()) {
            id = set.getInt("id_services");
            name = set.getString("name");
            price = set.getInt("price");
            description = set.getString("description");
            res.add(new ServiceEntity(id, name, price, description));
        }
        return res;
    }
}
