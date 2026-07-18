package model;

import interfaces.Registrable;

//Esta clase implementa la interfaz Registable
public class Experiencia implements Registrable {

    //Atributos
    private Cliente cliente;
    private String experiencia;
    private int valor;
    private String horario;
    private Empleado encargado;

    //Constructores
    public Experiencia() {
    }

    public Experiencia(Cliente cliente, String experiencia, int valor, String horario, Empleado encargado) {
        this.cliente = cliente;
        this.experiencia = experiencia;
        this.valor = valor;
        this.horario = horario;
        this.encargado = encargado;
    }

    //Getters y Setters
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getExperiencia() {
        return experiencia;
    }
    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Empleado getEncargado() {
        return encargado;
    }
    public void setEncargado(Empleado encargado) {
        this.encargado = encargado;
    }

    //Sobrescribir metodo mostrarResumen
    @Override
    public void mostrarResumen() {

        System.out.println("Cliente      : " + cliente.getCodigo() + " - " + cliente.getNombre());
        System.out.println("Experiencia  : " + experiencia);
        System.out.println("Valor        : $" + valor);
        System.out.println("Horario      : " + horario);

        if (encargado != null) {
            System.out.println("Encargado    : " + encargado.getNombre());
        }
    }
}
