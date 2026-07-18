package data;

import model.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GestorExperiencias {

    //Crear lista que almacene objetos tipo "Experiencia"
    private ArrayList<Experiencia> listado = new ArrayList<>();

    //Metodo "experiencias" lee un archivo y carga los datos de la lista
    public void experiencias (String lugarLista, GestorEntidades gestor){

        try {
            File lista = new File(lugarLista);
            Scanner leer = new Scanner(lista);

            //Recorrer el archivo linea por linea
            while (leer.hasNextLine()){
                String linea = leer.nextLine();
                //Separar los datos usando un ";"
                String[] partes = linea.split(";");
                if (partes.length == 5) {

                    //Busca cliente y empleado ya guardados
                    Cliente cliente = gestor.buscarCliente(partes[0]);
                    Empleado encargado = gestor.buscarEmpleado(partes[4]);
                    int valor = Integer.parseInt(partes[2]);

                    //Crea objeto Experiencia con todos los datos
                    Experiencia produc = new Experiencia(
                            cliente,
                            partes[1],
                            valor,
                            partes[3],
                            encargado
                    );

                    //Guarda el objeto en la lista
                    listado.add(produc);
                }
            }
            leer.close();

        } catch (Exception e) {
            System.out.println("Error en el archivo");
        }

    }

    //Metodo para guardar reservas
    public void guardarExperiencia (

            Cliente cliente,
            String experiencia,
            int valor,
            String horario,
            Empleado encargado) {

        try {
            //Abre el archivo experiencias.txt y agrega al final sin borrar
            FileWriter archivo = new FileWriter("src/utils/experiencias.txt", true);
            PrintWriter escribir = new PrintWriter(archivo);

            //Guarda con los codigos de cliente y encargado
            escribir.println(
                    cliente.getCodigo() + ";" +
                    experiencia + ";" +
                    valor + ";" +
                    horario + ";" +
                    encargado.getCodigo()
            );

            escribir.close();

            //Crea objeto Experiencia, para que aparezca sin reiniciar el programa
            Experiencia nueva = new Experiencia(
                    cliente,
                    experiencia,
                    valor,
                    horario,
                    encargado
            );

            listado.add(nueva);


        } catch (Exception e) {
            System.out.println("Error al guardar experiencia");
        }
    }

    //Metodo mostrarExperiencias muestra cada reserva de la lista
    public void mostrarExperiencias() {

        for(Experiencia e : listado){
            System.out.println("------------------");
            e.mostrarResumen();

        }
    }
}

