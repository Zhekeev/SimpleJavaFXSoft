package sample.service.contract;

import java.io.IOException;
import java.net.URL;
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

public class HPC {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitButton;

    @FXML
    private Button openButton;

    @FXML
    private RadioButton emplRB;

    @FXML
    private RadioButton ContractRB;

    @FXML
    private TextField setEmployeeID;

    @FXML
    private TextField setContractID;

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
    void initialize() {
       exitButton.setOnAction(event -> {
           exitButton.getScene().getWindow().hide();
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
       if(emplRB.isSelected()){
           openButton.setOnAction(event -> {
               openButton.getScene().getWindow().hide();
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
       }if(ContractRB.isSelected()){
            openButton.setOnAction(event -> {
                openButton.getScene().getWindow().hide();
                String getID = setContractID.getText();
                int id = Integer.parseInt(getID);
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/sample/service/contract/secondhpc.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            });
        }
    }
}
