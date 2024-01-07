/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.yourminifactory;

import Clases.Conexion;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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

    private Conexion conexion ;
    private Connection conn;
    @FXML
    private ImageView logo;
    @FXML
    private VBox contenido_page;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
        conn = conexion.connect();
        
    }    

    @FXML
    private void home(MouseEvent event) {
        List<List<String>> resultados = conexion.query(conn, "SELECT * FROM tribu");
        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        // Suponiendo que tienes los datos de las tribus en tribeData
        for (int i = 0; i < resultados.size(); i++) {
            List<String> tribe = resultados.get(i);
            VBox card = createTribeCard(tribe, i + 1);

            // Agregar la tarjeta al GridPane
            grid.add(card, i % 4, i / 4); // Esto organizará las tarjetas en filas de 4
        }
        contenido_page.getChildren().add(grid);  
    }
    private VBox createTribeCard(List<String> tribe, int index) {
        // Cargar la imagen
        ImageView imageView = new ImageView(new Image(getImagePath(index)));
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        // Crear etiquetas para el nombre y los miembros de la tribu
        Label nameLabel = new Label(tribe.get(0) + " - " + tribe.get(2));
        Label membersLabel = new Label("Members: " + tribe.get(3));

        // Organizar la imagen y las etiquetas en un VBox
        VBox card = new VBox(10, imageView, nameLabel, membersLabel);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: black;");

        return card;
    }
    
    private String getImagePath(int index) {
        // Cambia esto por la ruta donde tienes las imágenes de las tribus
        return "/Images/Images_tribes/" + index + ".png";
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
