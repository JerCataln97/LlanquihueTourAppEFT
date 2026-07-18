package model;

//Clase abstract ya que no se utilizara para crear objetos directamente
public abstract class Persona {

    //Atributos
    protected String codigo;
    protected String nombre;

    //Constructor
    public Persona(String codigo, String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
    }

    //Getters y Setter
    public String getCodigo(){
        return codigo;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    //toString
    @Override
    public String toString(){
        return "Codigo: " + codigo +
                "\nNombre: " + nombre;
    }
}
