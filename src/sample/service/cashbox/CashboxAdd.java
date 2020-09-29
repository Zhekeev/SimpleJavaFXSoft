package sample.service.cashbox;

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
import sample.entity.CashboxEntity;

public class CashboxAdd {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button updateButton;

    @FXML
    private TextField setEmployeeId;

    @FXML
    private Button addButton;

    @FXML
    private TextField setClientId;

    @FXML
    private TextField setTourId;

    @FXML
    private TextField setAmount;

    @FXML
    private TextField setData;

    @FXML
    private TextField deleteIdClient;

    @FXML
    private Button deleteButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
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
        deleteButton.setOnAction(event -> {
            String getId = deleteIdClient.getText();
            int id = Integer.parseInt(getId);
            deleteCashbox(id);
        });
        addButton.setOnAction(event -> {
            try {
                registerAddCashbox();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        updateButton.setOnAction(event -> {
            try {
                registerUpdateCashbox();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
    public void registerAddCashbox() throws SQLException, ClassNotFoundException {
        String getEmployeeID = setEmployeeId.getText();
        int employeeID = Integer.parseInt(getEmployeeID);
        String getClientID = setClientId.getText();
        int clientID = Integer.parseInt(getClientID);
        String getTourID = setTourId.getText();
        int tourID = Integer.parseInt(getTourID);
        String getAmount = setAmount.getText();
        int amount = Integer.parseInt(getAmount);
        String date = setData.getText();

        CashboxEntity cashboxEntity = new CashboxEntity(employeeID,clientID,tourID,amount,date);
        CashboxAdd register = new CashboxAdd();
        register.addCashbox(cashboxEntity);

    }
    public void registerUpdateCashbox() throws SQLException, ClassNotFoundException {
        String getEmployeeID = setEmployeeId.getText();
        int employeeID = Integer.parseInt(getEmployeeID);
        String getClientID = setClientId.getText();
        int clientID = Integer.parseInt(getClientID);
        String getTourID = setTourId.getText();
        int tourID = Integer.parseInt(getTourID);
        String getAmount = setAmount.getText();
        int amount = Integer.parseInt(getAmount);
        String date = setData.getText();

        CashboxEntity cashboxEntity = new CashboxEntity(employeeID,clientID,tourID,amount,date);
        CashboxAdd register = new CashboxAdd();
        register.updateCashbox(cashboxEntity,clientID);
    }
    private void addCashbox(CashboxEntity cashboxEntity) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "insert into Cashbox (id_employee,id_client,id_tour, amount,date) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setInt(1, cashboxEntity.getIdEmployee());
        preparedStatement.setInt(2,cashboxEntity.getIdClient());
        preparedStatement.setInt(3,cashboxEntity.getIdTour());
        preparedStatement.setInt(4,cashboxEntity.getAmount());
        preparedStatement.setString(5,cashboxEntity.getDate());
        preparedStatement.executeUpdate();
    }
    private void updateCashbox(CashboxEntity cashboxEntity, int id) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "update Cashbox set id_employee = ?,id_client = ?,id_tour = ?, amount = ?,date = ? where id_client =?";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setInt(1, cashboxEntity.getIdEmployee());
        preparedStatement.setInt(2,cashboxEntity.getIdClient());
        preparedStatement.setInt(3,cashboxEntity.getIdTour());
        preparedStatement.setInt(4,cashboxEntity.getAmount());
        preparedStatement.setString(5,cashboxEntity.getDate());
        preparedStatement.setInt(6,id);
        preparedStatement.executeUpdate();
    }
    private void deleteCashbox(int id) {
        ConnectionPool connectionPool = new ConnectionPool();
        String remove = "delete  from  Cashbox where id_client=?";
        try {
            PreparedStatement newData = connectionPool.getConnection().prepareStatement(remove);
            newData.setInt(1, id);
            newData.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
