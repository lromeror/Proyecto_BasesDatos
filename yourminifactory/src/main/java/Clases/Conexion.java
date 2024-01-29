/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.math.BigDecimal;
import java.sql.CallableStatement;
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
        CallableStatement cs = null;
    try {
        String sql = "{CALL InsertarUsuario(?, ?, ?, ?)}";
        cs = conn.prepareCall(sql);
        cs.setString(1, nombre);
        cs.setString(2, fecha);
        cs.setString(3, passw);
        cs.setString(4, correo);

        int filasInsertadas = cs.executeUpdate();
        if (filasInsertadas > 0) {
            System.out.println("Inserción exitosa");
        } else {
            System.out.println("No se pudo insertar el dato");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (cs != null) {
                cs.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    }

    public void insertarDatoSuper(Connection conn, String nameTable, String nombre, String fecha, String passw, String correo) {
        CallableStatement cs = null;
        try {
            String sql = "{CALL InsertarDatoSuper(?, ?, ?, ?, ?)}";
            cs = conn.prepareCall(sql);
            cs.setString(1, nameTable);
            cs.setString(2, nombre);
            cs.setString(3, fecha);
            cs.setString(4, passw);
            cs.setString(5, correo);

            int filasInsertadas = cs.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("No se pudo insertar el dato");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }


    public void insertarModelo(Connection conn, String descripcion, String precio, String titulo, String model, String libreria, String visibilidad) {
        CallableStatement cs = null;
        try {
            String sql = "{CALL InsertarModelo(?, ?, ?, ?, ?, ?, ?)}";
            cs = conn.prepareCall(sql);
            cs.setString(1, descripcion);
            cs.setString(2, precio);
            cs.setString(3, titulo);
            cs.setString(4, model);
            cs.setInt(5, Integer.parseInt(libreria));
            cs.setBoolean(6, Boolean.parseBoolean(visibilidad));

            int filasInsertadas = cs.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("No se pudo insertar el dato");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
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
        CallableStatement cs = null;
        try {
            String sql = "{CALL InsertarDatoCarComp(?, ?)}";
            cs = conn.prepareCall(sql);
            cs.setInt(1, id_car);
            cs.setInt(2, id_mod);

            cs.executeUpdate();
            System.out.println("Operación realizada con éxito");
        } catch (SQLException e) {
            System.out.println("Error al insertar datos: " + e.getMessage());
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }

    public boolean deleteRecordAnadir(int idCarrito, int idModelo) {
        String sql = "{CALL EliminarRegistroAnadir(?, ?)}";

        try (Connection conn = DriverManager.getConnection(cadena, usuario, contraseña); 
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, idCarrito);
            cstmt.setInt(2, idModelo);

            int rowsAffected = cstmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public void insertarDatoCamapana(Connection conn, String descripcion, String pionero, String moneyRe) {
        CallableStatement cs = null;
        try {
            String sql = "{CALL InsertarDatoCampana(?, ?, ?)}";
            cs = conn.prepareCall(sql);
            cs.setString(1, descripcion);
            cs.setInt(2, Integer.parseInt(pionero));
            cs.setBigDecimal(3, new BigDecimal(moneyRe));

            int filasInsertadas = cs.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("No se pudo insertar el dato");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }
    
    public boolean deleteRecordApoya(Connection conn, int idUsuario, int idCampana) {
        String sql = "{CALL EliminarRegistroApoya(?, ?)}";

        try (CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, idUsuario);
            cstmt.setInt(2, idCampana);

            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
     public void insertarDatoApoya(Connection conn, int idUsuario, int idCampana, int nivel) {
        CallableStatement cs = null;
        try {
            String sql = "{CALL InsertarDatoApoya(?, ?, ?)}";
            cs = conn.prepareCall(sql);
            cs.setInt(1, idUsuario);
            cs.setInt(2, idCampana);
            cs.setInt(3, nivel);

            int filasInsertadas = cs.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa en la tabla Apoya");
            } else {
                System.out.println("No se pudo insertar el registro en la tabla Apoya");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar en la tabla Apoya");
            e.printStackTrace();
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }


}
