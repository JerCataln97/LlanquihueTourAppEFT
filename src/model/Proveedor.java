package model;

import interfaces.Registrable;

//Esta clase hereda atributos de la clase Persona e implementa la interfaz Registable
public class Proveedor extends Persona implements Registrable {

    //Atributo propio
    private String area;

    //Constructor
    public Proveedor(
            String codigo,
            String nombre,
            String area){
        super(codigo,nombre);
        this.area = area;
    }

    //Getter
    public String getArea(){
        return area;
    }

    //toString
    @Override
    public String toString(){
        return super.toString()
                + "\nArea: "
                + area;
    }

    //Sobrescribir metodo mostrarResumen
    @Override
    public void mostrarResumen(){
        System.out.println(this);
    }
}
