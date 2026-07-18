package model;

import interfaces.Registrable;

//Esta clase hereda atributos de la clase Persona e implementa la interfaz Registable
public class Empleado extends Persona implements Registrable {

    //Atributo propio
    private String especialidad;

    //Constructor
    public Empleado(
            String codigo,
            String nombre,
            String especialidad){
        super(codigo,nombre);
        this.especialidad = especialidad;
    }

    //Getter
    public String getEspecialidad(){
        return especialidad;
    }

    //toString
    @Override
    public String toString(){
        return super.toString()
                + "\nCargo: "
                + especialidad;
    }

    //Sobrescribir metodo mostrarResumen
    @Override
    public void mostrarResumen(){
        System.out.println(this);
    }
}
