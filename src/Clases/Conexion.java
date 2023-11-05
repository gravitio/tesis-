package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/tecnogras";
        String usuario = "root";
        String contraseña = ""; // Cambia "tu_contraseña" por la contraseña correcta

        try {
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            return conn;
        } catch (SQLException e) {
            // Manejar la excepción, por ejemplo, imprimir un mensaje de error
            e.printStackTrace();
            return null;
        }
    }
}
