package net.cris.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.cris.app.model.Detalle;
import net.cris.app.repository.DetallesRepository;

@Service
public class DetallesServiceJPA implements IDetallesService{
	
	@Autowired
	private DetallesRepository detalleRepo;

	@Override
	public void insertar(Detalle detalle) {
		detalleRepo.save(detalle);
		
	}

}
