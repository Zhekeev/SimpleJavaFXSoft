package sample.service.tour;

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
import sample.entity.TourEntity;

public class TourAdd {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField setName;

    @FXML
    private Button addButton;

    @FXML
    private TextField setPrice;

    @FXML
    private TextField setDuration;

    @FXML
    private TextField setIDForUPDT;

    @FXML
    private Button updateButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            try {
                registerClient();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
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
        updateButton.setOnAction(event -> {
            try {
                registerUpdateClient();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
    public void registerClient() throws SQLException, ClassNotFoundException {
        String name = setName.getText();
        String getPrice = setPrice.getText();
        int price = Integer.parseInt(getPrice);
        String getDuration = setDuration.getText();
        int duration = Integer.parseInt(getDuration);

        TourEntity client = new TourEntity(name, price, duration);
        TourAdd register = new TourAdd();
        register.addTour(client);
    }

    private void addTour(TourEntity client) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "insert into Tours (name, price,duration) values (?,?,?)";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setString(1, client.getName());
        preparedStatement.setInt(2,client.getPrice());
        preparedStatement.setInt(3,client.getDuration());
        preparedStatement.executeUpdate();
    }

    public void registerUpdateClient() throws SQLException, ClassNotFoundException {
        String name = setName.getText();
        String getPrice = setPrice.getText();
        int price = Integer.parseInt(getPrice);
        String getDuration = setDuration.getText();
        int duration = Integer.parseInt(getDuration);
        String getId = setIDForUPDT.getText();
        int id = Integer.parseInt(getId);
        TourEntity client = new TourEntity(name, price, duration);
        TourAdd register = new TourAdd();
        register.updateTour(client,id);
    }

    private void updateTour(TourEntity client, int id) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "update Tours set name = ?, price = ?,duration = ? where id_tour = ?";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setString(1, client.getName());
        preparedStatement.setInt(2,client.getPrice());
        preparedStatement.setInt(3,client.getDuration());
        preparedStatement.setInt(4,id);
        preparedStatement.executeUpdate();
    }
}
