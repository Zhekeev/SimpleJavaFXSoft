package sample.service.client;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.entity.ClientEntity;
import sample.connectionPool.ConnectionPool;

public class ClientAdd {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField setName;

    @FXML
    private Button addButton;

    @FXML
    private TextField setLastName;

    @FXML
    private TextField setAddress;

    @FXML
    private TextField setPhoneNumber;

    @FXML
    private TextField setINN;

    @FXML
    private TextField setDateOfINN;

    @FXML
    private RadioButton maleRB;

    @FXML
    private RadioButton femaleRB;

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
                root = FXMLLoader.load(getClass().getResource("/sample/service/client/client.fxml"));
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
        String firstName = setName.getText();
        String lastName = setLastName.getText();
        String address = setAddress.getText();
        String phoneNumber = setPhoneNumber.getText();
        String gender = "";
        String IIN =setINN.getText();
        String date_of_IIN = setDateOfINN.getText();
        if (maleRB.isSelected()) {
            gender = "Муж";
        } else {
            gender = "Жен";
        }
        ClientEntity client = new ClientEntity(firstName, lastName, address, phoneNumber, gender, IIN, date_of_IIN);
        ClientAdd register = new ClientAdd();
        register.addUser(client);
    }
    public void registerUpdateClient() throws SQLException, ClassNotFoundException {
        String firstName = setName.getText();
        String lastName = setLastName.getText();
        String address = setAddress.getText();
        String phoneNumber = setPhoneNumber.getText();
        String gender = "";
        String IIN =setINN.getText();
        String date_of_IIN = setDateOfINN.getText();
        if (maleRB.isSelected()) {
            gender = "Муж";
        } else {
            gender = "Жен";
        }
        ClientEntity client = new ClientEntity(firstName, lastName, address, phoneNumber, gender, IIN, date_of_IIN);
        ClientAdd register = new ClientAdd();
        register.updateUser(client,IIN);
    }

    private void addUser(ClientEntity client) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "insert into client (first_name, last_name,address, phone_number,gender, IIN,date_of_IIN) values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setString(1, client.getFirstName());
        preparedStatement.setString(2, client.getLastName());
        preparedStatement.setString(3, client.getAddress());
        preparedStatement.setString(4, client.getPhoneNumber());
        preparedStatement.setString(5, client.getGender());
        preparedStatement.setString(6, client.getIIN());
        preparedStatement.setString(7, client.getDateOfIIN());
        preparedStatement.executeUpdate();
    }

    private void updateUser(ClientEntity client, String IIN) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "update client set first_name = ?, last_name = ?,address = ?, phone_number = ?,gender = ?, IIN = ?,date_of_IIN = ? where IIN = ?";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setString(1, client.getFirstName());
        preparedStatement.setString(2, client.getLastName());
        preparedStatement.setString(3, client.getAddress());
        preparedStatement.setString(4, client.getPhoneNumber());
        preparedStatement.setString(5, client.getGender());
        preparedStatement.setString(6, client.getIIN());
        preparedStatement.setString(7, client.getDateOfIIN());
        preparedStatement.setString(8,IIN);
        preparedStatement.executeUpdate();
    }
}
