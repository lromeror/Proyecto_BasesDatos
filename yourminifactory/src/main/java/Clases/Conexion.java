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
import java.sql.Statement;
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
            ps = conn.prepareStatement(sql); 
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
            ps = conn.prepareStatement(sql); 
            rs = ps.executeQuery();
            
               while (rs.next()) {
                   resultados.add(rs.getString(1));
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


    public void insertarModelo(Connection conn, String descripcion, String precio, String titulo, String model, String libreria, Boolean visibilidad) {
        CallableStatement cs = null;
        try {
            String sql = "{CALL InsertarModelo(?, ?, ?, ?, ?, ?)}";
            cs = conn.prepareCall(sql);
            cs.setString(1, descripcion);
            cs.setString(2, precio);
            cs.setString(3, titulo);
            cs.setString(4, model);
            cs.setInt(5, Integer.parseInt(libreria));
            cs.setBoolean(6, visibilidad);

            int filasInsertadas = cs.executeUpdate();
   
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
    
    public boolean deleteTribuUser(int id_user, int id_tribu) {
        String sql = "{CALL EliminarRegistroTribu(?, ?)}";

        try (Connection conn = DriverManager.getConnection(cadena, usuario, contraseña); 
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id_user);
            cstmt.setInt(2, id_tribu);

            int rowsAffected = cstmt.executeUpdate();

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
    public void insertarTribuUser(Connection conn, int id_user, int id_tribu) {
        CallableStatement cs = null;
        try {
            String sql = "{CALL InsertarTribuUser(?, ?)}";
            cs = conn.prepareCall(sql);
            cs.setInt(1, id_user);
            cs.setInt(2, id_tribu);
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
     PreparedStatement ps = null;
     try {
         String sql = "INSERT INTO Apoya (id_usuario, id_campana, nivel) VALUES (?, ?, ?)";
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
            System.out.println("Error al insertar en la tabla Apoya: " + e.getMessage());
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


     
     public void insertarDatoTribu(Connection conn, String descripcion, String title, int id_cate) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO tribu (descripcion, name, id_categoria) VALUES (?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, descripcion);
            ps.setString(2, title);
            ps.setInt(3, id_cate);

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
     
    public boolean registerCampaignWithAssignments(String campaignDescription, List<String> assignments) {
        Connection conn = null;
        PreparedStatement pstmtCampaign = null;
        PreparedStatement pstmtAssignment = null;
        ResultSet generatedKeys = null;
        boolean isSuccess = false;

        try {
            conn = this.connect();
            conn.setAutoCommit(false); // Desactivar auto-commit para manejar la transacción manualmente.

            // Inserción de la campaña
            String insertCampaignSQL = "INSERT INTO campana (descripcion) VALUES (?)";
            pstmtCampaign = conn.prepareStatement(insertCampaignSQL, Statement.RETURN_GENERATED_KEYS);
            pstmtCampaign.setString(1, campaignDescription);
            int affectedRowsCampaign = pstmtCampaign.executeUpdate();

            if (affectedRowsCampaign == 0) {
                throw new SQLException("Creating campaign failed, no rows affected.");
            }

            generatedKeys = pstmtCampaign.getGeneratedKeys();
            if (generatedKeys.next()) {
                int campaignId = generatedKeys.getInt(1);

                // Inserción de las asignaciones
                String insertAssignmentSQL = "INSERT INTO asignacion (id_campana, id_tier, id_modelo) VALUES (?, ?, ?)";
                pstmtAssignment = conn.prepareStatement(insertAssignmentSQL);

                for (String assignment : assignments) {
                    String[] parts = assignment.split(",");
                    int idTier = Integer.parseInt(parts[1].trim()); // Asegúrate de que la indexación sea correcta.
                    int idModelo = Integer.parseInt(parts[2].trim()); // Asegúrate de que la indexación sea correcta.

                    pstmtAssignment.setInt(1, campaignId);
                    pstmtAssignment.setInt(2, idTier);
                    pstmtAssignment.setInt(3, idModelo);
                    pstmtAssignment.executeUpdate();
                }

                conn.commit(); // Confirmar la transacción.
                isSuccess = true;
                System.out.println("Campaign and assignments inserted successfully.");
            } else {
                throw new SQLException("Creating campaign failed, no ID obtained.");
            }
        } catch (SQLException e) {
            // Intentar revertir la transacción si hay un error
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transaction failed, changes were rolled back.");
                } catch (SQLException ex) {
                    System.out.println("Couldn't roll back the transaction.");
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Cerrar los recursos
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (pstmtCampaign != null) pstmtCampaign.close();
                if (pstmtAssignment != null) pstmtAssignment.close();
                if (conn != null) {
                    conn.setAutoCommit(true); // Restaurar el modo auto-commit
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return isSuccess;
    }

   public int getNextAutoIncrement(Connection conn, String tableName) {
    String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, "yourminifactory");  
        pstmt.setString(2, tableName);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("AUTO_INCREMENT");
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return -1; // En caso de error o si no se puede obtener el valor
}


    


}
