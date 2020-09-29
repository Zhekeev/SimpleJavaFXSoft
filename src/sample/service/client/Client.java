package sample.service.client;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
import javafx.scene.control.TextField;
import sample.entity.ClientEntity;
import sample.connectionPool.ConnectionPool;

public class Client {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ClientEntity> table;

    @FXML
    private TableColumn<ClientEntity, Integer> clientID;

    @FXML
    private TableColumn<ClientEntity, String > clientFirstName;

    @FXML
    private TableColumn<ClientEntity, String> clientLastName;

    @FXML
    private TableColumn<ClientEntity, String> clientAddress;

    @FXML
    private TableColumn<ClientEntity, String> clientPhoneNumber;

    @FXML
    private TableColumn<ClientEntity, String> clientGender;

    @FXML
    private TableColumn<ClientEntity, String> clientINN;

    @FXML
    private TableColumn<ClientEntity, String> clientDateOfINN;

    @FXML
    private Button clientAddButton;

    @FXML
    private TextField clientDelete;

    @FXML
    private Button deleteButton;

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
    void initialize() throws SQLException, ClassNotFoundException {
        clientAddButton.setOnAction(event -> {
            clientAddButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/service/client/clientadd.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });
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

        deleteButton.setOnAction(event -> {
            String getId = clientDelete.getText();
            int id = Integer.parseInt(getId);
            deleteClient(id);
        });
        table.setItems(getClient());
        clientID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        clientLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        clientAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clientPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        clientGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clientINN.setCellValueFactory(new PropertyValueFactory<>("IIN"));
        clientDateOfINN.setCellValueFactory(new PropertyValueFactory<>("dateOfIIN"));
    }

    private void deleteClient(int id){
        ConnectionPool connectionPool = new ConnectionPool();
        Connection connection = null;
        String remove = "delete  from  Client where id_client=?";
        try {
            PreparedStatement newData = connectionPool.getConnection().prepareStatement(remove);
            newData.setInt(1,id);
            newData.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<ClientEntity> getClient() throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String selectRequest = "select * from Client";
        ObservableList<ClientEntity> res = FXCollections.observableArrayList();
        Statement statement = connectionPool.getConnection().createStatement();
        ResultSet set = statement.executeQuery(selectRequest);

        String fitstName, lastName,address,phoneNumber,gender,IIN,dateOfIIN;
        int id;
        while (set.next()) {
            id = set.getInt("id_client");
            fitstName = set.getString("first_name");
            lastName = set.getString("last_name");
            address = set.getString("address");
            phoneNumber = set.getString("phone_number");
            gender = set.getString("gender");
            IIN = set.getString("IIN");
            dateOfIIN = set.getString("date_of_IIN");
            res.add(new ClientEntity(id,fitstName,lastName,address,phoneNumber,gender,IIN,dateOfIIN));
        }
        return res;
    }
}
