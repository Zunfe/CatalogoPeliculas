
package com.peliculas.datos;

import com.peliculas.domain.Pelicula;
import com.peliculas.exepciones.*;
import java.io.*;
import java.util.*;


public class AccesoDatosImp implements AccesoDatos {

    @Override
    public boolean existe(String nombreRecurso) {
        File archivo = new File(nombreRecurso);
        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {

        File archivo = new File(nombreRecurso);
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            while (linea != null) {
                Pelicula pelicula = new Pelicula(linea);
                peliculas.add(pelicula);
                linea = entrada.readLine();

            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Exepcion al Listar peliculas: " + ex.getMessage());
        } catch (IOException ex) {

        }
        return peliculas;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {
        File archivo = new File(nombreRecurso);

        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("Se ha escrito en el archivo : " + pelicula);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new EscrituraDatosEx("Exepcion al listar peliculas " + ex.getMessage());
        }

    }

    @Override
    public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx {
        File archivo = new File(nombreRecurso);
        String resultado = null;

        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
           // linea = entrada.readLine();
            int indice = 1;
            while ((linea = entrada.readLine()) != null) {
                linea = linea .toLowerCase();
                buscar = buscar.toLowerCase();
                
                if (linea.contains(buscar)) {
                    resultado = "Pelicula " + linea + " Encontrada en la linea del archivo #  " + indice;
                    break;
                }
               // linea = entrada.readLine();
                indice++;
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Exepcion al Buscar  pelicula: " + ex.getMessage());
        } catch (IOException ex) {
            throw new LecturaDatosEx("Exepcion al Buscar  pelicula: " + ex.getMessage());
        }

        return resultado;
    }

    @Override
    public void crear(String NombreRecurso) throws AccesoDatosEx {
        File archivo = new File(NombreRecurso);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("Se ha creado el archivo");
        } catch (IOException ex) {

            ex.printStackTrace();
            throw new AccesoDatosEx("Excepcion al crear el archivo:" + ex.getMessage());
        }
    }

    @Override
    public void borrar(String NombreRecurso) throws AccesoDatosEx {
        File archivo = new File(NombreRecurso);

        if (archivo.exists()) {
            archivo.delete();
        }
        System.out.println("Se ha borrado el archivo");
    }

}
