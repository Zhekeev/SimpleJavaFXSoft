package sample.service.cashbox;

import java.io.IOException;
import java.net.URL;
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
import sample.entity.CashboxEntity;

public class Cashbox {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitButton;

    @FXML
    private TableView<CashboxEntity> table;

    @FXML
    private TableColumn<CashboxEntity, Integer> setEmployeeID;

    @FXML
    private TableColumn<CashboxEntity, Integer> setClientID;

    @FXML
    private TableColumn<CashboxEntity, Integer> setTourID;

    @FXML
    private TableColumn<CashboxEntity, Integer> setPrice;

    @FXML
    private TableColumn<CashboxEntity, String> setDate;

    @FXML
    private Button openButton;

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

        openButton.setOnAction(event -> {
            openButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/service/cashbox/cashboxadd.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });
        table.setItems(getCashbox());
        setEmployeeID.setCellValueFactory(new PropertyValueFactory<>("idEmployee"));
        setClientID.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        setTourID.setCellValueFactory(new PropertyValueFactory<>("idTour"));
        setPrice.setCellValueFactory(new PropertyValueFactory<>("amount"));
        setDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    public ObservableList<CashboxEntity> getCashbox() throws SQLException, ClassNotFoundException {
        String selectRequest = "SELECT * FROM Cashbox";
        ConnectionPool connectionPool = new ConnectionPool();
        ObservableList<CashboxEntity> res = FXCollections.observableArrayList();
        Statement statement = connectionPool.getConnection().createStatement();
        ResultSet set = statement.executeQuery(selectRequest);
        String date;
        int idEmployee, idClient, idTour,amount;
        while (set.next()) {
            idEmployee = set.getInt("id_employee");
            idClient = set.getInt("id_client");
            idTour = set.getInt("id_tour");
            amount = set.getInt("amount");
            date = set.getString("date");
            res.add(new CashboxEntity(idEmployee, idClient, idTour, amount,date));
        }
        return res;
    }
}
