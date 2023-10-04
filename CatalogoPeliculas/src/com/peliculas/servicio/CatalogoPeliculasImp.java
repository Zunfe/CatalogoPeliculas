
package com.peliculas.servicio;

import com.peliculas.datos.AccesoDatos;
import com.peliculas.datos.AccesoDatosImp;
import com.peliculas.domain.Pelicula;
import com.peliculas.exepciones.AccesoDatosEx;
import java.util.List;



public class CatalogoPeliculasImp implements CatalogoPeliculas {

    private final AccesoDatos datos;

    public CatalogoPeliculasImp() {
        this.datos = new AccesoDatosImp() ;
    }
    
    
    
    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula = new Pelicula(nombrePelicula);
        boolean anexar = false;
        try {
            anexar = datos.existe(NOMBRE_RECURSO);
            datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void listarPelicula() {
        try {
            List<Pelicula> peliculas = datos.listar(NOMBRE_RECURSO);
            for( Pelicula  pelicula: peliculas){
                System.out.println("pelicula: " + pelicula);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarPelicula(String buscar) {
       String resultado = null;
        try {
            resultado = datos.buscar(NOMBRE_RECURSO, buscar);
           
        } catch (AccesoDatosEx ex) {
             System.out.println("Error de acceso datos = " );
             ex.printStackTrace(System.out);
        }
        System.out.println("resultado = " + resultado);
    }

    @Override
    public void iniciarCatalogoPeliculas() {
        try {
            if(datos.existe(NOMBRE_RECURSO)){
                datos.borrar(NOMBRE_RECURSO);
                datos.crear(NOMBRE_RECURSO);
            }
            else{
                datos.crear(NOMBRE_RECURSO);
            }} catch (AccesoDatosEx ex) {
            System.out.println("Error al iniciar catalogo de peliculas");
            ex.printStackTrace(System.out);
        }
    }
    
}
