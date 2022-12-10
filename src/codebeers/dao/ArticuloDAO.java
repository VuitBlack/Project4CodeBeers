package codebeers.dao;

import codebeers.modelo.Articulo;
import java.util.List;

/**
 *  Data Acces Object
 *
 *  CRUD - Create Retrieve Update Delete
 */

public interface ArticuloDAO {

    /**CREATE**/
    /**
     * Inserta un nuevo registro en la tabla Articulo
     * @param articulo
     * @return
     */
    Articulo create(Articulo articulo);


    /**RETRIEVE**/
    /**
     * Recuperar todos los articulos de la base de datos de la tabla Articulos
     * @return lista de articulos
     */
    List<Articulo> findAll();

    /**
     * Busca un articulo por su Id
     * @return articulo
     */
    Articulo findById(Long id);

    /**
     * Busca articulospor precio
     * @return lista de articulos
     */
    List<Articulo> findByPvp(Float pvp);


    /**UPDATE**/
    /**
     * Actualiza un registro existente en la tabla Articulo
     * @param articulo
     * @return
     */
    Articulo update(Articulo articulo);


    /**DELETE**/
    /**
     * Borra un registro existente de la tabla Articulo
     * @param id
     * @return
     */
    boolean deleteById(Long id);


}
