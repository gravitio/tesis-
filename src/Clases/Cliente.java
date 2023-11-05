package Clases;

public class Cliente extends Persona {

    private int celular;

    public Cliente() {
    }

    public Cliente(int codigo, String nombre, int DNI, int celular) {
        super(codigo, nombre, DNI, celular);
        this.celular = celular;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

}
