/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.yourminifactory;

import Clases.Conexion;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jonanyu
 */
public class JoinController implements Initializable {

    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView imgLogo;
    @FXML
    private TextField labelName;
    @FXML
    private TextField labelEmail;
    @FXML
    private PasswordField labelPass;
    @FXML
    private DatePicker labelDateBirth;
    private Conexion co;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imgLogo.setImage(new Image("Images/images_login/logo2.png"));
        img1.setImage(new Image("Images/images_login/1.png"));
        img2.setImage(new Image("Images/images_login/2.png"));
        img3.setImage(new Image("Images/images_login/3.png"));
        img4.setImage(new Image("Images/images_login/4.png"));
        co = new Conexion();
    }

    @FXML
    private void btnCancel(MouseEvent event) {
        labelName.setText("");
        labelEmail.setText("");
        labelPass.setText("");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Page.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) labelEmail.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnJoin(MouseEvent event) {
        String name = labelName.getText();
        String mail = labelEmail.getText();
        String pass = labelPass.getText();
        String date = String.valueOf(labelDateBirth.getValue());

        System.out.println("");
        if (!(name == null || mail == null || pass == null || date == null)) {
            List<List<String>> listUsuarios = co.query(co.connect(), "Select * from usuario");
            if (!validarRegistro(mail, listUsuarios)) {
                co.insertarUsuario(co.connect(), name, date, pass, mail);
                labelName.setText("");
                labelEmail.setText("");
                labelPass.setText("");
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Page.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) labelEmail.getScene().getWindow();
                    stage.setScene(scene);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("JOIN");
                alerta.setHeaderText("User already exist");
                alerta.setContentText("¡Intente Nuevamente!");
                alerta.show();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("JOIN");
            alerta.setHeaderText("Field Empty");
            alerta.setContentText("¡Intente Nuevamente!");
            alerta.show();
        }
    }

    public boolean validarRegistro(String emil, List<List<String>> listUsuarios) {
        for (List<String> lineUser : listUsuarios) {
            String mailUser = lineUser.get(4);
            if (mailUser.equals(emil)) {
                return true;
            }
        }
        return false;
    }
    

}
