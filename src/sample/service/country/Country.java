package sample.service.country;

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
import sample.entity.CountryEntity;

public class Country {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<CountryEntity> table;

    @FXML
    private TableColumn<?, ?> countryID;

    @FXML
    private TableColumn<?, ?> countryName;

    @FXML
    private Button countryAddButton;

    @FXML
    private TextField setIdDelete;

    @FXML
    private Button countryDeleteButton;

    @FXML
    private TextField setNameAdd;

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
    private TextField setIdCountryForUPDT;

    @FXML
    private Button countryUpdateButton;

    @FXML
    private Button countryTourOpenButton;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        countryDeleteButton.setOnAction(event -> {
            String getId = setIdDelete.getText();
            int id = Integer.parseInt(getId);
            deleteCountry(id);
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

        countryTourOpenButton.setOnAction(event -> {
            countryTourOpenButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/service/countrytour/countrytour.fxml"));
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
        countryUpdateButton.setOnAction(event -> {
            try {
                registerUpdateCountry();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        table.setItems(getCountry());
        countryID.setCellValueFactory(new PropertyValueFactory<>("id"));
        countryName.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

    private void deleteCountry(int id) {
        ConnectionPool connectionPool = new ConnectionPool();
        String remove = "delete  from  Country where id_country=?";
        try {
            PreparedStatement newData = connectionPool.getConnection().prepareStatement(remove);
            newData.setInt(1, id);
            newData.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void registerCountry() throws SQLException, ClassNotFoundException {
        String name = setNameAdd.getText();
        CountryEntity country = new CountryEntity(name);
        Country register = new Country();
        register.addCountry(country);
    }

    private void addCountry(CountryEntity country) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "insert into Country (name) values (?)";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setString(1, country.getName());
        preparedStatement.executeUpdate();
    }

    public void registerUpdateCountry() throws SQLException, ClassNotFoundException {
        String name = setNameAdd.getText();
        String getID = setIdCountryForUPDT.getText();
        int id = Integer.parseInt(getID);
        CountryEntity country = new CountryEntity(name);
        Country register = new Country();
        register.updateCountry(country,id);
    }

    private void updateCountry(CountryEntity country, int id) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "update Country set name where id_country = ?";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setString(1, country.getName());
        preparedStatement.setInt(2,id);
        preparedStatement.executeUpdate();
    }
    public ObservableList<CountryEntity> getCountry() throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String selectRequest = "select * from Country";
        ObservableList<CountryEntity> res = FXCollections.observableArrayList();
        Statement statement = connectionPool.getConnection().createStatement();
        ResultSet set = statement.executeQuery(selectRequest);

        String name;
        int id;
        while (set.next()) {
            id = set.getInt("id_country");
            name = set.getString("name");
            res.add(new CountryEntity(id,name));
        }
        return res;
    }
}
