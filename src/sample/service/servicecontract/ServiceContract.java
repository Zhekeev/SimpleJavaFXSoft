package sample.service.servicecontract;

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
import sample.entity.ServiceContractEntity;
import sample.service.country.Country;

public class ServiceContract {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button countryAddButton;

    @FXML
    private TextField setContractIdDelete;

    @FXML
    private Button countryDeleteButton;

    @FXML
    private TextField setServiceIdADD;

    @FXML
    private TableView<ServiceContractEntity> table;

    @FXML
    private TableColumn<ServiceContractEntity, Integer> serviceId;

    @FXML
    private TableColumn<ServiceContractEntity, Integer> contractId;

    @FXML
    private Button countryUpdateButton;

    @FXML
    private TextField setContractIdADD;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
       exitButton.setOnAction(event -> {
           exitButton.getScene().getWindow().hide();
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
           String getId = setContractIdDelete.getText();
           int id = Integer.parseInt(getId);
           deleteCountry(id);
       });

       table.setItems(getCountry());
       contractId.setCellValueFactory(new PropertyValueFactory<>("contractId"));
       serviceId.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
    }
    private void deleteCountry(int id) {
        ConnectionPool connectionPool = new ConnectionPool();
        String remove = "delete  from  ServiceContract where id_contract=?";
        try {
            PreparedStatement newData = connectionPool.getConnection().prepareStatement(remove);
            newData.setInt(1, id);
            newData.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void registerCountry() throws SQLException, ClassNotFoundException {
        String getContractId = setContractIdADD.getText();
        int contractId = Integer.parseInt(getContractId);
        String getServiceId = setServiceIdADD.getText();
        int serviceId = Integer.parseInt(getServiceId);
        ServiceContractEntity country = new ServiceContractEntity(contractId,serviceId);
        ServiceContract register = new ServiceContract();
        register.addServiceContract(country);
    }

    private void addServiceContract(ServiceContractEntity country) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "insert into ServiceContract (id_contract, id_services) values (?,?)";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setInt(1, country.getContractId());
        preparedStatement.setInt(2,country.getServiceId());
        preparedStatement.executeUpdate();
    }

    public void registerUpdateCountry() throws SQLException, ClassNotFoundException {
        String getContractId = setContractIdADD.getText();
        int contractId = Integer.parseInt(getContractId);
        String getServiceId = setServiceIdADD.getText();
        int serviceId = Integer.parseInt(getServiceId);
        ServiceContractEntity country = new ServiceContractEntity(contractId,serviceId);
        ServiceContract register = new ServiceContract();
        register.updateCountry(country,contractId);
    }

    private void updateCountry(ServiceContractEntity country, int id) throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String insert = "update ServiceContract set id_contract,id_service where id_contract = ?";
        PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(insert);
        preparedStatement.setInt(1,country.getContractId());
        preparedStatement.setInt(2,country.getServiceId());
        preparedStatement.setInt(3,id);
        preparedStatement.executeUpdate();
    }
    public ObservableList<ServiceContractEntity> getCountry() throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String selectRequest = "select * from ServiceContract";
        ObservableList<ServiceContractEntity> res = FXCollections.observableArrayList();
        Statement statement = connectionPool.getConnection().createStatement();
        ResultSet set = statement.executeQuery(selectRequest);

        int contractId, serviceId;
        while (set.next()) {
            contractId = set.getInt("id_contract");
            serviceId = set.getInt("id_services");
            res.add(new ServiceContractEntity(contractId,serviceId));
        }
        return res;
    }
}
