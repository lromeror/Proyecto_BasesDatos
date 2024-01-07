/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.yourminifactory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imageLogo.setImage(new Image("Images/images_login/logo2.png"));
    }    

    @FXML
    private void btnLogin(MouseEvent event) {
    }

    @FXML
    private void btnJoin(MouseEvent event) {
    }
    
}
