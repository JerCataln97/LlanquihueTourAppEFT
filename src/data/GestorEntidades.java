package data;


import interfaces.Registrable;
import model.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GestorEntidades {

    //Crear lista que almacene objetos tipo "Registrable"
    private ArrayList<Registrable> listado2 = new ArrayList<>();

    //Metodo "baseDatos" lee un archivo y carga los datos de la lista
    public void baseDatos(String lugarLista){

        try {
            File lista = new File(lugarLista);
            Scanner leer = new Scanner(lista);

            //Recorrer el archivo linea por linea
            while(leer.hasNextLine()){
                String linea = leer.nextLine();
                //Separar los datos usando un ";"
                String[] partes = linea.split(";");

                if(partes.length == 4){
                    //Para saber que objeto crear
                    String tipo = partes[0];
                    switch(tipo){
                        //Diferentes en caso de que sea Empleado, Proveedor o Cliente y los agrega a la lista
                        case "EMPLEADO":
                            Empleado emp = new Empleado(
                                    partes[1],
                                    partes[2],
                                    partes[3]
                            );

                            listado2.add(emp);
                            break;

                        case "PROVEEDOR":
                            Proveedor pro = new Proveedor(
                                    partes[1],
                                    partes[2],
                                    partes[3]
                            );

                            listado2.add(pro);
                            break;

                        case "CLIENTE":
                            Cliente cli = new Cliente(
                                    partes[1],
                                    partes[2],
                                    partes[3]
                            );

                            listado2.add(cli);
                            break;
                    }
                }
            }
            leer.close();

        } catch(Exception e){
            System.out.println("Error en el archivo");
        }
    }

    //Para saber cual sera el siguiente codigo y no se repitan
    public int contClientes(){
        int contador = 0;
        for(Registrable r : listado2){
            if(r instanceof Cliente){
                contador++;
            }
        }
        return contador + 1;
    }
    public int contEmpleados(){
        int contador = 0;
        for(Registrable r : listado2){
            if(r instanceof Empleado){
                contador++;
            }
        }
        return contador + 1;
    }
    public int contProveedores(){
        int contador = 0;
        for(Registrable r : listado2){
            if(r instanceof Proveedor){
                contador++;
            }
        }
        return contador + 1;
    }

    //Metodo para editar cliente, cambiar nombre y rut
    public void editarCliente(String codigo, String nuevoNombre, String nuevoRut){

        //Recorre la lista buscando el cliente
        for(Registrable r : listado2){
            if(r instanceof Cliente){
                Cliente c = (Cliente) r;

                //Si el codigo coincide, modifica el nombre y rut
                if(c.getCodigo().equalsIgnoreCase(codigo)){
                    c.setNombre(nuevoNombre);
                    c.setRut(nuevoRut);

                    try{
                        //Actualiza el archivo, borrando los datos ya que no tiene true
                        FileWriter archivo = new FileWriter("src/utils/datos.txt");
                        PrintWriter escribir = new PrintWriter(archivo);

                        //Recorre la lista y vuelve a escribir pero con los datos actualizados
                        for(Registrable reg : listado2){

                            if(reg instanceof Empleado){
                                Empleado e = (Empleado) reg;
                                escribir.println(
                                        "EMPLEADO;" +
                                                e.getCodigo() + ";" +
                                                e.getNombre() + ";" +
                                                e.getEspecialidad()
                                );

                            }else if(reg instanceof Proveedor){
                                Proveedor p = (Proveedor) reg;
                                escribir.println(
                                        "PROVEEDOR;" +
                                                p.getCodigo() + ";" +
                                                p.getNombre() + ";" +
                                                p.getArea()
                                );

                            }else if(reg instanceof Cliente){
                                Cliente cli = (Cliente) reg;
                                escribir.println(
                                        "CLIENTE;" +
                                                cli.getCodigo() + ";" +
                                                cli.getNombre() + ";" +
                                                cli.getRut()
                                );
                            }
                        }

                        escribir.close();

                        System.out.println("Cliente actualizado.");

                    }catch(Exception e){
                        System.out.println("Error al actualizar archivo.");
                    }
                    return;
                }
            }
        }

        System.out.println("Cliente no encontrado.");
    }

    //Metodo para agregar objeto a l lista
    public void registrar(Registrable dato){
        listado2.add(dato);
    }

    //Metodo para guardar objeto en datos.txt
    public void guardarArchivo(Registrable dato){

        try{
            //Abre el archivo datos.txt y agrega al final sin borrar
            FileWriter archivo = new FileWriter("src/utils/datos.txt", true);
            PrintWriter escribir = new PrintWriter(archivo);

            //Instanceof si es Empleado, Proveedor o CLiente
            if(dato instanceof Empleado){
                Empleado e = (Empleado) dato;
                escribir.println(
                        "EMPLEADO;" +
                                e.getCodigo() + ";" +
                                e.getNombre() + ";" +
                                e.getEspecialidad()
                );

            }else if(dato instanceof Proveedor){
                Proveedor p = (Proveedor) dato;
                escribir.println(
                        "PROVEEDOR;" +
                                p.getCodigo() + ";" +
                                p.getNombre() + ";" +
                                p.getArea()
                );

            }else if(dato instanceof Cliente){
                Cliente c = (Cliente) dato;
                escribir.println(
                        "CLIENTE;" +
                                c.getCodigo() + ";" +
                                c.getNombre() + ";" +
                                c.getRut()
                );
            }

            escribir.close();


        }catch(Exception e){
            System.out.println("Error guardando archivo");
        }
    }

    //Metodo mostrar, recorre la lista usando ciclo for-each para mostrar los datos guardados
    public void mostrar (){
        for (Registrable r : listado2){

            System.out.println("-----------------------------");

            //instanceof para saber que tipo de objeto es
            if (r instanceof Empleado) {
                System.out.println("EMPLEADO: ");
            } else if (r instanceof Proveedor) {
                System.out.println("PROVEEDOR: ");
            } else if (r instanceof Cliente) {
                System.out.println("CLIENTE: ");
            }

            System.out.println("-----------------------------");

            r.mostrarResumen();
            System.out.println();
        }
    }

    //Recorre la lista buscando empleados
    public ArrayList<Empleado> buscarEmpleados(){

        ArrayList<Empleado> empleados = new ArrayList<>();

        for(Registrable r : listado2){

            if(r instanceof Empleado){
                empleados.add((Empleado) r);
            }
        }
        return empleados;
    }

    //Recorre la lista buscando un cliente con el mismo codigo
    public Cliente buscarCliente(String codigo){

        for(Registrable r : listado2){

            if(r instanceof Cliente){
                Cliente c = (Cliente) r;

                if(c.getCodigo().equals(codigo)){
                    return c;
                }
            }
        }
        return null;
    }

    //Recorre la lista buscando un empleado con el mismo codigo
    public Empleado buscarEmpleado(String codigo){

        for(Registrable r : listado2){

            if(r instanceof Empleado){
                Empleado e = (Empleado) r;

                if(e.getCodigo().equals(codigo)){
                    return e;
                }
            }
        }
        return null;
    }

    //Obtener la lista
    public ArrayList<Registrable> getLista(){ return listado2; }

}