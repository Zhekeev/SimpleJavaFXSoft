package sample.service.countrytour;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.connectionPool.ConnectionPool;
import sample.entity.CountryTourEntity;

public class CountryTour {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button countryAddButton;

    @FXML
    private TextField setTourtIdDelete;

    @FXML
    private Button countryDeleteButton;

    @FXML
    private TextField setTourIdADD;

    @FXML
    private TableView<CountryTourEntity> table;

    @FXML
    private TableColumn<CountryTourEntity, Integer> tourId;

    @FXML
    private TableColumn<CountryTourEntity, Integer> countryId;

    @FXML
    private Button countryUpdateButton;

    @FXML
    private TextField setCountryIdADD;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        exitButton.setOnAction(event -> {
            exitButton.getScene().getWindow().hide();
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
        countryAddButton.setOnAction(event -> {
            try {
                registerCountry();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        countryUpdateButton.setOnAction(event -> {
            try {
                registerUpdateCountry();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        countryDeleteButton.setOnAction(event -> {
            String getId = setTourtIdDelete.getText();
            int id = Integer.parseInt(getId);
            deleteCountry(id);
        });

        table.setItems(getCountry());
        tourId.setCellValueFactory(new PropertyValueFactory<>("idTour"));
        countryId.setCellValueFactory(new PropertyValueFactory<>("idCountry"));
    }

    private void deleteCountry(int id) {
        ConnectionPool connectionPool = new ConnectionPool();
        String remove = "delete  from  CountryTours where id_tour=?";
        try {
            PreparedStatement newData = connectionPool.getConnection().prepareStatement(remove);
            newData.setInt(1, id);
            newData.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void registerCountry() throws SQLException, ClassNotFoundException {
        String getContractId = setTourIdADD.getText();
        int tourId = Integer.parseInt(getContractId);
        String getServiceId = setCountryIdADD.getText();
        int countryId = Integer.parseInt(getServiceId);
        CountryTourEntity country = new CountryTourEntity(tourId,countryId);
        CountryTour register = new CountryTour();
        register.addServiceContract(country);
    }

    private void addServiceContract(CountryTourEntity country) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "insert into CountryTours (id_tour, id_country) values (?,?)";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setInt(1, country.getIdTour());
        preparedStatement.setInt(2,country.getIdCountry());
        preparedStatement.executeUpdate();
    }

    public void registerUpdateCountry() throws SQLException, ClassNotFoundException {
        String getContractId = setTourIdADD.getText();
        int tourId = Integer.parseInt(getContractId);
        String getServiceId = setCountryIdADD.getText();
        int countryId = Integer.parseInt(getServiceId);
        CountryTourEntity country = new CountryTourEntity(tourId,countryId);
        CountryTour register = new CountryTour();
        register.updateCountry(country,tourId);
    }

    private void updateCountry(CountryTourEntity country, int id) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "update CountryTours set id_tour,id_country where id_tour = ?";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setInt(1,country.getIdTour());
        preparedStatement.setInt(2,country.getIdCountry());
        preparedStatement.setInt(3,id);
        preparedStatement.executeUpdate();
    }
    public ObservableList<CountryTourEntity> getCountry() throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String selectRequest = "select * from CountryTours";
        ObservableList<CountryTourEntity> res = FXCollections.observableArrayList();
        Statement statement = connectionPool.getConnection().createStatement();
        ResultSet set = statement.executeQuery(selectRequest);

        int tourId, countryId;
        while (set.next()) {
            tourId = set.getInt("id_tour");
            countryId = set.getInt("id_country");
            res.add(new CountryTourEntity(tourId,countryId));
        }
        return res;
    }
}
