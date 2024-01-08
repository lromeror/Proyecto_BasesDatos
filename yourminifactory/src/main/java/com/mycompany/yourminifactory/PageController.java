/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.yourminifactory;

import Clases.Conexion;
<<<<<<< HEAD
import Clases.Model;
=======
import Clases.Libreria;
import java.io.File;
>>>>>>> 78092f5 (Ya se puede subir modelos)
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.time.LocalDate;
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
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class PageController implements Initializable {

    @FXML
    public Label link_tribes;
    @FXML
    public Label link_tienda;
    @FXML
    public Label link_carrito_compra;
    @FXML
    public SplitMenuButton drowdown;
    @FXML
    public MenuItem modelo_option;
    @FXML
    public Label link_user;

    public Conexion conexion ;
    public Connection conn;
    @FXML
    private ImageView logo;
    @FXML
    private VBox contenido_page;
    
    public int id_user=1;
    public boolean subido = false;
    public String url_model;
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
    
    private VBox createModelCard(List<String> model, int index){
        // int idModel, String description, double price, String title, LocalDate publicationDate, int libraryId
        // [1, Modelo 3D Impresionante, 50, Estatua de Dragón, dragon.stl, 2023-03-01, 1, 1]
        int modId= Integer.parseInt(model.get(0));
        double pr= Double.parseDouble(model.get(2));
        int libId= Integer.parseInt(model.get(6));
        int vis= Integer.parseInt(model.get(7));
        String date= model.get(5);
        
        Model m=new Model(modId, model.get(1), pr , model.get(3), date , libId, vis);
        
        ImageView imageView = new ImageView(new Image(m.getImage()));
        imageView.setFitWidth(200);
        imageView.setFitHeight(150);
        
        Text nameLabel = new Text(m.getIdModel() + " - " + m.getTitle());
        nameLabel.setFont(Font.font("Arial", 16));
        Button deleteButton = new Button("Eliminar");
        deleteButton.setOnAction(event -> {
            // Logic to delete the model goes here.
            // For example, you might call a method that removes the model from the database
            // and then removes the card from the UI.
            // This is a hypothetical method call.
        });
        deleteButton.getStyleClass().add("delete-button");
        
        VBox card = new VBox(10, imageView, nameLabel, deleteButton);
        
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
        contenido_page.getChildren().clear();

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
        Platform.runLater(()->{
            contenido_page.getChildren().clear();
            contenido_page.getChildren().add(grid);  
        });
    }

    @FXML
    private void showTiendaContent(MouseEvent event) {
        
    }

    @FXML
    private void show_carrito_compraContent(MouseEvent event) {
        List<List<String>> resultados = conexion.query(conn, "select *\n" +
        "from modelo \n" +
        "where modelo.id_modelo in (select modelo.id_modelo\n" +
        "from modelo join anadir on modelo.id_modelo=anadir.id_modelo join carro_compra on carro_compra.id_carrito=anadir.id_carrito\n" +
        "where carro_compra.id_usuario="+this.id_user+")");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(40);
        grid.setHgap(40);
        
        for (int i = 0; i < resultados.size(); i++) {
            List<String> model = resultados.get(i);
            System.out.println(model);
            VBox card = createModelCard(model, i + 1);
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

            grid.add(card, i % 4, i / 4); // Esto organizará las tarjetas en filas de 4
        }
        Platform.runLater(()->{
            contenido_page.getChildren().clear();
            contenido_page.getChildren().add(grid);  
        });
    }

    
//    private void uploadFile(){   
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("CHOOSE 3D MODEL");
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("3D Files", "*.stl", "*.obj", "*.fbx", "*.3ds","*.png","*.jpg","*.jpeg"));
//        File selectedFile = fileChooser.showOpenDialog(null);
//        
//        if (selectedFile != null) {
//            String originalFileName = selectedFile.getName();
//            Path sourcePath = selectedFile.toPath();
//            Path targetPath = Paths.get("src/main/resources/Images/Modelos/" + originalFileName);
//            
//            try {
//                Files.createDirectories(targetPath.getParent());
//                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
//                
//            } catch (IOException e) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION,"PROBLEMAS AL CARGAR ARCHIVOS");
//                alert.setTitle("CARGAR ARCHIVO");
//                alert.setHeaderText("INFORMACIÓN");
//                ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
//                alert.getButtonTypes().setAll(okButton);
//                alert.showAndWait();
//            }
//        }
//    }
    public VBox createUploadedVBox(String fileName) {
        ImageView imageView = new ImageView(new Image("/Images/img_Picture/archivo.png"));
        imageView.setFitWidth(100); // Ajusta el ancho al deseado
        imageView.setFitHeight(100); // Ajusta la altura al deseado
        imageView.setPreserveRatio(true);

        Text fileNameText = new Text(fileName);

        VBox vbox = new VBox(10); // Espaciado de 10px entre elementos
        vbox.getChildren().addAll(imageView, fileNameText);
        return vbox;
    }
    
    public static int countFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            return files.length;
        } else {
            return -1;
        }
    }
    
    @FXML
    private void modelo_option(ActionEvent event) {
        contenido_page.getChildren().clear();
        contenido_page.setAlignment(Pos.CENTER);
        contenido_page.setSpacing(30);
        
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setMaxSize(800, 200);
        vbox.getStyleClass().add("vbox_");
        vbox.setSpacing(30);
        
        Label dragDropLabel = new Label("Drag and drop your 3D files here or");
        Button selectFilesButton = new Button("Select object files");
        selectFilesButton.getStyleClass().add("select-files-button");
        VBox dragDropArea = new VBox(dragDropLabel, selectFilesButton);
        dragDropArea.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(dragDropArea);
        vbox.getStyleClass().add("file-drop-area");
        
        selectFilesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
            @Override
            public void handle(Event event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("CHOOSE 3D MODEL");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("3D Files", "*.stl", "*.obj", "*.fbx", "*.3ds","*.png","*.jpg","*.jpeg"));
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    String originalFileName = selectedFile.getName();
                    Path sourcePath = selectedFile.toPath();
                    int n_files = countFilesInDirectory("src/main/resources/Images/Modelos/");
                    Path targetPath = Paths.get("src/main/resources/Images/Modelos/" + n_files+originalFileName);
                    try {
                        Files.createDirectories(targetPath.getParent());
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        vbox.getChildren().clear();
                        VBox createUploadedVBox = createUploadedVBox(originalFileName);      
                        vbox.getChildren().add(createUploadedVBox);
                        subido = true;
                        url_model = "/Images/Modelos/" +n_files+ originalFileName;
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION,"PROBLEMAS AL CARGAR ARCHIVOS");
                        alert.setTitle("CARGAR ARCHIVO");
                        alert.setHeaderText("INFORMACIÓN");
                        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        alert.getButtonTypes().setAll(okButton);
                        alert.showAndWait();
                    }
                }
            }
        });
        dragDropArea.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
            @Override
            public void handle(Event event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("CHOOSE 3D MODEL");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("3D Files", "*.stl", "*.obj", "*.fbx", "*.3ds","*.png","*.jpg","*.jpeg"));
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    String originalFileName = selectedFile.getName();
                    Path sourcePath = selectedFile.toPath();
                    int n_files = countFilesInDirectory("src/main/resources/Images/Modelos/");
                    Path targetPath = Paths.get("src/main/resources/Images/Modelos/" +n_files+ originalFileName);
                    try {
                        Files.createDirectories(targetPath.getParent());
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        vbox.getChildren().clear();
                        VBox createUploadedVBox = createUploadedVBox(originalFileName);      
                        vbox.getChildren().add(createUploadedVBox);
                        subido = true;
                        url_model = "/Images/Modelos/" +n_files+ originalFileName;
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION,"PROBLEMAS AL CARGAR ARCHIVOS");
                        alert.setTitle("CARGAR ARCHIVO");
                        alert.setHeaderText("INFORMACIÓN");
                        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        alert.getButtonTypes().setAll(okButton);
                        alert.showAndWait();
                    }
                }
            }
        });
        HBox hbox = new HBox();
        
        hbox.setAlignment(Pos.CENTER);
        hbox.setPrefWidth(1080);
        
        VBox vb1 = new VBox();
        vb1.setAlignment(Pos.CENTER);
        vb1.setPrefWidth(400);
        vb1.setSpacing(40);

        VBox vb_nombre = new VBox();
        vb_nombre.setSpacing(10);
        vb_nombre.setAlignment(Pos.CENTER);
        vb_nombre.setPrefWidth(350);
        Label name = new Label("Escribe un nombre para su objecto");
        TextField objectNameField = new TextField();
        objectNameField.setMaxWidth(250);
        objectNameField.setPromptText("NOMBRE");
        vb_nombre.getChildren().addAll(name,objectNameField);

        HBox hbox_visi_preico = new HBox();
        hbox_visi_preico.setAlignment(Pos.CENTER);
        hbox_visi_preico.setPrefWidth(350);
        hbox_visi_preico.setSpacing(20);
        
        VBox vbox_C = new VBox();
        vbox_C.setSpacing(10);
        vbox_C.setAlignment(Pos.CENTER);
        vbox_C.setMaxWidth(250);
        Label l = new Label("Visibilidad");
        ComboBox<String> visibilityComboBox = new ComboBox<>();
        visibilityComboBox.getItems().addAll("Publico", "Privado");
        visibilityComboBox.setValue("Publico");
        vbox_C.getChildren().addAll(l,visibilityComboBox);
        

        VBox vbox_C2 = new VBox();
        vbox_C2.setSpacing(10);
        vbox_C2.setAlignment(Pos.CENTER);
        Label l2 = new Label("Precio");
        TextField precio = new TextField();
        precio.setMaxSize(100, 50);
        vbox_C2.getChildren().addAll(l2,precio);
        
        hbox_visi_preico.getChildren().addAll(vbox_C,vbox_C2);

        
        VBox libreria = new VBox();
        libreria.setSpacing(10);
        libreria.setAlignment(Pos.CENTER);
        String sql = "SELECT * FROM libreria WHERE id_usuario = "+id_user;
        List<List<String>> l_librerias  = conexion.query(conn, sql);
        Label ll = new Label("Libreria");
        ComboBox<Libreria> LibreriaComboBox = new ComboBox<>();
        
        for(List<String> lista : l_librerias){
            String palabra = lista.get(1);
            int id = Integer.parseInt(lista.get(0));
            Libreria libre = new Libreria(id,palabra);
            LibreriaComboBox.getItems().add(libre);
        }
        
        libreria.getChildren().addAll(ll,LibreriaComboBox);
        String palabra = l_librerias.get(0).get(1);
        int id = Integer.parseInt(l_librerias.get(0).get(0));
        Libreria libre2 = new Libreria(id,palabra);
        LibreriaComboBox.setValue(libre2);
        vb1.getChildren().addAll(vb_nombre,hbox_visi_preico,libreria);
       
        VBox vb2 = new VBox();
        vb2.setAlignment(Pos.CENTER);
        vb2.setPrefWidth(350);
        vb2.setSpacing(20);
        Label desc = new Label("Escriba una descripción para su objecto");
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("DESCRIPCION");
        vb2.getChildren().addAll(desc,descriptionArea);
        
        hbox.getChildren().addAll(vb1,vb2);
        
        Button btn_modelo = new Button("NUEVO MODELO");
        btn_modelo.getStyleClass().add("select-files-button");
        btn_modelo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
            @Override
            public void handle(Event event) {
                String name = objectNameField.getText();
                String visibilidad = visibilityComboBox.getValue();
                if(visibilidad=="Publico"){
                    visibilidad="1";
                }
                else{
                    visibilidad="0";
                }
                String precio_s = precio.getText();
                String descripcion = descriptionArea.getText();
                String libreria_v = LibreriaComboBox.getValue().getId_libreria()+"";
                Boolean available = false;
                if(name!=null && visibilidad!=null && precio_s !=null && descripcion !=null && libreria_v!=null){
                    if(!name.isEmpty() && !visibilidad.isEmpty() && !precio_s.isEmpty() && !descripcion.isEmpty()){
                        available = true;
                    }
                }
                if(available){
                    conexion.insertarModelo(conn,descripcion,precio_s,name,url_model,libreria_v,visibilidad);
                    url_model="";
                    // aqui debo ir a home 
                    contenido_page.getChildren().clear();
                }
                else{
                     Alert alert = new Alert(Alert.AlertType.INFORMATION,"HAY CAMPOS ERRONEOS O VACIOS");
                     alert.setTitle("CAMPOS ERRONES O VACIOS");
                     alert.setHeaderText("INFORMACIÓN");
                     ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                     alert.getButtonTypes().setAll(okButton);
                     alert.showAndWait();    
                }    
            }
        });
        // Añadiendo todos los elementos al VBox principal
        contenido_page.getChildren().addAll(vbox,hbox,btn_modelo);
        
    }

    @FXML
    private void show_usercontent(MouseEvent event) {
    }

    @FXML
    private void hand(MouseEvent event) {
        this.link_tribes.setCursor(Cursor.HAND);
    }

    @FXML
    private void handHome(MouseEvent event) {
        this.logo.setCursor(Cursor.HAND);
    }

    @FXML
    private void hand1(MouseEvent event) {
        this.link_tienda.setCursor(Cursor.HAND);

    }

    @FXML
    private void hand2(MouseEvent event) {
        this.link_carrito_compra.setCursor(Cursor.HAND);
    }

    @FXML
    private void hand3(MouseEvent event) {
        this.link_user.setCursor(Cursor.HAND);
    }
    
}
