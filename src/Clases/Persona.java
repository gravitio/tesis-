package Clases;

public class Persona {
    private int codigo;
    private String nombre;
    private int DNI;
    int rol; // Agregar un campo para el rol

    public Persona() {
    }

    public Persona(int codigo, String nombre, int DNI, int rol) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.DNI = DNI;
        this.rol = rol; // Incluir el rol en el constructor
    }

    // Getters y setters para el campo de rol
    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
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

}
