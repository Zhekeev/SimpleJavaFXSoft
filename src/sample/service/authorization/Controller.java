package sample.service.authorization;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.connectionPool.ConnectionPool;
import sample.entity.EmployeeEntity;
import sample.Shake;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField setLogin;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField setPassword;

    @FXML
    void initialize() {
        loginButton.setOnAction(event -> {
            String loginText = setLogin.getText().trim();
            String passwordText = setPassword.getText().trim();
            if(!loginText.equals("")&&!passwordText.equals("")){
                try {
                    loginEmployee(loginText,passwordText);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                Shake shake = new Shake(setLogin);
                Shake shake1 = new Shake(setPassword);
                shake.playAnim();
                shake1.playAnim();
            }
        });
    }
    private void loginEmployee(String login, String password) throws SQLException {
        Controller singUpController = new Controller();
        EmployeeEntity employee = new EmployeeEntity();
        employee.setLogin(login);
        employee.setPassword(password);
        ResultSet resultSet = singUpController.getEmployee(employee);
        int count = 0;
        while (resultSet.next()){
            count++;
        }
        if(count>=1){
            loginButton.setOnAction(event -> {
                loginButton.getScene().getWindow().hide();
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
        } else {
            Shake shake = new Shake(setLogin);
            Shake shake1 = new Shake(setPassword);
            shake.playAnim();
            shake1.playAnim();
        }
    }

    private ResultSet getEmployee(EmployeeEntity employeeEntity) throws SQLException {
        ConnectionPool connectionPool = new ConnectionPool();
        ResultSet resultSet = null;
        String select = "SELECT * FROM Employee WHERE login =? AND password =?";
        try {
            PreparedStatement preparedStatement = connectionPool.getConnection().prepareStatement(select);
            preparedStatement.setString(1, employeeEntity.getLogin());
            preparedStatement.setString(2, employeeEntity.getPassword());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
