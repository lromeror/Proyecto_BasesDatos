/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.yourminifactory;

import Clases.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jonanyu
 */
public class LoginController implements Initializable {

    @FXML
    private ImageView imageLogo;
    @FXML
    private TextField labelUsername;
    @FXML
    private PasswordField labelPasswor;

    private Conexion co = new Conexion();
    private List<List<String>> listUsuarios = co.query(co.connect(), "Select * from usuario");
    private List<List<String>> listAdmin = co.query(co.connect(), "Select * from administrador");
    @FXML
    private Label msgW;
    private int id_user;
    private String nameUser;
    @FXML
    private AnchorPane page_login;
    private String typeUser;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnJoin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageLogo.setImage(new Image("Images/images_login/logo2.png"));
        btnLogin.setStyle("-fx-background-color: #55C959; " + "-fx-text-fill: white; "
                +  "-fx-font-weight: bold; " + // Negrita
                "-fx-border-color: transparent; " + "-fx-border-radius: 5; " + "-fx-background-radius: 5; " + "-fx-font-size: 12px;" + "-fx-padding: 3 16;");
        btnLogin.setOnMousePressed(e -> btnLogin.setStyle("-fx-background-color: #367C39; " + "-fx-text-fill: white; "
                +  "-fx-font-weight: bold; " + // Negrita
                "-fx-border-color: transparent; " + "-fx-border-radius: 5;" + "-fx-background-radius: 5; " + "-fx-font-size: 12px;" + "-fx-padding: 3 16;"));
        btnLogin.setOnMouseReleased(e -> btnLogin.setStyle("-fx-background-color: #55C959; " + "-fx-text-fill: white; "
                + "-fx-font-weight: bold; " + // Negrita
                "-fx-border-color: transparent; " + "-fx-border-radius: 5; " + "-fx-background-radius: 5; " + "-fx-font-size: 12px;" + "-fx-padding: 3 16;"));
        
        
        btnJoin.setStyle("-fx-background-color: #55C959; " + "-fx-text-fill: white; "
                +  "-fx-font-weight: bold; " + // Negrita
                "-fx-border-color: transparent; " + "-fx-border-radius: 5; " + "-fx-background-radius: 5; " + "-fx-font-size: 12px;" + "-fx-padding: 3 10;");
        btnJoin.setOnMousePressed(e -> btnLogin.setStyle("-fx-background-color: #367C39; " + "-fx-text-fill: white; "
                +  "-fx-font-weight: bold; " + // Negrita
                "-fx-border-color: transparent; " + "-fx-border-radius: 5;" + "-fx-background-radius: 5; " + "-fx-font-size: 12px;" + "-fx-padding: 3 10;"));
        btnJoin.setOnMouseReleased(e -> btnLogin.setStyle("-fx-background-color: #55C959; " + "-fx-text-fill: white; "
                + "-fx-font-weight: bold; " + // Negrita
                "-fx-border-color: transparent; " + "-fx-border-radius: 5; " + "-fx-background-radius: 5; " + "-fx-font-size: 12px;" + "-fx-padding: 3 10;"));
}

    @FXML
    private void btnLogin(MouseEvent event) {
        String email = labelUsername.getText();
        String passW = labelPasswor.getText();
        if (validarRegistro(email, passW, this.listUsuarios)) {
            labelUsername.setText("");
            labelPasswor.setText("");
            id_user = getUser(email, passW, this.listUsuarios);
            typeUser="User";
            changeInterfaz(id_user);

        } else if (validarRegistro(email, passW, this.listAdmin)) {
            this.typeUser="Admin";
            labelUsername.setText("");
            labelPasswor.setText("");
            id_user = getUser(email, passW, this.listAdmin);
            changeInterfaz(id_user);
        } else {
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("LOGIN");
            alerta.setHeaderText("Usuario o Password Incorrect");
            alerta.setContentText("¡Intente Nuevamente!");
            alerta.show();
        }
    }

    private void changeInterfaz(int idUser) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Page.fxml"));
            Parent root = loader.load();
            PageController controller = loader.getController();
            controller.setId_User(idUser, this.nameUser,this.typeUser);
            controller.id_user = idUser; // se guarda el ide para luego tenerlo al iniciar sesion en page
            Scene scene = new Scene(root);
            Stage stage = (Stage) imageLogo.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnJoin(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("join.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) page_login.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean validarRegistro(String emil, String pass, List<List<String>> listUsers) {
        for (List<String> lineUser : listUsers) {
            String mailUser = lineUser.get(4);
            String pasUser = lineUser.get(3);
            if (mailUser.equals(emil) && pass.equals(pasUser)) {
                this.id_user = Integer.parseInt(lineUser.get(0));//guardo el ide del usuario
                this.nameUser = lineUser.get(1);
                return true;
            }
        }
        return false;
    }

    private static int getUser(String emil, String pass, List<List<String>> listUsers) {
        for (List<String> lineUser : listUsers) {
            String mailUser = lineUser.get(4);
            String pasUser = lineUser.get(3);
            if (mailUser.equals(emil) && pass.equals(pasUser)) {
                return Integer.parseInt(lineUser.get(0));
            }
        }
        return 0;
    }
}
