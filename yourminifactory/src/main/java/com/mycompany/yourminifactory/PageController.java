/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.yourminifactory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class PageController implements Initializable {

    @FXML
    private Label link_tribes;
    @FXML
    private Label link_tienda;
    @FXML
    private Label link_carrito_compra;
    @FXML
    private SplitMenuButton drowdown;
    @FXML
    private MenuItem modelo_option;
    @FXML
    private Label link_user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void home(MouseEvent event) {
    }

    @FXML
    private void showTribesContent(MouseEvent event) {
    }

    @FXML
    private void showTiendaContent(MouseEvent event) {
    }

    @FXML
    private void show_carrito_compraContent(MouseEvent event) {
    }

    @FXML
    private void modelo_option(ActionEvent event) {
    }

    @FXML
    private void show_usercontent(MouseEvent event) {
    }
    
}
