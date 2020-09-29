package sample.service.contract;

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
import sample.entity.ContractEntity;

public class ContractAdd {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField setClientId;

    @FXML
    private Button addButton;

    @FXML
    private TextField setEmployeeId;

    @FXML
    private TextField setTourId;

    @FXML
    private TextField setDateOfStart;

    @FXML
    private TextField setDateOfFinish;

    @FXML
    private TextField deleteIdContract;

    @FXML
    private Button deleteButton;

    @FXML
    private Button backButton;

    @FXML
    private Button updateButton;

    @FXML
    void initialize() {
       backButton.setOnAction(event -> {
           backButton.getScene().getWindow().hide();
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

       addButton.setOnAction(event -> {
           try {
               registerContract();
           } catch (SQLException e) {
               e.printStackTrace();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
       });

       deleteButton.setOnAction(event -> {
           String getId = deleteIdContract.getText();
           int id = Integer.parseInt(getId);
           deleteContract(id);
       });
       updateButton.setOnAction(event -> {
           try {
               registerUpdateContract();
           } catch (SQLException e) {
               e.printStackTrace();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
       });
    }

    public void registerContract() throws SQLException, ClassNotFoundException {
        String getEmployeeID = setClientId.getText();
        int employeeID = Integer.parseInt(getEmployeeID);
        String getClientID = setEmployeeId.getText();
        int clientID = Integer.parseInt(getClientID);
        String getTourID = setTourId.getText();
        int tourID = Integer.parseInt(getTourID);
        String tourStartDate = setDateOfStart.getText();
        String tourFinishDate = setDateOfFinish.getText();

        ContractEntity contractEntity = new ContractEntity(clientID,employeeID,tourID,tourStartDate,tourFinishDate);
        ContractAdd register = new ContractAdd();
        register.addContract(contractEntity);
    }

    private void addContract(ContractEntity contractEntity) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "insert into Contract (id_client,id_employee,id_tour, tour_start_date,tour_finish_date) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setInt(1,contractEntity.getIdClient());
        preparedStatement.setInt(2, contractEntity.getIdEmployee());
        preparedStatement.setInt(3,contractEntity.getIdTour());
        preparedStatement.setString(4,contractEntity.getTourStartDate());
        preparedStatement.setString(5,contractEntity.getTourFinishDate());
        preparedStatement.executeUpdate();
    }

    public void registerUpdateContract() throws SQLException, ClassNotFoundException {
        String getEmployeeID = setClientId.getText();
        int employeeID = Integer.parseInt(getEmployeeID);
        String getClientID = setEmployeeId.getText();
        int clientID = Integer.parseInt(getClientID);
        String getTourID = setTourId.getText();
        int tourID = Integer.parseInt(getTourID);
        String tourStartDate = setDateOfStart.getText();
        String tourFinishDate = setDateOfFinish.getText();

        ContractEntity contractEntity = new ContractEntity(clientID,employeeID,tourID,tourStartDate,tourFinishDate);
        ContractAdd register = new ContractAdd();
        register.updateContract(contractEntity,clientID);
    }

    private void updateContract(ContractEntity contractEntity, int id) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "update Contract set id_client = ?,id_employee = ?,id_tour = ?, tour_start_date = ?,tour_finish_date = ? where id_client = ?";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setInt(1,contractEntity.getIdClient());
        preparedStatement.setInt(2, contractEntity.getIdEmployee());
        preparedStatement.setInt(3,contractEntity.getIdTour());
        preparedStatement.setString(4,contractEntity.getTourStartDate());
        preparedStatement.setString(5,contractEntity.getTourFinishDate());
        preparedStatement.setInt(6,id);
        preparedStatement.executeUpdate();
    }
    private void deleteContract(int id) {
        ConnectionPool connectionPool = new ConnectionPool();
        String remove = "delete  from  Contract where id_contract=?";
        try {
            PreparedStatement newData = connectionPool.getConnection().prepareStatement(remove);
            newData.setInt(1, id);
            newData.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
