package sample.service.contract;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.connectionPool.ConnectionPool;
import sample.entity.HPC;

public class FirstHPC {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private TableView<HPC> table;

    @FXML
    private TableColumn<String, HPC> name;

    @FXML
    private TableColumn<String, HPC> lastName;

    @FXML
    private TableColumn<Integer, HPC> idContract;
    @FXML
    private Button updateButton;

    @FXML
    private TextField setEmpId;

    @FXML

    void initialize() throws SQLException, ClassNotFoundException {
        exitButton.setOnAction(event -> {
            exitButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/service/contract/hpc.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });
        updateButton.setOnAction(event -> {
            updateButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/service/contract/firsthpc.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });
        table.setItems(getContract());
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        idContract.setCellValueFactory(new PropertyValueFactory<>("idContract"));
    }
    public ObservableList<HPC> getContract() throws SQLException, ClassNotFoundException {
        ConnectionPool connectionPool = new ConnectionPool();
        String getId = setEmpId.getText();
        int id = Integer.parseInt(getId);
        String selectRequest = "EXEC Contract_employee " + id;
        ObservableList<HPC> res = FXCollections.observableArrayList();
        Statement statement = connectionPool.getConnection().createStatement();
        ResultSet set = statement.executeQuery(selectRequest);
        String name, lastName;
        int idContract;
        while (set.next()) {
            name = set.getString("first_name");
            lastName = set.getString("last_name");
            idContract = set.getInt("Contract_number");
            res.add(new HPC(name , lastName, idContract));
        }
        return res;
    }

}
