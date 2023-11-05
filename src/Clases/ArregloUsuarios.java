package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ArregloUsuarios {

    public final ArrayList<Usuario> usuarios = new ArrayList<>();
    public int contador = 0;
    int contadorfila = 0;

    public void guardarUsu(Usuario nuevoUsu, Connection connection) {
        String sql = "INSERT INTO Usuario (nombre, DNI, contraseña, rol, correo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nuevoUsu.getNombre());
            statement.setInt(2, nuevoUsu.getDNI());
            statement.setString(3, nuevoUsu.getContraseña());
            statement.setInt(4, nuevoUsu.getRol());
            statement.setString(5, nuevoUsu.getCorreo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar el usuario en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean existeDNI(int dni) {
        for (Usuario usuario : usuarios) {
            if (usuario.getDNI() == dni) {
                return true;
            }
        }
        return false;
    }

    public boolean existeCorreo(String correo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }

    public void eliminarUsu(int codigo, Connection connection) {
        String sql = "DELETE FROM Usuario WHERE codigo = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modificarUsu(Usuario usuarioModificado, Connection connection) {
        String sql = "UPDATE Usuario SET nombre = ?, DNI = ?, contraseña = ?, rol = ?, correo = ? WHERE codigo = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuarioModificado.getNombre());
            statement.setInt(2, usuarioModificado.getDNI());
            statement.setString(3, usuarioModificado.getContraseña());
            statement.setInt(4, usuarioModificado.getRol());
            statement.setString(5, usuarioModificado.getCorreo());
            statement.setInt(6, usuarioModificado.getCodigo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al modificar el usuario en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ActualizarTablaUsu(JTable tblUsuario) {
        DefaultTableModel dtm = (DefaultTableModel) tblUsuario.getModel();
        Object nf[] = {};

        for (int i = (contadorfila - 1); i >= 0; i--) {
            dtm.removeRow(i);
        }
        contadorfila = 0;
        for (Usuario usuario : usuarios) {
            dtm.addRow(nf);
            tblUsuario.setValueAt(usuario.getCodigo(), contadorfila, 0);
            tblUsuario.setValueAt(usuario.getNombre(), contadorfila, 1);
            tblUsuario.setValueAt(usuario.getContraseña(), contadorfila, 2);
            tblUsuario.setValueAt(usuario.getDNI(), contadorfila, 3);
            tblUsuario.setValueAt(usuario.getRol(), contadorfila, 4);
            contadorfila++;
        }
    }

    public List<Usuario> obtenerUsuarios(Connection connection) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                String nombre = resultSet.getString("nombre");
                int DNI = resultSet.getInt("DNI");
                String contraseña = resultSet.getString("contraseña");
                int rol = resultSet.getInt("rol");
                String correo = resultSet.getString("correo");
                Usuario usuario = new Usuario(codigo, nombre, DNI, contraseña, rol, correo);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener usuarios desde la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return usuarios;
    }

    public void llenarCBVendedor(JComboBox<String> cbVendedor) {
        for (Usuario usuario : usuarios) {
            cbVendedor.addItem(usuario.getNombre());
        }
    }




}
