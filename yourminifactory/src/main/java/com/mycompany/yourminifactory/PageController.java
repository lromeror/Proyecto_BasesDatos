/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.yourminifactory;

import Clases.Conexion;
import Clases.Model;
import Clases.Libreria;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    public Conexion conexion;
    public Connection conn;
    @FXML
    private ImageView logo;
    @FXML
    private VBox contenido_page;
    private String nameUser;
    public int id_user;
    public boolean subido = false;
    public String url_model;
    private String typeUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
        conn = conexion.connect();
        ImageView img = new ImageView(new Image("/Images/img_Picture/NuevoDiagramaLogico.jpeg"));
        img.setFitHeight(600);
        img.setFitWidth(1000);
        contenido_page.getChildren().add(img);
    }

    public void setId_User(int id, String nameUser) {
        this.id_user = id;
        this.nameUser = nameUser;
    }

    public void setId_User(int id, String nameUser, String type) {
        this.id_user = id;
        this.nameUser = nameUser;
        this.typeUser = type;
    }

    @FXML
    private void home(MouseEvent event) {

        contenido_page.getChildren().clear();
        ImageView img = new ImageView(new Image("/Images/img_Picture/NuevoDiagramaLogico.jpeg"));
        img.setFitHeight(600);
        img.setFitWidth(1000);
        contenido_page.getChildren().add(img);

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

    private VBox createModelCard(List<String> model, int index) {
        // int idModel, String description, double price, String title, LocalDate publicationDate, int libraryId
        // [1, Modelo 3D Impresionante, 50, Estatua de Dragón, dragon.stl, 2023-03-01, 1, 1]
        int modId = Integer.parseInt(model.get(0));
        double pr = Double.parseDouble(model.get(2));
        int libId = Integer.parseInt(model.get(6));
        int vis = Integer.parseInt(model.get(7));
        String date = model.get(5);

        Model m = new Model(modId, model.get(1), pr, model.get(3), date, libId, vis);

        ImageView imageView = new ImageView(new Image(m.getImage()));
        imageView.setFitWidth(200);
        imageView.setFitHeight(150);

        Text nameLabel = new Text(m.getIdModel() + " - " + m.getTitle());
        Text priceLabel = new Text("Price: " + pr);
        priceLabel.setFont(Font.font("Arial", 16));
        nameLabel.setFont(Font.font("Arial", 16));
        Button deleteButton = new Button("Eliminar");
        deleteButton.setOnAction(Event -> {
            System.out.println(conexion.deleteRecordAnadir(id_user, modId));
            this.show_carrito_compraContent_AV(Event);
            System.out.println("re biennnnn");
        });
        deleteButton.getStyleClass().add("delete-button");

        VBox card = new VBox(10, imageView, nameLabel, priceLabel, deleteButton);

        card.setPrefSize(225, 231);
        card.setPadding(new Insets(10));
        card.getStyleClass().add("card");

        return card;

    }

    private VBox createModelCardTienda(List<String> model, int index) {
        // int idModel, String description, double price, String title, LocalDate publicationDate, int libraryId
        // [1, Modelo 3D Impresionante, 50, Estatua de Dragón, dragon.stl, 2023-03-01, 1, 1]
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
        imageView.setFitWidth(200);
        imageView.setFitHeight(150);

        Text nameLabel = new Text(m.getIdModel() + " - " + m.getTitle());
        Text priceLabel = new Text("Price: " + pr);
        priceLabel.setFont(Font.font("Arial", 16));
        nameLabel.setFont(Font.font("Arial", 16));
        Button addButton = new Button("Añadir");
        addButton.setOnAction(Event -> {
            conexion.insertarDatoCarComp(conn, id_user, modId); // remember, the id_user will be the same as the id_car
            System.out.println("re biennnnn");

        });
        addButton.getStyleClass().add("add-button");
        VBox card = new VBox(10, imageView, nameLabel, priceLabel, addButton);

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

        Platform.runLater(() -> {
            contenido_page.getChildren().clear();
            contenido_page.getChildren().add(grid);
        });
    }

    @FXML
    private void showTiendaContent(MouseEvent event) {
        List<List<String>> resultados = conexion.query(conn, "SELECT * FROM yourminifactory.modelo where visibilidad=1;");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(40);
        grid.setHgap(40);

        for (int i = 0; i < 20; i++) {
            List<String> model = resultados.get(i);
            System.out.println(model);
            VBox card = createModelCardTienda(model, i + 1);
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
        Platform.runLater(() -> {
            contenido_page.getChildren().clear();
            contenido_page.getChildren().add(grid);
        });
    }

    @FXML
    private void show_carrito_compraContent(MouseEvent event) {
        List<List<String>> resultados = conexion.query(conn, "select *\n"
                + "from modelo \n"
                + "where modelo.id_modelo in (select modelo.id_modelo\n"
                + "from modelo join anadir on modelo.id_modelo=anadir.id_modelo join carro_compra on carro_compra.id_carrito=anadir.id_carrito\n"
                + "where carro_compra.id_usuario=" + this.id_user + ")");
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
        Platform.runLater(() -> {
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

        selectFilesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
            @Override
            public void handle(Event event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("CHOOSE 3D MODEL");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("3D Files", "*.stl", "*.obj", "*.fbx", "*.3ds", "*.png", "*.jpg", "*.jpeg"));
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    String originalFileName = selectedFile.getName();
                    Path sourcePath = selectedFile.toPath();
                    int n_files = countFilesInDirectory("src/main/resources/Images/Modelos/");
                    Path targetPath = Paths.get("src/main/resources/Images/Modelos/" + n_files + originalFileName);
                    try {
                        Files.createDirectories(targetPath.getParent());
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        vbox.getChildren().clear();
                        VBox createUploadedVBox = createUploadedVBox(originalFileName);
                        vbox.getChildren().add(createUploadedVBox);
                        subido = true;
                        url_model = "/Images/Modelos/" + n_files + originalFileName;
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "PROBLEMAS AL CARGAR ARCHIVOS");
                        alert.setTitle("CARGAR ARCHIVO");
                        alert.setHeaderText("INFORMACIÓN");
                        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        alert.getButtonTypes().setAll(okButton);
                        alert.showAndWait();
                    }
                }
            }
        });
        dragDropArea.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
            @Override
            public void handle(Event event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("CHOOSE 3D MODEL");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("3D Files", "*.stl", "*.obj", "*.fbx", "*.3ds", "*.png", "*.jpg", "*.jpeg"));
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    String originalFileName = selectedFile.getName();
                    Path sourcePath = selectedFile.toPath();
                    int n_files = countFilesInDirectory("src/main/resources/Images/Modelos/");
                    Path targetPath = Paths.get("src/main/resources/Images/Modelos/" + n_files + originalFileName);
                    try {
                        Files.createDirectories(targetPath.getParent());
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        vbox.getChildren().clear();
                        VBox createUploadedVBox = createUploadedVBox(originalFileName);
                        vbox.getChildren().add(createUploadedVBox);
                        subido = true;
                        url_model = "/Images/Modelos/" + n_files + originalFileName;
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "PROBLEMAS AL CARGAR ARCHIVOS");
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
        vb_nombre.getChildren().addAll(name, objectNameField);

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
        vbox_C.getChildren().addAll(l, visibilityComboBox);

        VBox vbox_C2 = new VBox();
        vbox_C2.setSpacing(10);
        vbox_C2.setAlignment(Pos.CENTER);
        Label l2 = new Label("Precio");
        TextField precio = new TextField();
        precio.setMaxSize(100, 50);
        vbox_C2.getChildren().addAll(l2, precio);

        hbox_visi_preico.getChildren().addAll(vbox_C, vbox_C2);

        VBox libreria = new VBox();
        libreria.setSpacing(10);
        libreria.setAlignment(Pos.CENTER);
        String sql = "SELECT * FROM libreria WHERE id_usuario = " + id_user;
        List<List<String>> l_librerias = conexion.query(conn, sql);
        Label ll = new Label("Libreria");
        ComboBox<Libreria> LibreriaComboBox = new ComboBox<>();
        for (List<String> lista : l_librerias) {
            String palabra = lista.get(1);
            int id = Integer.parseInt(lista.get(0));
            Libreria libre = new Libreria(id, palabra);
            LibreriaComboBox.getItems().add(libre);
        }
        libreria.getChildren().addAll(ll, LibreriaComboBox);
        String palabra = l_librerias.get(0).get(1);
        int id = Integer.parseInt(l_librerias.get(0).get(0));
        Libreria libre2 = new Libreria(id, palabra);
        LibreriaComboBox.setValue(libre2);
        vb1.getChildren().addAll(vb_nombre, hbox_visi_preico, libreria);

        VBox vb2 = new VBox();
        vb2.setAlignment(Pos.CENTER);
        vb2.setPrefWidth(350);
        vb2.setSpacing(20);
        Label desc = new Label("Escriba una descripción para su objecto");
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("DESCRIPCION");
        vb2.getChildren().addAll(desc, descriptionArea);

        hbox.getChildren().addAll(vb1, vb2);

        Button btn_modelo = new Button("NUEVO MODELO");
        btn_modelo.getStyleClass().add("select-files-button");
        btn_modelo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
            @Override
            public void handle(Event event) {
                String name = objectNameField.getText();
                String visibilidad = visibilityComboBox.getValue();
                if (visibilidad == "Publico") {
                    visibilidad = "1";
                } else {
                    visibilidad = "0";
                }
                String precio_s = precio.getText();
                String descripcion = descriptionArea.getText();
                String libreria_v = LibreriaComboBox.getValue().getId_libreria() + "";
                Boolean available = false;
                if (name != null && visibilidad != null && precio_s != null && descripcion != null && libreria_v != null) {
                    if (!name.isEmpty() && !visibilidad.isEmpty() && !precio_s.isEmpty() && !descripcion.isEmpty()) {
                        available = true;
                    }
                }
                if (available) {
                    conexion.insertarModelo(conn, descripcion, precio_s, name, url_model, libreria_v, visibilidad);
                    url_model = "";
                    // aqui debo ir a home 
                    contenido_page.getChildren().clear();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "HAY CAMPOS ERRONEOS O VACIOS");
                    alert.setTitle("CAMPOS ERRONES O VACIOS");
                    alert.setHeaderText("INFORMACIÓN");
                    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    alert.getButtonTypes().setAll(okButton);
                    alert.showAndWait();
                }
            }
        });
        // Añadiendo todos los elementos al VBox principal
        contenido_page.getChildren().addAll(vbox, hbox, btn_modelo);

    }

    @FXML
    private void show_usercontent(MouseEvent event) {
        this.contenido_page.getChildren().clear();
        this.contenido_page.setAlignment(Pos.TOP_CENTER);
        ImageView photo = new ImageView(new Image("Images/img_Picture/foto.png"));
        photo.setFitWidth(1072);
        photo.setFitHeight(400);
        StackPane stackPane = new StackPane(photo);
        stackPane.setStyle("-fx-border-color: #BCBCBC  ; "+ "-fx-border-width: 7px; "+ "-fx-border-radius: 7px; "+ "-fx-background-radius: 7px; ");
        Text name = new Text("\n" + "Name: " + this.nameUser + "\n");
        name.setFont(Font.font("null", FontWeight.BOLD, 19));
        HBox buts = new HBox();
        buts.setAlignment(Pos.CENTER);
        Text espacio = new Text("      ");
        VBox vConten = new VBox();
        vConten.setAlignment(Pos.CENTER);
        if (this.typeUser.equals("Admin")) {
            Button btAdmin = new Button("Crear Company");
            btAdmin.setStyle("-fx-background-color: #66D0A8; "
                    + "-fx-text-fill: white; "
                    + "-fx-font-weight: bold; "
                    + "-fx-border-color: #FFFFFF; "
                    + "-fx-border-width: 3px; "
                    + "-fx-border-radius: 4px; "
                    + "-fx-background-radius: 6px; "
                    + "-fx-padding: 12px;" + "-fx-font-size: 15px;");
            btAdmin.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                vConten.getChildren().clear();
                vConten.setAlignment(Pos.TOP_LEFT);
                vConten.setSpacing(10);
                vConten.setPadding(new Insets(15, 380, 35, 380));
                Text tiReg = new Text("\nRegister Company");
                tiReg.setFont(Font.font("null", FontWeight.BOLD, 19));
                Label descripTi = new Label("Description:");
                TextField descrFie = new TextField();
                Label cantPioTi = new Label("Numbers Pioners:");
                TextField cantFiel = new TextField();
                Label moneyReTi = new Label("Raised money:");
                TextField moneyFie = new TextField();
                Button btnSave = new Button("Save");
                Button btnCancel = new Button("Cancel");
                HBox btnsAd = new HBox(btnSave, btnCancel);
                btnsAd.setSpacing(15);
                Text alert=new Text();
                btnSave.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e) -> {
                    alert.setText(this.btnRegisterSave(descrFie, cantFiel, moneyFie));
                    descrFie.setText("");cantFiel.setText("");moneyFie.setText("");
                });
                btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e) -> {
                   descrFie.setText("");cantFiel.setText("");moneyFie.setText("");alert.setText("");
                });
                btnSave.setStyle("-fx-background-color: #55C959; "+ "-fx-text-fill: white; "+ // "-fx-font-weight: bold; " + // Negrita
                        "-fx-border-color: transparent; "+ "-fx-border-radius: 5; "+ "-fx-background-radius: 5; "+ "-fx-font-size: 14px;"+ "-fx-padding: 3 20;");
                btnSave.setOnMousePressed(e -> btnSave.setStyle("-fx-background-color: #367C39; "+ "-fx-text-fill: white; "+ // "-fx-font-weight: bold; " + // Negrita
                        "-fx-border-color: transparent; "+ "-fx-border-radius: 5;"+ "-fx-background-radius: 5; " + "-fx-font-size: 14px;"+ "-fx-padding: 3 20;"));
                btnSave.setOnMouseReleased(e -> btnSave.setStyle("-fx-background-color: #55C959; "+ "-fx-text-fill: white; "+ // "-fx-font-weight: bold; " + // Negrita
                        "-fx-border-color: transparent; "+ "-fx-border-radius: 5; "+ "-fx-background-radius: 5; "+ "-fx-font-size: 14px;"+ "-fx-padding: 3 20;"));
                btnCancel.setStyle("-fx-background-color: #457bba; "+ "-fx-text-fill: white; "+ // "-fx-font-weight: bold; " + // Negrita
                        "-fx-border-color: transparent; "+ "-fx-border-radius: 5; "+ "-fx-background-radius: 5; "+ "-fx-font-size: 14px;"+ "-fx-padding: 3 20;");
                btnCancel.setOnMousePressed(e -> btnCancel.setStyle("-fx-background-color: #315986; "+ "-fx-text-fill: white; "+ // "-fx-font-weight: bold; " + // Negrita
                        "-fx-border-color: transparent; "+ "-fx-border-radius: 5;"+ "-fx-background-radius: 5; "+ "-fx-font-size: 14px;"+ "-fx-padding: 3 20;"));
                btnCancel.setOnMouseReleased(e -> btnCancel.setStyle("-fx-background-color: #457bba; "+ "-fx-text-fill: white; "+ // "-fx-font-weight: bold; " + // Negrita
                        "-fx-border-color: transparent; "+ "-fx-border-radius: 5; "+ "-fx-background-radius: 5; "+ "-fx-font-size: 14px;"+ "-fx-padding: 3 20;"));
                vConten.getChildren().addAll(tiReg, descripTi, descrFie, cantPioTi, cantFiel, moneyReTi, moneyFie, btnsAd,alert);});
            Button btnShowAllCompany = new Button("Company");
            btnShowAllCompany.setStyle("-fx-background-color: #F36230; "+ "-fx-text-fill: white; "+ "-fx-font-weight: bold; "+ "-fx-border-color: #FFFFFF; "+ "-fx-border-width: 3px; "+ "-fx-border-radius: 4px; "+ "-fx-background-radius: 6px; "+ "-fx-padding: 12px;" + "-fx-font-size: 15px;");
            List<List<String>> lisLib = conexion.query(conn, "select * from campana;");
            btnShowAllCompany.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                vConten.getChildren().clear();
                vConten.setPadding(new Insets(0, 0, 30, 0));
                vConten.setAlignment(Pos.CENTER);
                vConten.setSpacing(0);
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
                vConten.getChildren().addAll(tituloList, grid);});
            buts.getChildren().addAll(btAdmin, espacio, btnShowAllCompany);
        } else {
            Button bt = new Button("Modelos");
            bt.setStyle("-fx-background-color: #66D0A8; "+ "-fx-text-fill: white; "+ "-fx-font-weight: bold; "+ "-fx-border-color: #FFFFFF; "+ "-fx-border-width: 3px; "+ "-fx-border-radius: 4px; "+ "-fx-background-radius: 6px; "+ "-fx-padding: 12px;" + "-fx-font-size: 15px;");
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
            Button bt2 = new Button("Company");
            bt2.setStyle("-fx-background-color: #F36230; "+ "-fx-text-fill: white; "+ "-fx-font-weight: bold; "+ "-fx-border-color: #FFFFFF; "+ "-fx-border-width: 3px; "+ "-fx-border-radius: 4px; "+ "-fx-background-radius: 6px; "+ "-fx-padding: 12px;" + "-fx-font-size: 15px;");
            List<List<String>> lisLib = conexion.query(conn, "select camp.id_campana,camp.descripcion,camp.pioneros,camp.dinerorecaudado from usuario us \n"
                    + "join apoya ap on us.id_usuario=ap.id_usuario\n"
                    + "join campana camp on ap.id_campana=camp.id_campana\n"
                    + "where us.id_usuario=" + this.id_user + ";");
            bt2.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                vConten.getChildren().clear();
                Text tituloList = new Text("\n" + "Campaign List" + "\n");
                tituloList.setFont(Font.font("null", FontWeight.BOLD, 19));
                GridPane grid = new GridPane();
                grid.setAlignment(Pos.CENTER);grid.setVgap(27);grid.setHgap(27);
                for (int i = 0; i < showCampana(lisLib).size(); i++) {
                    VBox tribe = showCampana(lisLib).get(i);
                    grid.add(tribe, i % 4, i / 4);
                }
                vConten.getChildren().addAll(tituloList, grid);
            });
            buts.getChildren().addAll(bt, espacio, bt2);
        }
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
                v1.setStyle("-fx-background-color: #B0BEC5; "+ "-fx-border-color: black; "+ "-fx-border-radius: 10; "+ "-fx-background-radius: 10; "+ "-fx-padding: 10;");
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
            v1.setStyle("-fx-background-color: #B0BEC5; "+ "-fx-border-color: black; " + "-fx-border-radius: 10; "+ "-fx-background-radius: 10; "+ "-fx-padding: 10;");
            listVbox.add(v1);
        }
        return listVbox;
    }

    private String btnRegisterSave(TextField descrFie, TextField cantFiel, TextField moneyFie) {
        if (!(descrFie.getText().equals("") ||cantFiel.getText().equals("")||moneyFie.getText().equals(""))) {
            this.conexion.insertarDatoCamapana(conn, descrFie.getText(), cantFiel.getText(), moneyFie.getText());
            return "Successful registration";
        }
        return "Please fill out the data fields";
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

    private void show_carrito_compraContent_AV(ActionEvent Event) {
        List<List<String>> resultados = conexion.query(conn, "select *\n"
                + "from modelo \n"
                + "where modelo.id_modelo in (select modelo.id_modelo\n"
                + "from modelo join anadir on modelo.id_modelo=anadir.id_modelo join carro_compra on carro_compra.id_carrito=anadir.id_carrito\n"
                + "where carro_compra.id_usuario=" + this.id_user + ")");
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
        Platform.runLater(() -> {
            contenido_page.getChildren().clear();
            contenido_page.getChildren().add(grid);
        });
    }

}
