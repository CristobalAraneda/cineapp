package net.cris.app.service;

import org.springframework.stereotype.Service;
import net.cris.app.model.Noticia;

@Service
public class NoticiasServiceImpl implements INoticiasService{
	
	// Constructor vacio. Unicamente para imprimir un mensaje al crearse una instancia
	public NoticiasServiceImpl() {
		//System.out.println("Creando una instancia de la clase NoticiasServiceImpl");
	}

	@Override
	public void guardar(Noticia noticia) {
		// TODO Auto-generated method stub
		System.out.println("Guadando el objeto " + noticia + " en la base de datos.");
	}

}
