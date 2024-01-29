/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.yourminifactory;

import Clases.Conexion;
import Clases.Model;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class SolicitudController implements Initializable {

    @FXML
    private ImageView logo;
    
    public Conexion conexion;
    public Connection conn;
    private int id_campana ; 
    private int id_user;
    @FXML
    private VBox container_nivel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
        conn = conexion.connect();
        id_campana = PageController.id_Campana;
        id_user = PageController.id_user;
        createTiers();

    }    

    @FXML
    private void home(MouseEvent event) {
        
    }
    private void createTiers(){
        container_nivel.getChildren().clear();
        String querie = "SELECT *, (SELECT Count(a.cant) FROM asignacion a WHERE a.id_campana = " + id_campana + " AND a.id_tier = t.id_tier) AS total_producto " +
"FROM tier t WHERE t.id_tier IN (SELECT DISTINCT id_tier FROM campana c WHERE c.id_campana = " + id_campana + ") ORDER BY nivelApoyo;";
        List<List<String>> query = conexion.query(conn, querie);
        for(List<String> tier : query){
            HBox containerprincipal = new HBox();
            Pane createNivelContainer = createNivelContainer(tier,conexion,conn);
            containerprincipal.setPrefSize(520, 218);
            containerprincipal.setAlignment(Pos.CENTER);
            containerprincipal.getChildren().add(createNivelContainer);
            container_nivel.getChildren().add(containerprincipal);
        }
    }
    
    private VBox createNivelContainer(List<String> tier, Conexion conexion, Connection conn) {
        String id_tier = tier.get(0);
        String precio = tier.get(1);
        String nivel = tier.get(2);
        String descuento = tier.get(3);
        
        VBox container = new VBox();  // Cambiado de Pane a VBox
        container.setPrefSize(520, 218);
        container.setSpacing(10);  // Espaciado entre elementos
        

        Text textNivel = new Text("NIVEL " + nivel);
        textNivel.setFont(new Font(25));
        TextFlow textFlowNivel = new TextFlow(textNivel);
        textFlowNivel.setTextAlignment(TextAlignment.CENTER);
        
        Text textBeneficios = new Text("BENEFICIOS");
        textBeneficios.setFont(new Font(24));
        
        ScrollPane scrollPaneModelos = new ScrollPane();
        
        scrollPaneModelos.setMaxWidth(230);
        scrollPaneModelos.setMinHeight(200);
        scrollPaneModelos.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneModelos.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    
    
        VBox scrollContent = new VBox();
        scrollContent.setAlignment(Pos.CENTER);
        scrollContent.setMaxWidth(230);
        scrollPaneModelos.setMinHeight(200);
        scrollPaneModelos.setContent(scrollContent);
        
        String modelos_tier = "SELECT * FROM Modelo m WHERE m.id_modelo IN (SELECT a.id_modelo FROM asignacion a WHERE a.id_tier = " + id_tier + " AND a.id_campana = " + id_campana + ");";
        List<List<String>> modelos_tier_l = conexion.query(conn, modelos_tier);
        for(List<String> modelo : modelos_tier_l){
            VBox createModelCardTienda = createModelCardTienda(modelo);
            scrollContent.getChildren().add(createModelCardTienda);
        }
        
         Text textDescuento = new Text(descuento + "% Descuento");
        textDescuento.setFont(new Font(19));
     
        Text textPrecio = new Text("PRECIO");
        textPrecio.setFont(new Font(24));
     
        Text textValor = new Text("$" + precio);
        textValor.setFont(new Font(22));
        
        Button buttonUnirse = new Button("Unirse");
        buttonUnirse.getStyleClass().add("buttonApoyar");
        buttonUnirse.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
                @Override
                public void handle(Event event) {

                    conexion.insertarDatoApoya(conn,id_user , id_campana,Integer.parseInt(nivel));
                    Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stageActual.close();

                }
            });
        container.getChildren().addAll(textFlowNivel, textBeneficios, scrollPaneModelos, textDescuento, textPrecio, textValor, buttonUnirse);
        
        return container;
    }


    private VBox createModelCardTienda(List<String> model) {
        int modId = Integer.parseInt(model.get(0));
        double pr = Double.parseDouble(model.get(2));
        int libId = Integer.parseInt(model.get(6));
        int vis = Integer.parseInt(model.get(7));
        String date = model.get(5);
        Model m = null;
        if (modId <= 20) {
            m = new Model(modId, model.get(1), pr, model.get(3), date, libId, vis);
        } else {
            m = new Model(modId, model.get(1), pr, model.get(3), date, libId, vis, model.get(4));
        }

        ImageView imageView = new ImageView(new Image(m.getImage()));
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        Text nameLabel = new Text(m.getTitle());
        Text priceLabel = new Text("Price Original : " + pr);
        priceLabel.setFont(Font.font("Arial", 12));
        nameLabel.setFont(Font.font("Arial", 12));
        VBox card = new VBox(10, imageView, nameLabel, priceLabel);

        card.setPrefSize(200, 160);
        card.getStyleClass().add("card");

        return card;

    }
}
