package model;

import interfaces.Registrable;

//Esta clase hereda atributos de la clase Persona e implementa la interfaz Registable
public class Cliente extends Persona implements Registrable {

    //Atributo propio
    private String rut;

    //Constructor
    public Cliente(String codigo, String nombre, String rut) {
        super(codigo, nombre);
        this.rut = rut;
    }

    //Getter y Setter
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }

    //toString
    @Override
    public String toString(){
        return super.toString()
                + "\nRut: "
                + rut;
    }

    //Sobrescribir metodo mostrarResumen
    @Override
    public void mostrarResumen(){
        System.out.println(this);
    }
}
