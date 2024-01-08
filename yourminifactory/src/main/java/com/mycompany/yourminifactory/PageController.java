/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.yourminifactory;

import Clases.Conexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    private Conexion conexion;
    private Connection conn;
    @FXML
    private ImageView logo;
    @FXML
    private VBox contenido_page;
    private int id_user;
    private String nameUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
        conn = conexion.connect();

    }

    public void setId_User(int id, String nameUser) {
        this.id_user = id;
        this.nameUser = nameUser;
    }

    @FXML
    private void home(MouseEvent event) {
    }

    private VBox createTribeCard(List<String> tribe, int index) {
        ImageView imageView = new ImageView(new Image(getImagePath(index)));
        imageView.setFitWidth(200);
        imageView.setFitHeight(150);

        Text nameLabel = new Text(tribe.get(0) + " - " + tribe.get(2));
        Text membersLabel = new Text("Members: " + tribe.get(3));
        nameLabel.setFont(Font.font("Arial", 16));
        membersLabel.setFont(Font.font("Arial", 16));

        VBox card = new VBox(10, imageView, nameLabel, membersLabel);

        card.setPrefSize(225, 231);
        card.setPadding(new Insets(10));
        card.getStyleClass().add("card");

        return card;
    }

    private String getImagePath(int index) {
        return "/Images/Images_tribes/" + index + ".png";
    }

    @FXML
    private void showTribesContent(MouseEvent event) {
        this.contenido_page.getChildren().clear();
        List<List<String>> resultados = conexion.query(conn, "SELECT * FROM tribu");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(40);
        grid.setHgap(40);

        // Suponiendo que tienes los datos de las tribus en tribeData
        for (int i = 0; i < resultados.size(); i++) {
            List<String> tribe = resultados.get(i);
            VBox card = createTribeCard(tribe, i + 1);
            card.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
                @Override
                public void handle(Event event) {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) contenido_page.getScene().getWindow();
                        stage.setScene(scene);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            // Agregar la tarjeta al GridPane
            grid.add(card, i % 4, i / 4); // Esto organizará las tarjetas en filas de 4
        }
        Platform.runLater(() -> {
            contenido_page.getChildren().add(grid);
        });
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
        this.contenido_page.getChildren().clear();
        this.contenido_page.setAlignment(Pos.TOP_CENTER);
        ImageView photo = new ImageView(new Image("Images/img_Picture/foto.png"));
        photo.setFitWidth(1074);
        photo.setFitHeight(400);
        //photo.setPreserveRatio(true);
        StackPane stackPane = new StackPane(photo);
        stackPane.setStyle(        
                "-fx-border-color: #BCBCBC  ; "  
                 + "-fx-border-width: 7px; " 
                 + "-fx-border-radius: 7px; "  
                 + "-fx-background-radius: 7px; "
                ); 
        Text name = new Text("\n"+"Name: " + this.nameUser + "\n");
        name.setFont(Font.font("null", FontWeight.BOLD, 19));
        Button bt = new Button("Modelo");
        bt.setStyle("-fx-background-color: #66D0A8; " 
                + "-fx-text-fill: white; " 
                + "-fx-font-weight: bold; " 
                + "-fx-border-color: #FFFFFF; " 
                + "-fx-border-width: 3px; "
                + "-fx-border-radius: 4px; " 
                + "-fx-background-radius: 6px; "
                + "-fx-padding: 12px;" + "-fx-font-size: 15px;");
        Button bt2 = new Button("Company");
        bt2.setStyle("-fx-background-color: #F36230; "
                + "-fx-text-fill: white; " 
                + "-fx-font-weight: bold; " 
                + "-fx-border-color: #FFFFFF; "
                + "-fx-border-width: 3px; " 
                + "-fx-border-radius: 4px; " 
                + "-fx-background-radius: 6px; " 
                + "-fx-padding: 12px;" + "-fx-font-size: 15px;");
        Text espacio = new Text("      ");
        HBox buts = new HBox(bt, espacio, bt2);
        buts.setAlignment(Pos.CENTER);
        VBox vConten = new VBox();
        vConten.setAlignment(Pos.CENTER);
        bt.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
            vConten.getChildren().clear();
            Text tituloList = new Text("\n" + "Bookstore List" + "\n");
            tituloList.setFont(Font.font("null", FontWeight.BOLD, 19));
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setVgap(35);
            grid.setHgap(35);
            for (int i = 0; i < findLibreria().size(); i++) {
                VBox tribe = findLibreria().get(i);
                grid.add(tribe, i % 4, i / 4);
            }
            vConten.getChildren().addAll(tituloList, grid);
        });
        List<List<String>> lisLib = conexion.query(conn, "select * from campana;");
        bt2.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
            vConten.getChildren().clear();
            Text tituloList = new Text("\n" + "Campaign List" + "\n");
            tituloList.setFont(Font.font("null", FontWeight.BOLD, 19));
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setVgap(27);
            grid.setHgap(27);
            for (int i = 0; i < showCampana(lisLib).size(); i++) {
                VBox tribe = showCampana(lisLib).get(i);
                grid.add(tribe, i % 4, i / 4);
            }
            vConten.getChildren().addAll(tituloList, grid);
        });

        this.contenido_page.getChildren().add(stackPane);
        this.contenido_page.getChildren().addAll(name);

        this.contenido_page.getChildren().addAll(buts, vConten);
    }

    private List<VBox> findLibreria() {
        List<VBox> lisFil = new ArrayList<>();
        List<List<String>> lisLib = conexion.query(conn, "select * from libreria;");
        for (List<String> infoLib : lisLib) {
            if (Integer.parseInt(infoLib.get(2)) == this.id_user) {
                List<List<String>> lisMode = conexion.query(conn, "select * from modelo;");
                VBox table = findModel(Integer.parseInt(infoLib.get(0)), lisMode, infoLib.get(1));
                lisFil.add(table);
            }
        }
        return lisFil;
    }

    private VBox findModel(int id_libreia, List<List<String>> listModelos, String tituloLib) {
        VBox v1 = new VBox();
        Text tituloL = new Text("Libreria: \n" + tituloLib);
        tituloL.setFill(Color.WHITE);
        tituloL.setFont(Font.font("null", FontWeight.BOLD, 12));
        tituloL.setUnderline(true);
        v1.getChildren().add(tituloL);
        for (List<String> infoMode : listModelos) {
            if (Integer.parseInt(infoMode.get(6)) == id_libreia) {
                HBox filaInfo = new HBox();
                Text titulo = new Text(infoMode.get(3) + ":  ");
                Text price = new Text(infoMode.get(2) + "$");
                filaInfo.getChildren().addAll(titulo, price);
                v1.setStyle("-fx-background-color: #B0BEC5; "
                        + "-fx-border-color: black; "
                        + "-fx-border-radius: 10; "
                        + "-fx-background-radius: 10; "
                        + "-fx-padding: 10;");
                v1.getChildren().add(filaInfo);
            }
        }
        return v1;
    }

    private List<VBox> showCampana(List<List<String>> lisLib) {
        List<VBox> listVbox = new ArrayList<>();
        for (List<String> lis : lisLib) {
            VBox v1 = new VBox();
            Text ti = new Text(lis.get(1) + "\n");
            ti.setFill(Color.WHITE);
            ti.setFont(Font.font("null", FontWeight.BOLD, 12));
            HBox contenido = new HBox();
            VBox pio = new VBox();
            Text tiPio = new Text("Numbers Pioners:");
            Text cantPio = new Text(lis.get(2));
            pio.getChildren().addAll(tiPio, cantPio);
            VBox money = new VBox();
            Text espacio = new Text("   ");
            Text tiDi = new Text("Raised money:");
            Text cantMo = new Text(lis.get(3) + " $");
            money.getChildren().addAll(tiDi, cantMo);
            pio.setStyle("-fx-border-color: #FFFFFF; " + "-fx-padding: 5;" + "-fx-border-radius: 6; ");
            money.setStyle("-fx-border-color: #FFFFFF; " + "-fx-padding: 5;" + "-fx-border-radius: 6; ");
            contenido.getChildren().addAll(pio, espacio, money);
            v1.getChildren().addAll(ti, contenido);
            v1.setStyle("-fx-background-color: #B0BEC5; "
                    + "-fx-border-color: black; "
                    + "-fx-border-radius: 10; "
                    + "-fx-background-radius: 10; "
                    + "-fx-padding: 10;");
            listVbox.add(v1);
        }
        return listVbox;
    }

}
