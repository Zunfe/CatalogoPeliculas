
package com.peliculas.datos;

import com.peliculas.domain.Pelicula;
import com.peliculas.exepciones.*;
import java.util.List;


public interface AccesoDatos {

    boolean existe(String nombreRecurso) throws AccesoDatosEx;

    List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx;

    void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx;

    String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx;

    void crear(String NombreRecurso) throws AccesoDatosEx;

    void borrar(String NombreRecurso) throws AccesoDatosEx;
    
    
}
