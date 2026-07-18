package app;

import data.GestorEntidades;
import data.GestorExperiencias;
import model.*;
import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //Crea objeto gestor y gestor2 y carga los datos de las listas
        GestorEntidades gestor = new GestorEntidades();
        gestor.baseDatos("src/utils/datos.txt");
        GestorExperiencias gestor2 = new GestorExperiencias();
        gestor2.experiencias("src/utils/experiencias.txt", gestor);

        //Crea todos los menus a utilizar
        String opciones = "-- LLANQUIHUE TOUR APP --\n" +
                "-------------------------------------------------\n" +
                "1. Experiencias disponibles\n" +
                "2. Base de Datos\n" +
                "3. Salir";

        String opciones2 = "-- EXPERIENCIAS --\n" +
                "-------------------------------------------------\n" +
                "-- Rutas Gastronómicas\n" +
                "-- Paseos Lacustres\n" +
                "-- Excursiones Culturales\n" +
                "-- Tours Personalizados\n" +
                "-------------------------------------------------\n" +
                "1. Reservar\n" +
                "2. Ver reservas\n" +
                "3. Editar\n" +
                "4. Salir";

        String opciones3 = "-- BASE DE DATOS --\n" +
                "-------------------------------------------------\n" +
                "1. Agregar\n" +
                "2. Mostrar\n" +
                "3. Salir";

        String valores = "-- ¿Cuál desea reservar? --\n" +
                "-------------------------------------------------\n" +
                "1. Rutas Gastronómicas - Valor $20.000 - 11:30 hrs\n" +
                "2. Paseos Lacustres - Valor $10.000 - 13:00 hrs\n" +
                "3. Excursiones Culturales - Valor $15.000 - 12:00 hrs\n" +
                "4. Tours Personalizados - Valor $40.000 - A definir\n" +
                "5. Salir";

        String agregarP = "-- ¿Que desea agregar? --\n" +
                "-------------------------------------------------\n" +
                "1. Empleado\n" +
                "2. Proveedor\n" +
                "3. Salir";

        //Contadores, pregunta la cantidad que existen
        int numEmp = gestor.contEmpleados();
        int numPro = gestor.contProveedores();
        int numCli = gestor.contClientes();

        //Crea bucle que muestre el menu principal hasta que se precione la "X" o se escriba "3"
        while (true) {
            String panel = JOptionPane.showInputDialog(opciones);
            if (panel == null || panel.equals("3")) break;

            //Opciones del menu principal
            switch (panel) {

                case "1":
                    //Crea bucle que muestre el menu de las experiencias hasta que se precione la "X" o se escriba "4"
                    while (true) {
                        String panel2 = JOptionPane.showInputDialog(opciones2);
                        if (panel2 == null || panel2.equals("4")) break;

                        //Opciones del menu de experiecias
                        switch (panel2) {

                            case "1":
                                //Crea bucle que muestre las opciones de reserva hasta que se precione la "X" o se escriba "5"
                                while (true) {
                                    String opcionReserva = JOptionPane.showInputDialog(valores);
                                    if (opcionReserva == null || opcionReserva.equals("5")) {
                                        break;
                                    }
                                    //JOptionPane pidiendo los datos del cliente (Nombre y RUT)
                                    String nombreCliente = JOptionPane.showInputDialog ("Nombre del cliente:");
                                    if(nombreCliente == null){
                                        break;
                                    }
                                    String rutCliente = JOptionPane.showInputDialog ("Rut del cliente:");
                                    if(rutCliente == null){
                                        break;
                                    }

                                    //Crea el objeto cliente
                                    Cliente cliente = new Cliente(
                                            "CLI-" + numCli++,
                                            nombreCliente,
                                            rutCliente
                                    );

                                    //Guarda el objeto cliente
                                    gestor.registrar(cliente);
                                    gestor.guardarArchivo(cliente);

                                    //Recorre listado2 buscando Empleados y los muestra como opciones
                                    ArrayList<Empleado> empleados = gestor.buscarEmpleados();

                                    String lista = "Seleccione encargado para la experiencia:\n";

                                    for(int i = 0; i < empleados.size(); i++){
                                        lista += (i+1) + ". "
                                                + empleados.get(i).getNombre()
                                                + "\n";
                                    }
                                    int opcionEmpleado = Integer.parseInt(
                                            JOptionPane.showInputDialog(lista)
                                    );

                                    //opcionEmpleado - 1, porque la lista empieza en el 0, para que la opcion escogida sea correcta
                                    Empleado guia = empleados.get(opcionEmpleado - 1);
                                    String nombre = "";
                                    int valor = 0;
                                    String horario = "";

                                    //Switch con las diferentes opciones de reserva que da el programa
                                    switch(opcionReserva){

                                        case "1":
                                            nombre = "Ruta Gastronómica";
                                            valor = 20000;
                                            horario = "11:30";
                                            break;

                                        case "2":
                                            nombre = "Paseo Lacustre";
                                            valor = 10000;
                                            horario = "13:00";
                                            break;

                                        case "3":
                                            nombre = "Excursión Cultural";
                                            valor = 15000;
                                            horario = "12:00";
                                            break;

                                        case "4":
                                            nombre = "Tour Personalizado";
                                            valor = 40000;
                                            horario = "A definir";
                                            break;
                                    }

                                    //Recibe los datos y los guarda
                                    gestor2.guardarExperiencia(cliente, nombre, valor, horario, guia);

                                    JOptionPane.showMessageDialog(null,
                                            "Reserva guardada correctamente.");
                                }
                                break;

                            case "2":
                                //Metodo recorre el ArrayList experiencias y las muestra en la pantalla
                                gestor2.mostrarExperiencias();
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Las experiencias se mostraron en la consola."
                                );
                                break;

                            case "3":
                                //Para editar se le pide al usuario el codigo de la reserva y pide el nuevo nombre y rut
                                String codigo = JOptionPane.showInputDialog ("Código del cliente:");
                                if(codigo == null){
                                break;
                            }
                                String nuevoNombre = JOptionPane.showInputDialog ("Nuevo nombre:");
                                if(nuevoNombre == null){
                                break;
                            }
                                String nuevoRut = JOptionPane.showInputDialog ("Nuevo RUT:");
                                if(nuevoRut == null){
                                break;
                            }

                                //Busca al cliente por el codigo y modifica los otros datos
                                gestor.editarCliente(codigo, nuevoNombre, nuevoRut);
                        }
                    }
                    break;

                case "2":
                    //Crea bucle que muestre el menu de la base de datos hasta que se precione la "X" o se escriba "3"
                    while (true) {
                        String panel3 = JOptionPane.showInputDialog(opciones3);
                        if (panel3 == null || panel3.equals("3")) break;

                        //Opciones del menu de base de datos
                        switch(panel3){

                            case "1":
                                String opcionAgregar = JOptionPane.showInputDialog(agregarP);

                                //Opciones del menu agregar, nos da la opcion de empleado o proveedor
                                switch(opcionAgregar){

                                    case "1":
                                        //JOptionPane pidiendo nombre del empleado y la especialidad
                                        String nomE = JOptionPane.showInputDialog("Nombre del empleado:");
                                        if(nomE == null){
                                            break;
                                        }
                                        String esp = JOptionPane.showInputDialog("Especialidad:");
                                        if(esp == null){
                                            break;
                                        }

                                        //Crea objeto Empleado
                                        Empleado empleado = new Empleado(
                                                "EMP-" + numEmp++,
                                                nomE,
                                                esp
                                        );

                                        //Guarda objeto Empleado
                                        gestor.registrar(empleado);
                                        gestor.guardarArchivo(empleado);

                                        //Mensaje que se ha guardado
                                        JOptionPane.showMessageDialog(null,"Empleado agregado");
                                        break;


                                    case "2":
                                        //JOptionPane pidiendo nombre del proveedor y la area
                                        String nomP = JOptionPane.showInputDialog("Nombre del proveedor:");
                                        if(nomP == null){
                                            break;
                                        }
                                        String area = JOptionPane.showInputDialog("Área del proveedor:");
                                        if(area == null){
                                            break;
                                        }

                                        //Crea objeto Proveedor
                                        Proveedor proveedor = new Proveedor(
                                                "PROV-" + numPro++,
                                                nomP,
                                                area
                                        );

                                        //Guarda objeto Proveedor
                                        gestor.registrar(proveedor);
                                        gestor.guardarArchivo(proveedor);

                                        //Mensaje que se ha guardado
                                        JOptionPane.showMessageDialog(null,"Proveedor agregado");
                                        break;
                                }
                                break;

                            case "2":
                                //Metodo recorre el ArrayList datos y las muestra en la pantalla los clientes, empleados y proveedores
                                gestor.mostrar();
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Registros mostrados en consola"
                                );
                                break;
                        }
                    }
            }
        }
    }
}