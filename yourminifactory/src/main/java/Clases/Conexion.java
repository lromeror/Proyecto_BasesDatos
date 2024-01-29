/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author angelozurita
 */
public class Conexion {

    Connection conectar = null;
    String usuario = "Administrador";
    String contraseña = "a5min#2023";
    String bd = "yourminifactory";
    String ip = "yourminifactory.mysql.database.azure.com";
//    String ip = "localhost";
    String puerto = "3306";

    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

//    public Connection ConectDB(){
//        try{
//            Class.forName("com.mysql.cj.jdb.Driver");
//            conectar = DriverManager.getConnection(cadena, usuario, contraseña);
//            System.out.println("Conectado exitosamente");
//        }catch(Exception e){
//            System.out.println("Incorrecta la conexion");
//            e.printStackTrace();
//        }
//        return conectar;
//    }
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(cadena, usuario, contraseña);
            if (conn != null) {
                System.out.println("Ingreso Exitoso");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return conn;
    }

    public List<List<String>> query(Connection conn, String sql) {
        List<List<String>> resultados = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql); // Utilizar la conexión pasada como parámetro
            rs = ps.executeQuery();

            while (rs.next()) {
                List<String> fila = new ArrayList<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    fila.add(rs.getString(i));
                }
                resultados.add(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                // No cerrar conn aquí, porque se puede reutilizar
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return resultados;
    }
    public List<String> query2(Connection conn, String sql) {
        List<String> resultados = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql); // Utilizar la conexión pasada como parámetro
            rs = ps.executeQuery();
            
               while (rs.next()) {
                   // Asumiendo que cada fila contiene exactamente un String
                   resultados.add(rs.getString(1)); // Añade directamente el String a la lista
               }
           } catch (SQLException e) {
               e.printStackTrace();
           } finally {
               try {
                   if (rs != null) {
                       rs.close();
                   }
                   if (ps != null) {
                       ps.close();
                   }
                   // No cerrar conn aquí, porque se puede reutilizar
               } catch (SQLException se) {
                   se.printStackTrace();
               }
           }
           return resultados;
    }

    public void insertarDato(Connection conn, String nombre, String fecha, String passw, String correo) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO usuario (nombre, fecha_nacimi, contrasena, correo) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, fecha);
            ps.setString(3, passw);
            ps.setString(4, correo);

            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("No se pudo insertar el dato");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void insertarDatoSuper(Connection conn, String nameTable,String nombre, String fecha, String passw, String correo) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO "+nameTable+" (nombre, fecha_nacimi, contrasena, correo) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, fecha);
            ps.setString(3, passw);
            ps.setString(4, correo);

            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("No se pudo insertar el dato");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void insertarModelo(Connection conn, String descripcion, String precio, String titulo, String model, String libreria, String visibilidad) {
        PreparedStatement ps = null;
        try {
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fecha = date.format(formatter);
            String sql = "INSERT INTO modelo (descripcion, precio, titulo, model,fecha_publicacion,id_libreria,visibilidad) VALUES (?, ?, ?, ?, ?, ?, ? )";
            ps = conn.prepareStatement(sql);
            ps.setString(1, descripcion);
            ps.setString(2, precio);
            ps.setString(3, titulo);
            ps.setString(4, model);
            ps.setString(5, fecha);
            ps.setString(6, libreria);
            ps.setString(7, visibilidad);
            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("No se pudo insertar el dato");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Conexion co = new Conexion();
        Connection conn = co.connect();

        // Ejemplo de inserción de datos en la tabla
        co.insertarDato(conn, "Pitusa2", "2020-12-01", "12345679", "correo@pitusa.ec");

    }

    public void insertarDatoCarComp(Connection conn, int id_car, int id_mod) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO anadir (id_carrito, id_modelo) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_car);
            ps.setInt(2, id_mod);

            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("No se pudo insertar el dato");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    

    public boolean deleteRecordAnadir(int idCarrito, int idModelo) {
        
        String sql = "DELETE FROM anadir WHERE id_carrito = ? AND id_modelo = ?";
        try (Connection conn = DriverManager.getConnection(cadena, usuario, contraseña); 
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCarrito);
            pstmt.setInt(2, idModelo);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void insertarDatoCamapana(Connection conn, String descripcion) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO campana (descripcion) VALUES (?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, descripcion);
//            ps.setString(2, pionero);
//            ps.setString(3, moneyRe);

            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("No se pudo insertar el dato");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    public void insertarDatoTribu(Connection conn, String descripcion, String title) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO tribu (descripcion) VALUES (?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, descripcion);
            ps.setString(2, title);


            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("No se pudo insertar el dato");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    
    public boolean deleteRecordApoya(Connection conn, int idUsuario, int idCampana) {
        String sql = "DELETE FROM Apoya WHERE id_usuario = ? AND id_campana = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUsuario);
            pstmt.setInt(2, idCampana);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void insertarDatoApoya(Connection conn, int idUsuario, int idCampana, int nivel) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO Apoya (id_usuario, id_campana,nivel) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idCampana);
             ps.setInt(3, nivel);
            
            
            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa en la tabla Apoya");
            } else {
                System.out.println("No se pudo insertar el registro en la tabla Apoya");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar en la tabla Apoya");
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }


}
