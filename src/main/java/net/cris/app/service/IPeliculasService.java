package net.cris.app.service;

import java.util.List;

import net.cris.app.model.Pelicula;

public interface IPeliculasService {
	
	void insertar(Pelicula pelicula); 
	
	List<Pelicula> buscarTodas();
	
	Pelicula buscarPorTd(int idPelicula);
	
	List<String> buscaGeneros();

}
