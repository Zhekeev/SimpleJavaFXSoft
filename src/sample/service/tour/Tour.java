package sample.service.tour;

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
import sample.connectionPool.ConnectionPool;
import sample.entity.TourEntity;

public class Tour {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TourEntity> table;

    @FXML
    private TableColumn<TourEntity, Integer> tourID;

    @FXML
    private TableColumn<TourEntity, String> tourName;

    @FXML
    private TableColumn<TourEntity, Integer> tourPrice;

    @FXML
    private TableColumn<TourEntity, Integer> tourDuration;

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
    private Button tourAddButton;

    @FXML
    private TextField setIdDelete;

    @FXML
    private Button tourDeleteButton;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        tourAddButton.setOnAction(event -> {
            tourAddButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/service/tour/touradd.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });

        tourDeleteButton.setOnAction(event -> {
            String getId = setIdDelete.getText();
            int id = Integer.parseInt(getId);
            deleteTour(id);
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

        table.setItems(getUsers());
        tourID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tourName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tourPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tourDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }
    private void deleteTour(int id){
        ConnectionPool connectionPool = new ConnectionPool();
        String remove = "delete  from  Tours where id_tour=?";
        try {
            PreparedStatement newData = connectionPool.getConnection().prepareStatement(remove);
            newData.setInt(1,id);
            newData.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<TourEntity> getUsers() throws SQLException, ClassNotFoundException {
        String selectRequest = "SELECT * FROM Tours";
        Connection connection = null;
        ConnectionPool connectionPool = new ConnectionPool();
        ObservableList<TourEntity> res = FXCollections.observableArrayList();
        Statement statement = connectionPool.getConnection().createStatement();
        ResultSet set = statement.executeQuery(selectRequest);

        String name;
        int id, price, duration;
        while (set.next()) {
            id = set.getInt("id_tour");
            name = set.getString("name");
            price = set.getInt("price");
            duration = set.getInt("duration");
            res.add(new TourEntity(id, name, price, duration));
        }
        return res;
    }
}
