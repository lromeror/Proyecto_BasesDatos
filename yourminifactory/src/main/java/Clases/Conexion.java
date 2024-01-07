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
import java.sql.Statement;
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

}
