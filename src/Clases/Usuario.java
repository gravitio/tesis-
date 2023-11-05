package Clases;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int codigo;
    private String nombre;
    private int DNI;
    private String contraseña;
    private int rol; // Cambia el tipo de dato a int para almacenar el código de rol
    private String correo;

    public Usuario() {
    }

    public Usuario(int codigo, String nombre, int DNI, String contraseña, int rol, String correo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.DNI = DNI;
        this.contraseña = contraseña;
        this.rol = rol;
        this.correo = correo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getRol() { // Cambia el tipo de retorno a int
        return rol;
    }

    public void setRol(int rol) { // Cambia el tipo de dato del parámetro a int
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Método para guardar un usuario en la base de datos
    public void guardarUsuario(Connection connection) throws SQLException {
        String sql = "INSERT INTO Usuario (nombre, DNI, contraseña, rol, correo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, getNombre());
            statement.setInt(2, getDNI());
            statement.setString(3, getContraseña());
            statement.setInt(4, getRol()); // Cambia el tipo de dato a int
            statement.setString(5, getCorreo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Propagar la excepción hacia arriba
        }
    }

    // Método para recuperar todos los usuarios de la base de datos
    public static List<Usuario> obtenerUsuarios(Connection connection) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                String nombre = resultSet.getString("nombre");
                int DNI = resultSet.getInt("DNI");
                String contraseña = resultSet.getString("contraseña");
                int rol = resultSet.getInt("rol"); // Cambia el tipo de dato a int
                String correo = resultSet.getString("correo");
                Usuario usuario = new Usuario(codigo, nombre, DNI, contraseña, rol, correo);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}
